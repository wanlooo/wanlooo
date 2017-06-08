app.factory('loginService', ['$http', function($http) {
	return {
		isLogin: function(){
			 return $http.get(weburl+'/bind/isLogin');
//			return $http.get(uiurl+'/json/huodong.json');
		},
		login:function(login){
			// return $http.get(weburl+'/huodong/getActiveDetail/'+id);
			return $http.post(weburl+'/bind/login',login);
		}
	};
}]);