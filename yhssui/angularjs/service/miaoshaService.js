// 秒杀请求
app.factory('miaoshaService', ['$http', function($http) {
	return {
		miaoshalist: function(){
			return $http.get(weburl+'/miaosha/getList');
			// return $http.get(uiurl+'/json/miaosha.json');
		},
		getActive: function(id){
			return $http.get(weburl+'/miaosha/getDetail/'+id);
			// return $http.get(uiurl+'/json/miaoshaDetail.json');
		}
	};
}]);