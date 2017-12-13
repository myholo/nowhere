/**
 * 特别的网络服务
 */
app
    .service('zspecService', function($rootScope,$q,$http,$log,ENV) {
        $log.debug("zspecService in");
        //列表
        var list;
		//当前的对象
        var obj;
		//页码信息
        var page = {
            where: '', //条件
            pageNo: 1, //第几页，从1开始
            pageSize:ENV.DEFPAGESIZE, //每页多少数量
            hasNextPage: true //是否还有下一页
        };
        var thislist=[]; //这次取到的数据

        return {
            /**
             * 登录
             * @param user
             * @returns {d.promise|Function|*|promise}
             */
            dologin: function(user) {
                var url = ENV.api + "/dologinb2c";
                var deferred = $q.defer();
                $http({
                    method: 'GET',
                    url: url,
                    params: {username: user.username, password: user.password, roleId: user.roleId, code: user.code}
                }).success(function(data) {
                    deferred.resolve(data);
                });
                return deferred.promise;
            },
        };
    });
