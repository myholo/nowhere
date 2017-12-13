/**
 * Created by zmax on 2017/2/7.
 * 模板
 */

app.controller('UserShowCtrl', function ($scope,$log,$state,Storage,ENV,UserService) {
    $log.debug("enter UserShow ctrl");
    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("UserShow ctrl init ");
        $scope.obj=Storage.get(LOCAL_TMP_OBJ);
    };
    $scope.init();
});