// 秒杀请求
app.factory('tuangouService', ['$http', function($http) {
	return {
		getTuangouList: function(){
			return $http.get(weburl+'/tuangou/getList');
			// return $http.get(uiurl+'/json/tuangou.json');
		},
		getTuangouDetail:function(id){
			return $http.get(weburl+'/tuangou/getDetail/'+id);
			// return $http.get(uiurl+'/json/tuangouDetail.json');
		}
	};
}]);