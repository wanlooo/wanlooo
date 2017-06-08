// 地址页面Ctrl
app.controller('orderCtrl', function($scope,$http) {
	$scope.orders = [] ;
	$scope.waitPayOrder = 1 ;
	var orderList = [] ;
	$http.get(weburl+'/order/getList').success(
		function(response){
			if(response.success){
				orderList = response.data ;
				var waitPayOrder = 0 ;
				for(var i in orderList){
					if(orderList[i].status == 'WP'){
						waitPayOrder ++ ;
					}
				}
				$scope.waitPayOrder = waitPayOrder ;
				orderfilter("ALL");
			}
		}
	);   

	$scope.showAll = function(){
		orderfilter("ALL");
	}
	$scope.showWP = function(){
		orderfilter("WP");
	}
	$scope.toDetail = function(orderNo){
		window.location.href='order-detail-product.html?orderNo='+orderNo ;
	}
	/*筛选订单，显示特定条件订单*/
	function orderfilter(type){
		$scope.orders = [] ;
		for(var i in orderList){
			var o = orderList[i];
			if(type=='ALL' || (type == 'WP' && o.status == 'WP')){
				orderhandle(o);
			}
			
		}
	}
	function orderhandle(o){
		var order = {};
		if(o.type == 'PRODUCT'){//商品订单
			order = o.porders[0] ;
			order.orderNo = o.orderNo ;
			order.totalAmount = o.amount ;
			order.type = o.type ;
			order.status = o.status ;
		}
		$scope.orders.push(order);
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


