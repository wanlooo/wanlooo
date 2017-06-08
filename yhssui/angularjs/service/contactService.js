// 秒杀请求
app.factory('contactService', ['$http', function($http) {
	return {
		getContacts: function(){
			return $http.get(weburl+'/contract/getList');
			//return $http.get(uiurl+'/json/contact.json');
		}
	}; 
}]);