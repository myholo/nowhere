/**
 * 路由器 之全页版
 * Created by zmax on 2017/2/7.
 */

'use strict';

app_sig.config(function ($stateProvider,$urlRouterProvider) {


    $stateProvider
        //登录
        .state('login', {
            url: '/login',
            views: {
                root: {
                    controller: 'loginCtrl',
                    templateUrl: 'views/zpub/login.html'
                }
            },
            data:{
                title: 'login'
            }
        })
        //注册
        .state('register', {
            url: '/register',
            views: {
                root: {
                    templateUrl: 'views/zpub/register.html'
                }
            },
            data: {
                title: 'Register',
                htmlId: 'extr-page'
            }
        });
    $urlRouterProvider.otherwise('/login');

});
