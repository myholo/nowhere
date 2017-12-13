/**
 * Created by zmax on 2017/2/7.
 * 共用
 */

app.controller('pubCtrl', function ($scope,$rootScope,$state ,$log,CommonService,Storage) {
    /**当前登录用户*/
    $rootScope.pubLocalUser={};
    /**当前客户端信息*/
    $rootScope.pubClientInfo={};
    /**
     * 初始化
     */
    $scope.init = function(){
        $log.debug("pubCtrl init");
    }

    /**
     * 检查当前是否处于登录状态
     * 登录成功标记：
     * 内存中有 $rootScope.pubLocalUser，并且其中的token不为空
     * 查一下本地有没有token
     * @returns {boolean}
     */
    $scope.checklogin = function(){
        $log.debug("checklogin");
        if($rootScope.pubLocalUser && $rootScope.pubLocalUser.token && $rootScope.pubLocalUser.token.length > 0)
            return true;
        $rootScope.pubLocalUser= Storage.get(LOCAL_USER);
        if($rootScope.pubLocalUser && $rootScope.pubLocalUser.token && $rootScope.pubLocalUser.token.length > 0)
            return true;
        $log.debug($rootScope.pubLocalUser);
        return false;
    }
    /**
     * 退出登录
     * 清硬盘，清内存
     */
    $scope.logout = function(){
        $log.debug("logout");
        $.SmartMessageBox({
            title: "易健",
            content: "确定要退出登录？",
            buttons: '[No][Yes]'
        }, function (ButtonPressed) {
            if (ButtonPressed === "Yes") {
                $rootScope.pubLocalUser={};
                $rootScope.pubClientInfo={};

                Storage.remove(LOCAL_USER);
                Storage.remove(CLIENT_INFO);
                $state.go('login');
            }
            if (ButtonPressed === "No") {
            }
        });

    }

    $scope.init();
});