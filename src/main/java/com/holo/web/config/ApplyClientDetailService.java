/*
package com.holo.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

*/
/**
 *
 * 对Client的访问主要依靠JdbcClientDetailsService类的实现，必须使用官方给出的数据库结构，
 *  如果想自定义数据库结构，可以根据需求重写JdbcClientDetailsService类的实现。
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/21 15:13
 * 4
 *//*

public class     ApplyClientDetailService implements ClientDetailsService {

    @Autowired
    private DataSource dataSource;

    @Override
    public ClientDetails loadClientByClientId(String applyName) throws ClientRegistrationException {

        */
/*
        // 使用mybatic验证client是否存在 ，根据需求写sql
        Map clientMap = applyService.findApplyById(applyName);

        if(clientMap == null) {
            throw new ClientRegistrationException("应用" + applyName + "不存在!");
        }*//*

        System.err.print("applyName ============" + applyName);
//        MyJdbcClientDetailsService jdbcClientDetailsService= new MyJdbcClientDetailsService(dataSource, "authentication");
        JdbcClientDetailsService jdbcClientDetailsService= new JdbcClientDetailsService(dataSource);
        ClientDetails clientDetails = jdbcClientDetailsService.loadClientByClientId(applyName);

        return clientDetails;
    }
}
*/
