/**
 * 路由器
 * Created by zmax on 2017/2/7.
 */

'use strict';

app.config(function ($stateProvider, $urlRouterProvider) {


    $stateProvider
        //模板
        .state('app', {
            abstract: true,
            views: {
                root: {
                    controller: 'pubCtrl',
                    templateUrl: 'views/zlayout/layout.tpl.html'
                }
            }
        })
        //首页
        .state('app.home', {
            url: '/home',
            views: {
                "content@app": {
                    controller: 'homeCtrl',
                    templateUrl: 'views/zhome/home.html'
                }
            },
            data:{
                title: 'home'
            }
        })
        //帮助
        .state('app.help', {
            url: '/help',
            views: {
                "content@app": {
                    //controller: 'homeCtrl',
                    templateUrl: 'views/zhome/help.html'
                }
            },
            data:{
                title: 'help'
            }
        })
        //User list
        .state('app.User', {
            url: '/UserList',
            views: {
                "content@app": {
                    controller: 'UserListCtrl',
                    templateUrl: 'views/User/UserList.html'
                }
            },
            data:{
                title: 'help'
            }
        })
        //User show
        .state('app.UserShow', {
            url: '/UserShow',
            views: {
                "content@app": {
                    controller: 'UserShowCtrl',
                    templateUrl: 'views/User/UserShow.html'
                }
            },
            data:{
                title: 'help'
            }
        })
    ;
    $urlRouterProvider.otherwise('/login');

});
