app.factory('orderService', ['$http', function($http) {
	return {
		pay: function(payParamInfo){
			 return $http.post(weburl+'/order/pay',payParamInfo);
		}
	};
}]);