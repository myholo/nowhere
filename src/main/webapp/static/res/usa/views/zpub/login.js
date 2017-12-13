/**
 * Created by zmax on 2017/2/7.
 * 登录
 */

app.controller('loginCtrl', function ($scope,$rootScope,$state,$log,zspecService,CommonService,Storage) {

    $scope.vm={};
    $scope.vm.user={};
    /**
     * 初始化
     */
    $scope.init = function(){
        $log.debug("loginCtrl init");
        $scope.vm.user=Storage.get(LOCAL_USER);
        $log.debug($scope.vm.user);
    }
    /**
     * 账号密码
     * @returns {boolean}
     */
    $scope.login = function(){
        $log.debug("loginCtrl login "+$scope.vm.user.username+","+$scope.vm.user.password+","+$scope.vm.user.isremember);
        zspecService.dologin($scope.vm.user).then(function (data) {
            //登录成功
            //老对象备份
            var oldUser= _.clone($scope.vm.user);
            $scope.vm.user=data;
            //数据恢复
            $scope.vm.user.isremember=oldUser.isremember;
            //这时的密码是密文了
            if(!$scope.vm.user.isremember){
                $scope.vm.user.password="";
            }

            $log.debug($scope.vm.user);
            Storage.set(LOCAL_USER,$scope.vm.user);
            //token以clientInfo为准，因此从user中复制过去
            var clientInfo=Storage.get(CLIENT_INFO);
            if(!clientInfo){
                clientInfo=_.clone(_ClientInfo);
            }
            clientInfo.token=$scope.vm.user.token;
            //登录成功标记：硬盘中有CLIENT_INFO，并且其中的token不为空
            Storage.set(CLIENT_INFO,clientInfo);
            $state.go('app.home');
        });
    }
    /**
     * 注册
     */
    $scope.reg = function(){
        //Storage.get("USER");
        CommonService.alertError("现在不开放注册");
        //$rootScope.$broadcast('event.alertError', "现在不开放注册");
        //$state.go('app.home');
        //$rootScope.$broadcast('event.NeedLoginException', 'nothing');
    }
    $scope.init();
});