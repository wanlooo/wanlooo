// 账号绑定页面Ctrl
app.controller('huodongCtrl', function($scope, huodongService) {
	$scope.onIndex = 0 ;
	$scope.huodongs = [];
	huodongService.getHuodongs().success(function(response){
		if(response.success){
			$scope.huodongs = response.huodongs ;
			$scope.activeHuodong = $scope.huodongs[$scope.onIndex];
			console.log("finish");
		}
	});
 
    /*选项卡*/
function ChanceBtn(id,btnClass,contClass,fnEnd){
    var _this=this;

    this.k=0;
    
    $(id).find(btnClass).click(function(){
        _this.k=$(this).index();
        $(this).siblings().removeClass('on');
        $(this).addClass('on');
        $(this).parents(id).find(contClass).removeClass('on');
        $(this).parents(id).find(contClass).eq(_this.k).addClass('on');

        if(fnEnd)fnEnd();
    })
}
    //touchstart事件
    function touchSatrtFunc(evt) {}

    //touchmove事件，这个事件无法获取坐标
    function touchMoveFunc(evt) {}

    function touchEndFunc(evt) {
        setTimeout(function() {
            var _total_length = $(".on li").length;
            $(".on li").each(function() {
                var _this_zindex = $(this).css("z-index");
                if (_this_zindex != _total_length) {
                    console.log($(this));
                    $(this).children(".sccontent").find("div").slideUp();
                    $(this).children(".sccontent").find("p").slideUp();
                } else {
                    $(this).children(".sccontent").find("div").slideDown();
                    $(this).children(".sccontent").find("p").slideDown();
                }
            });
        }, 500);
    }
    //绑定事件
    function bindEvent() {
        document.addEventListener('touchstart', touchSatrtFunc, false);
        document.addEventListener('touchmove', touchMoveFunc, false);
        document.addEventListener('touchend', touchEndFunc, false);
    }
	$scope.initSlide = function (){
        var swiper = new Swiper('.nav .swiper-container', {
        	pagination: '.swiper-pagination',
        	slidesPerView: 4.5,
        	paginationClickable: true,
   	 	});  
        $(".linecontent:eq(0)").addClass("on").siblings().removeClass("on");
    };
    $scope.initHuodong = function (){ 
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
        ChanceBtn('.hotactivity','.nav .swiper-container .swiper-wrapper .swiper-slide','.linecontent');
        bindEvent();
    };
    $scope.changeOn = function(index){
    	$scope.onIndex = index ;
    	// $scope.activeHuodong = $scope.huodongs[index];
		console.log("finish2");
    	// $scope.initHuodong();
    }
    //捕获 emited event
	// $scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
	// 	console.log("init finish1"); 
 //        var swiper = new Swiper('.nav .swiper-container', {
 //        	pagination: '.swiper-pagination',
 //        	slidesPerView: 4.5,
 //        	paginationClickable: true,
 //   	 	}); 
 // 	});

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

