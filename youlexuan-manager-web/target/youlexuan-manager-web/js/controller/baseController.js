app.controller('baseController',function ($scope) {


    $scope.jsonToString = function(jsonString,key){
        var json = JSON.parse(jsonString);//json字符串转成json数组或对象
        var value = [];
        for(var i = 0 ; i < json.length;i++){
            value.push(json[i][key]);
        }
        //return value;//["联想","三星","华为","OPPO","小米"]
        return value.toString();//联想,三星,华为,OPPO,小米
    }
//分页控件配置
$scope.paginationConf={
    currentPage:1,//当前页码
    totalItems:10,//总条数
    itemsPerPage:5,//默认每页5条
    perPageOptions:[5,10,15,20],//下拉框
    onChange:function () {
        $scope.reloadList();//重新加载，被分页组件变化触发
    }
};
//分页
$scope.findPage=function (pageNum,pageSize) {
    //$http.get('../brand/findPage.do?pageNum='+pageNum+'&pageSize='+pageSize)
    brandService.findPage(pageNum,pageSize).success(function (resp){
        $scope.paginationConf.totalItems = resp.total;//总记录数，赋值给paginationConf.totalItems
        $scope.brandList = resp.rows;//数据集合
    });
}
//选中id的集合
$scope.selectIds=[];
$scope.updateSelection=function ($event,id) {
    if($event.target.checked==true){//如果被选中
        $scope.selectIds.push(id);//想数组添加id
    }else {
        var i=$scope.selectIds.indexOf(id);
        $scope.selectIds.splice(i,1);//在数组删除id
    }
}

//重新加载列表数据
$scope.reloadList=function () {
    //切换页码
    $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
}

});