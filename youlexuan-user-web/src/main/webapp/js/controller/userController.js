 //用户表控制层 
app.controller('userController' ,function($scope,$controller   ,userService) {

	$controller('baseController', {$scope: $scope});//继承

//发送验证码
	$scope.sendCode = function () {
//判断手机号码是否为空
		if ($scope.entity.phone == null || $scope.entity.phone == "") {
			alert("请输入手机号码");
			return;
		}
		userService.sendCode($scope.entity.phone).success(
			function (response) {
				alert(response.message);
			}
		);
	}

	$scope.entity = {}; //需要定义，要不然判断用户为空不能正常执行
//用户注册方法
	$scope.reg = function () {
		//判断用户名是否为空
		if ($scope.entity.username == '' || $scope.entity.username == null) {
			alert("请输入要注册的用户名");
			return;
		}
		//判断用户输入密码和确认密码是否一致
		if ($scope.entity.password != $scope.password) {

			alert("对不起两次输入的密码不一致");
			return;
		}
		userService.add($scope.entity).success(function (response) {
			if (response.success) {
				alert("恭喜你注册成功");
			} else {
				alert(response.message);
			}
		})
	}


	//读取列表数据绑定到表单中
	$scope.findAll = function () {
		userService.findAll().success(
			function (response) {
				$scope.list = response;
			}
		);
	}

	//分页
	$scope.findPage = function (page, rows) {
		userService.findPage(page, rows).success(
			function (response) {
				$scope.list = response.rows;
				$scope.paginationConf.totalItems = response.total;//更新总记录数
			}
		);
	}

	//查询实体 
	$scope.findOne = function (id) {
		userService.findOne(id).success(
			function (response) {
				$scope.entity = response;
			}
		);
	}


	//保存
	$scope.save = function () {
		userService.add($scope.entity, $scope.smscode).success(
			function (response) {
				alert(response.message);
			}
		);
	}


	//批量删除
	$scope.dele = function () {
		//获取选中的复选框			
		userService.dele($scope.selectIds).success(
			function (response) {
				if (response.success) {
					$scope.reloadList();//刷新列表
					$scope.selectIds = [];
				}
			}
		);
	}

	$scope.searchEntity = {};//定义搜索对象

	//搜索
	$scope.search = function (page, rows) {
		userService.search(page, rows, $scope.searchEntity).success(
			function (response) {
				$scope.list = response.rows;
				$scope.paginationConf.totalItems = response.total;//更新总记录数
			}
		);
	}

});