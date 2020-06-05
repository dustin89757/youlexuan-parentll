//商品详细页（控制层）
app.controller('itemController',function($scope){
    $scope.specificationItems = {};//用来记录你所点击勾选的规格选项

    //添加商品到购物车
    $scope.addToCart=function(){
        alert('skuid:'+$scope.sku.id);	 //id +num
    }


    //用户选择规格
    $scope.selectSpecification=function(name,value){
        $scope.specificationItems[name]=value;
        $scope.searchSku();//读取sku
    }

    //查询SKU
    $scope.searchSku=function(){
        for(var i=0;i<skuList.length;i++ ){
            if( matchObject(skuList[i].spec ,$scope.specificationItems ) ){
                $scope.sku=skuList[i];
                return ;
            }
        }
        $scope.sku={id:0,title:'--------',price:0};//如果没有匹配的
    }


    //匹配两个对象
    $scope.matchObject=function(map1,map2){
        for(var k in map1){
            if(map1[k]!=map2[k]){
                return false;
            }
        }
        for(var k in map2){
            if(map2[k]!=map1[k]){
                return false;
            }
        }
        return true;
    }

    //加载默认SKU，页面上要显示sku的title和price
    $scope.loadSku=function(){
        $scope.sku=skuList[0];
        $scope.specificationItems= JSON.parse(JSON.stringify($scope.sku.spec)) ;
    }

    //数量操作，参数：正数或者负数
    $scope.addNum=function(x){
        $scope.num=$scope.num+x;
        if($scope.num<1){
            $scope.num=1;
        }
    }
    $scope.specificationItems={};//记录用户选择的规格，被选中的选项就会在该对象中
    //点击某个规格的时候，添加至specificationItems
    $scope.selectSpecification=function(name,value){
        $scope.specificationItems[name]=value;
    }
    //判断某规格选项是否被用户选中，如果该规格在specificationItems，说明被选中
    $scope.isSelected=function(name,value){
        if($scope.specificationItems[name]==value){
            return true;
        }else{
            return false;
        }
    }
});