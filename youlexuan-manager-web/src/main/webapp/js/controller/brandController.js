//创建controller
app.controller('brandController',function ($scope,$controller,brandService) {
    //继承
    $controller('baseController',{$scope:$scope});

    //查全部
    $scope.findAll=function () {
        //$http.get('../brand/findAll.do')
        brandService.findAll().success(function (resp) {
            $scope.list=resp;
        });
    }

    //保存
    $scope.save=function () {
        if($scope.entity.id==null){//id为空就是添加
            //$http.post('../brand/add.do',$scope.entity)
            brandService.add($scope.entity).success(function (resp) {
                if(resp.success){
                    //重查
                    $scope.reloadList();
                }else {
                    alert(resp.message);
                }
            });
        }else{//id不为空就是修改
            //$http.post('../brand/update.do',$scope.entity)
            brandService.update($scope.entity).success(function (resp) {
                if(resp.success){
                    $scope.reloadList();
                    $scope.entity={};
                }else{
                    alert(resp.message);
                }
            })
        }
    }

    //查询实体
    $scope.findOne=function (id) {
        //$http.get('../brand/findOne.do?id='+id)
        brandService.findOne(id).success(function (resp) {
            $scope.entity=resp;
        })
    }

    //删除
    $scope.del=function () {
        //$http.get('../brand/del.do?ids='+$scope.selectIds)
        brandService.del($scope.selectIds).success(function (resp) {
            if(resp.success){
                $scope.reloadList();
                $scope.selectIds=[];
            }else{
                alert(resp.message);
            }
        })
    }

    //条件查询
    $scope.searchEntity={};//定义一个搜索对象
    $scope.search=function (pageNum,pageSize) {
        //$http.post('../brand/search.do?pageNum='+pageNum+'&pageSize='+pageSize,$scope.searchEntity)
        brandService.search(pageNum,pageSize,$scope.searchEntity).success(function (resp) {
            $scope.paginationConf.totalItems=resp.total;//总的记录数赋值给分页
            $scope.brandList=resp.rows;//给变量赋值，数据集合
        });
    }

})