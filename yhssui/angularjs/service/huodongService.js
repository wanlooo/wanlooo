// 秒杀请求
app.factory('huodongService', ['$http', function($http) {
	return {
		getHuodongs: function(){
			// return $http.get(weburl+'/huodong/getHuodongs');
			return $http.get(uiurl+'/json/huodong.json');
		},
		getHuodongDetail:function(id){
			// return $http.get(weburl+'/huodong/getActiveDetail/'+id);
			return $http.get(uiurl+'/json/huodongDetail.json');
		}
	};
}]);