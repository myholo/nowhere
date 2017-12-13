/**
 * Created by zmax on 2017/2/7.
 * 模板
 */

app.controller('UserCtrl', function ($scope,$log,Storage,ENV,UserService) {
    $log.debug("enter UserList ctrl");
    /**页码*/
    $scope.page=_.clone(_Page);
    $scope.page.totalpage=1;
    /**页面显示的列表*/
    $scope.list=[];

    /**
     * 初始化
     */
    $scope.init=function(){
        $log.debug("UserList ctrl init ");
        $scope.page.where=Storage.get("UserList"+"QueryWhere");
        $scope.page.pageNo=1;
        $scope.page.hasNextPage=false;
        $scope.list=[];
        $scope.checkHead=false;
        $scope.query();
    };

    /**
     * 给list上加数据
     * @param data
     */
    $scope.addList=function(data){
        $scope.list=data;
        if($scope.list&&$scope.list.length>0&&$scope.list[0].objint1){
            $scope.page.dbsize=$scope.list[0].objint1;
            $scope.page.totalpage=Math.ceil($scope.page.dbsize/$scope.page.pageSize);
        }
    };
    /**
     * 表格头上的选择框，全选或取消
     */
    $scope.checkHeadAll=function(){
        angular.forEach($scope.list, function (obj) {
            obj.checked=$scope.checkHead;
        });
    };
     //////Page系列
    /**
     * pageNo回车
     */
    $scope.pageNoKeyup=function(e){
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.query();
        }
    };
    $scope.pageTop=function(){
        if($scope.page.pageNo==1)
            return;
        $scope.page.pageNo=1;
        $scope.query();
    }
    $scope.pageBack=function(){
        if($scope.page.pageNo==1)
            return;
        $scope.page.pageNo=$scope.page.pageNo-1;
        if($scope.page.pageNo < 1)
            $scope.page.pageNo=1;
        $scope.query();
    }
    $scope.pageForword=function(){
        if(!$scope.page.totalpage)
            return;
        if($scope.page.pageNo==$scope.page.totalpage)
            return;
        $scope.page.pageNo=$scope.page.pageNo+1;
        $scope.query();

    }
    $scope.pageButtom=function(){
        if(!$scope.page.totalpage)
            return;
        if($scope.page.pageNo==$scope.page.totalpage)
            return;
        $scope.page.pageNo=$scope.page.totalpage;
        $scope.query();
    }
    /**
     * 查询
     */
    $scope.query=function(){
        $log.debug($scope.page);
        UserService.query($scope.page).then(function (data) {
            $log.debug("UserList ctrl query then");
            $scope.addList(data);
        });
    };
    /**
     * 一下页
     */
    $scope.more=function(){
        $log.debug("UserList ctrl more=========");
        if(!$scope.page.hasNextPage){
            return;
        }
        UserService.more($scope.page).then(function (data) {
            $log.debug("UserList ctrl more then");
            $scope.addList(data);
        });
    };

    $scope.init();
    //ctrlReinitMap.remove('UserListCtrl');
});