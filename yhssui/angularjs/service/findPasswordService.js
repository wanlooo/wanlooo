app.factory('findPasswordService', ['$http', function($http) {
	return {
		getUserSecurity: function(userName){
			 return $http.get(weburl+'/user/getSecurityList?userName='+userName);
//			return $http.get(uiurl+'/json/huodong.json');
		},
		checkUserSecurity:function(security){
			return $http.post(weburl+'/user/checkSecurity',security);
		},
		resetPassword:function(user){
			return $http.post(weburl+'/user/resetPassword',user);
		}
	};
}]);