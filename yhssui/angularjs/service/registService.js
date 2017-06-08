// 秒杀请求
app.factory('registService', ['$http', function($http) {
	return {
		regist: function(regist){
			 return $http.post(weburl+'/bind/regist',regist);
//			return $http.get(uiurl+'/json/huodong.json');
		},
		checkUserName:function(userName){
			return $http.get(weburl+'/bind/checkUserName?userName='+userName);
		}
	};
}]);