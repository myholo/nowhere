/*
package com.holo.web.oauth2;

import com.holo.domain.my.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/29 11:23
 * 4
 *//*


//@RestController
public class UserController {

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping("/bar")
    public String bar(@RequestHeader("Authorization") String auth) {

        MyUserDetails userDetails = (MyUserDetails) tokenStore.readAuthentication(auth.split(" ")[1]).getPrincipal();

        User user = userDetails.getUser();

        return user.getUsername() + ":" + user.getPassword();
    }
}*/
