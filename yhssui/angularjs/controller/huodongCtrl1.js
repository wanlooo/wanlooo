// 账号绑定页面Ctrl
app.controller('huodongCtrl1', function($scope, huodongService) {
    $scope.huodong_001=[];
    $scope.huodong_002=[];
    $scope.huodong_003=[];
    $scope.huodong_004=[];
    $scope.huodong_005=[];
    $scope.huodong_006=[];
	huodongService.getHuodongs().success(function(response){
        if(response.success){
            initHuodong(response.huodongs);
        }
    });
    function initHuodong(huodongs){
        angular.forEach(huodongs,function(huodong,i){
            if(huodong.typeNo=='001'){
                $scope.huodong_001.push(huodong);
            }else if(huodong.typeNo=='002'){
                $scope.huodong_002.push(huodong);
            }else if(huodong.typeNo=='003'){
                $scope.huodong_003.push(huodong);
            }else if(huodong.typeNo=='004'){
                $scope.huodong_004.push(huodong);
            }else if(huodong.typeNo=='005'){
                $scope.huodong_005.push(huodong);
            }else if(huodong.typeNo=='006'){
                $scope.huodong_006.push(huodong);
            }
            console.log("finish1")
        });
    };
    
    $scope.initCarousel = function (){ 
        console.log("in");
        $('.carousel').carousel({
            itemWidth: 480,
            itemHeight: 390,
            distance: 0,
            selectedItemDistance: 35,
            selectedItemZoomFactor: 0.5,
            unselectedItemZoomFactor: 0.4,
            unselectedItemAlpha: 0.3,
            motionStartDistance: 140,
            topMargin: 30,
            gradientStartPoint: 0.36,
            gradientOverlayColor: "#ffffff",
            gradientOverlaySize: 190,
            selectByClick: true
        });
        $(".linecontent:eq(0)").addClass("on").siblings().removeClass("on");
        
    };

    // 获取url参数
	$scope.request = function(param){
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

