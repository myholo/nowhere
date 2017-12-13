/**
 * Created by zmax on 2017/2/7.
 * 自定义服务工厂
 */

/**
 * http ajax注入以及返回错误拦截
 */
app.config(function ($provide, $httpProvider, RestangularProvider) {

    $provide.factory('sessionInjector', ['$rootScope', '$q', '$log', 'Storage', function ($rootScope, $q, $log, Storage) {
        return {
            'request': function (config) {
                //console.log("request");
                //config.header.xx=111;
                if (config.params) {
                    config.params.ver = _ClientInfo.ver;
                    var clientInfo = Storage.get(CLIENT_INFO);
                    if (clientInfo) {
                        if (clientInfo.cli)config.params.cli = clientInfo.cli;
                        if (clientInfo.openid)config.params.openid = clientInfo.openid;
                        if (clientInfo.openidmd5)config.params.openidmd5 = clientInfo.openidmd5;
                        if (clientInfo.token)config.params.token = clientInfo.token;
                    } else {
                        config.params.cli = _ClientInfo.cli;
                    }
                }
                if (config.headers) {
                    //改变HEADER会改变请求结构，要服务器支持
                    //config.headers["xx"]=111;
                    //config.headers.yy=222;
                }
                //console.log(config);
                return config || $q.when(config);
            },
            'requestError': function (rejection) {
                //console.log('requestError:' + rejection);
                return rejection;
            },
            //success -> don't intercept
            'response': function (response) {
                //console.log('response:' + response);
                return response || $q.when(response);
            },
            //error -> if 401 save the request and broadcast an event
            'responseError': function (response) {
                //401表示没有权限需要登录
                if (response.status === 401) {
                    var deferred = $q.defer(),
                        req = {
                            config: response.config,
                            deferred: deferred
                        };
                    //$rootScope.requests401.push(req);
                    $rootScope.$broadcast('event.NeedLoginException', 'serv');
                    return deferred.promise;
                }
                //406表示系统错误，弹框
                if (response.status === 406) { //NOT_ACCEPTABLE
                    $log.debug("http error 406");
                    var deferred = $q.defer();
                    $rootScope.$broadcast('event.alertError', response.data);
                    //CommonService.alertm(response.data).then(function (res) {});
                    return deferred.promise;
                }
                return $q.reject(response);
            }
        };
    }]);
    // Add the interceptor to the $httpProvider.
    $httpProvider.interceptors.push('sessionInjector');

    RestangularProvider.setBaseUrl(location.pathname.replace(/[^\/]+?$/, ''));
});


/**
 * 本地存储
 */
app.factory('Storage', function() {
    "use strict";
    return {
        /**
         * 保存对象
         * ex. Storage.set("user",user)
         * @param key
         * @param data
         * @returns {*}
         */
        set: function(key, data) {
            return window.localStorage.setItem(key, window.JSON.stringify(data));
        },
        /**
         * 取出对象
         * ex. user=Storage.get("user")
         * @param key
         * @returns {*}
         */
        get: function(key) {
            try {
                return window.JSON.parse(window.localStorage.getItem(key));
            } catch(err) {
                console.log("window.JSON.parse.ERR"+ err.name+" ---> "+ err.message+" ---> ");
                console.log(key);
                console.log(window.localStorage.getItem(key));
                return null;
            }
        },
        /**
         * 删除
         * @param key
         * @returns {*}
         */
        remove: function(key) {
            return window.localStorage.removeItem(key);
        },

        /**
         * 清空
         * @returns {*}
         */
        clear: function() {
            return window.localStorage.clear();
        }
    };
});

/**
 * 普通服务
 */
app.factory('CommonService', function($http, $rootScope,ENV) {
    "use strict";
    return {
        /**
         * 弹警告信息窗口，
         * 使用方法 CommonService.alertm('消息','标题');
         * @param msg
         * @param title 可以为空
         * @returns {Object|*}
         */
        alertError: function(msg,title){
            return $.bigBox({
                title:title ? title : ENV.PRJCNAME,
                content: msg,
                color: "#C46A69",
                icon: "fa fa-warning shake animated",
                //number: "1",
                timeout: 6000
            });
        },
        /**
         * 二次确认窗口
         * 使用方法 CommonService.confirm('消息','标题').then(function(res) {if(res) {} else {}});
         * @param msg
         * @param title
         */
        confirm: function(msg,title) {
            return $.bigBox({
                title:title ? title : ENV.PRJCNAME,
                content: msg,
                color: "#C46A69",
                icon: "fa fa-warning shake animated",
                number: "1",
                timeout: 6000
            });
        }
    };
})
;

