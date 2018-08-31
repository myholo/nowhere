package com.holo.web.filter;

import com.holo.concurrent.annotation.Lock;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 *  动态CSRF token
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/31 14:25
 * 4
 */
public class CSRFTokenManager {
    public static final String REFER_CSRF_PARAM_NAME = "refer_csrf_token";
    public static final String CSRF_PARAM_NAME = "csrf_token";
    public static final String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenManager.class.getName() + ".tokenval";


    @Lock
    public static String getReferTokenForSession(HttpSession session) {
        String token = null;
        token = (String)session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
        if (StringUtils.isBlank(token)) {
            token = generateCsrfToken();
            session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
        }
        return token;
    }

    public static String generateCsrfToken() {
        return UUID.randomUUID().toString();
    }
    public static String getTokenForSession(HttpSession session) {
        String token = null;

        // I cannot allow more than one token on a session - in the case of two
        // requests trying to
        // init the token concurrently
        synchronized (session) {
            token = (String) session
                    .getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
            if (StringUtils.isBlank(token)) {
                token = generateCsrfToken();
                session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
            }
        }
        return token;
    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        String csrf = request.getParameter(CSRF_PARAM_NAME);
        if(StringUtils.isNotBlank(csrf)){
            return csrf;
        }

        return request.getHeader(CSRFTokenManager.CSRF_PARAM_NAME);
    }

    /**
     * Extracts the token value from the session
     *
     * @param request
     * @return
     */
    public static String getReferTokenFromRequest(HttpServletRequest request) {
        String refer_csrf = request.getHeader("Referer");
        if(StringUtils.isNotBlank(refer_csrf)){
            //从refer_csrf_token=之后开始
            //refer_csrf_token=xxx这是拼在地址最后面的
            int csrfIndex = refer_csrf.indexOf(REFER_CSRF_PARAM_NAME)+REFER_CSRF_PARAM_NAME.length()+1;
            String token = refer_csrf.substring(csrfIndex);
            return token;
        }else{
            return null;
        }

    }

}
