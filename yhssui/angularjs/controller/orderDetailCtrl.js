// 地址页面Ctrl
app.controller('orderDetailCtrl', function($scope,$http) {
	if(request("orderNo")==''){
		alert("请正确进入该页面");
		return ;
	}    
	$scope.express = {};
	$scope.order = {};
	$scope.product = {};
	$http.get(weburl+'/order/getOrder/'+request("orderNo")).success(
		function(response){
			if(response.success){
				$scope.express = response.data.orderAddress ;

				$scope.order = response.data.porders[0] ;
				$scope.order.orderNo = response.data.orderNo ;
				$scope.order.totalAmount = response.data.amount ;
				$scope.order.created = moment($scope.order.created).format('YYYY-MM-DD HH:mm:ss');

				$scope.product = $scope.order.product ;
			}
		}
	);

	function countdown(endTime){
		var now = new Date();
		var end = new Date();

	}

	function request(param){
		var url = location.href;  
		var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");  
		var paraObj = {}  
		for (i=0; j=paraString[i]; i++){  
			paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);  
		}  
		var returnValue = paraObj[param.toLowerCase()];  
		if(typeof(returnValue)=="undefined"){  
			return "";  
		}else{  
			return returnValue; 
		}
	};
    
});


