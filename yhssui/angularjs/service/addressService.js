// 收货地址请求
app.factory('addressService', ['$http', function($http) {
	return {
		save: function(address){
			 return $http.post(weburl+'/address/save',address);
		},
		queryUser:function(userName){
			return $http.get(weburl+'/bind/queryUser'+userName);
		}
	};
}]);