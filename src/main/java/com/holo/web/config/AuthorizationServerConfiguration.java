/*
package com.holo.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

*/
/**
 *  AuthorizationServerConfiguration 授权验证配置
    继承AuthorizationServerConfigurerAdapter，配置授权的相关信息，配置的核心都在这里
    在这里进行 配置客户端，配置token存储方式等
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/21 14:36
 * 4
 *//*

@Configuration
@EnableAutoConfiguration
public class AuthorizationServerConfiguration  extends AuthorizationServerConfigurerAdapter {
    private static final String DEMO_RESOURCE_ID = "*";

    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private DruidDataSource dataSource;

    // 初始化JdbcTokenStore
    @Autowired
    public TokenStore getTokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    // 自定义数据库存储tokenStore
   */
/* @Autowired
    public TokenStore getMyTokenStore() {
        return new MyJdbcTokenStore(dataSource);
    }*//*


    @Autowired
    private TokenStore getRedisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean   // 声明ApplyClientDetailService
    public ApplyClientDetailService getClientDetails() {
        return new ApplyClientDetailService();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端, 用于client认证
        clients.withClientDetails(getClientDetails());
*/
/*          //使用存在内存中配置
            clients.inMemory().withClient("client_1")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("all")
                .authorities("client")
                .secret("123456");*//*

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager);   // redis保存token
*/
/*        endpoints.tokenStore(getTokenStore())   // 数据库保存token
                .authenticationManager(authenticationManager);*//*

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();
    }
}
*/
