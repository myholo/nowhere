package com.holo.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/31 13:45
 * OncePerRequestFilter 一次请求只过一次filter
 * 4
 */
public class CsrfFilter extends OncePerRequestFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CsrfFilter.class);
    private boolean nosessiononoff = false;
    private Pattern pattern = null;
    private boolean csrfonoff = true;
    private String csrfSkipPattern = "";
    private String csrfIfErrorRedirectUrl;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.err.print("holo -----------------");
    }


    @Override
    public void destroy() {

    }

    private boolean isCSRF(HttpServletRequest request, HttpServletResponse response) {
        HttpServletRequest httprequest = request;
        String method = request.getMethod();
        String servletPath = httprequest.getServletPath();
        boolean isPost = "POST".equals(method);

        if (isPost) {
            String csrfTocken = CSRFTokenManager.getTokenFromRequest(httprequest);
            if (CSRFTokenManager.getTokenForSession(httprequest.getSession()).equals(csrfTocken)) {
                return false;
            }
            String refertocken = CSRFTokenManager.getReferTokenFromRequest(request);

            return ( (!(refertocken.equals(CSRFTokenManager.getReferTokenForSession(request.getSession())))));

        }

        return false;
    }

    public static String getTokenFromRequest(HttpServletRequest request) {



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

        return  refer_csrf;
    }


}
