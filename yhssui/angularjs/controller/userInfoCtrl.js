// 地址页面Ctrl
app.controller('userInfoCtrl', function($scope,$http) {
	$scope.user = {};
	$scope.init = function(){
		$http.get(weburl+'/user/getUserInfo').success(
			function(response){
				if(response.success){
					$scope.user = response.data ;
					sessionStorage.user = JSON.stringify(response.data);
				}
			}
		); 
	}
	$scope.userInfo = function(){
		if(sessionStorage.user == null || sessionStorage.user == undefined){
			alert("获取用户信息失败");
			return ;
		}
		$scope.user = JSON.parse(sessionStorage.user);
	} 
	$scope.showAllLevelRules = function(){
		//底部提示
		 /* layer.open({
		    content: '<img src="img/memberrules.png" style="width: 100%;" alt="会员规则表" />'
		    ,skin: 'footer'
		  });*/
		//提示框
		  layer.open({
			    content: '<img src="img/memberrules.png" style="width: 100%;" alt="会员规则表" />'
			    ,btn: '确定'
			    ,shadeClose: false
			  });
	} ;
    
});


