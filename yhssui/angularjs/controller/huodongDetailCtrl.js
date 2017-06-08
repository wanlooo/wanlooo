// 账号绑定页面Ctrl
app.controller('huodongDetailCtrl', function($scope, huodongService) {
    var id = request("id");
    if(id==''){
        alert('请正确进入该页面');
        return;
    }
    $scope.activeDetail={};
    $scope.recommend = [];
	huodongService.getHuodongDetail(id).success(function(response){
        if(response.success){
            $scope.activeDetail = response.hot_active;
            $scope.recommend = response.recommend ;
            console.log("finish");
        }
    });
    $scope.initRecommend = function(){
        // console.log("in");
        var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        slidesPerView: 4.5,
        paginationClickable: true,
        });
    }
    $scope.toRecommendDetail = function(id){
        window.location.href = 'huodongdetail.html?id='+id ;
    }

    //获取星级循环
    $scope.getcountlist = function(count){
        var countlist=[];
        for (var i = 0; i < count; i++) {
            countlist.push(i);
        }
        return countlist;
    }
    $scope.getrestcountlist = function(count){
        var totalcount = 5;
        var countlist=[];
        for (var i = 0; i < totalcount-count; i++) {
            countlist.push(i);
        }
        return countlist;
    }

    // 获取url参数
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
app.directive('onFinishRender',['$timeout', '$parse', function ($timeout, $parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function () {
                    // scope.$emit('ngRepeatFinished'); //事件通知
                    var fun = scope.$eval(attr.onFinishRender);
                    if(fun && typeof(fun)=='function'){
                        fun();  //回调函数
                    }  
                });
            }
        }
    }
}]);

