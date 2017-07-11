// 账号绑定页面Ctrl
app.controller('miaoshaDetailCtrl', function($scope, miaoshaService) {
	$scope.miaoshaDetail = {
		intervalId:0,
		init:function(){
			//没有秒杀活动记录，获取活动id
			var id = $scope.request("id");
			if(id !="" && !isNaN(id)){
				// console.log(id);
				miaoshaService.getActive(id).success(
					function(response){
						if(response.success){
							$scope.active = response.active;
							console.log($scope.active);
							//初始化状态
							$scope.miaoshaPeriod = $scope.miaoshaDetail.getMiaoshaPeriod();
							$scope.miaoshaDetail.initStatus();
							console.log("no cache");
						}
					}
				);
				
			}else if(id == ""){
				if(sessionStorage.miaoshaPeriod != undefined && sessionStorage.miaoshaPeriod!=null){
					$scope.miaoshaPeriod = JSON.parse(sessionStorage.miaoshaPeriod);
					$scope.active = $scope.miaoshaPeriod.actives[0];

					//初始化状态
					$scope.miaoshaDetail.initStatus();
					console.log("cache");
					console.log($scope.active);
				}else{
					window.location.href = 'miaosha.html';
				}
			}else{
					window.location.href = 'miaosha.html';
			}
			
		},
		//根据当前时间获取所处的秒杀时段
		getMiaoshaPeriod:function(){
			// var now = new Date();
			var getHour = $scope.active.limit_time;
			var time_periods = window.miaoshaParam.time_periods ;
			for (var i = time_periods.length - 1; i >= 0; i--) {
				var time_period = time_periods[i]
				if(getHour >= time_period.period){
					return time_period ;
				}
			}
			//时间小于第一时段默认返回第一个时段
			return time_periods[0];
		},
		//根据当前时间再次初始化活动状态
		initStatus:function(){
			var now = new Date();
			if(now.getHours()==$scope.miaoshaPeriod.period && now.getMinutes()<window.miaoshaParam.countdownminute){//活动有效时间内
				$scope.miaoshaPeriod.status = 1 ;//活动进行中

				//倒计时启动
				$scope.miaoshaDetail.intervalId = window.setInterval($scope.miaoshaDetail.countdown, 1000);

			}else{
				//活动未进行，统一设为2
				$scope.miaoshaPeriod.status = 2 ;
				$scope.miaoshaDetail.intervalId = window.setInterval($scope.miaoshaDetail.countdown, 1000);
				//活动未进行，跳转活动首页
				// window.location.href = 'miaosha.html';
			}

		},
		//设置倒计时
		countdown:function(){
			var now = new Date();
			// var now = new Date(2016, 10, 10, 13, 59, 46, 0); 
			var end = new Date();
			if($scope.miaoshaPeriod.status != 1){//显示距开始倒计时
				var countdown = "距开始" ;
				if(now.getHours() < $scope.miaoshaPeriod.period){//时间还没过
					end.setHours($scope.miaoshaPeriod.period);
					end.setMinutes(0);
					end.setSeconds(0); 
					end.setMilliseconds(0);
				}else{//时间已过
					end.setDate(end.getDate()+1);
					end.setHours($scope.miaoshaPeriod.period);
					end.setMinutes(0);
					end.setSeconds(0); 
					end.setMilliseconds(0);
				}
			}else{//显示距结束倒计时
				var countdown = "距结束" ;
				end.setMinutes(window.miaoshaParam.countdownminute);
				end.setSeconds(0); 
				end.setMilliseconds(0);
			}

			var leftTime=end.getTime()-now.getTime(); 
    		var leftsecond = parseInt(leftTime/1000); 

    		var day=Math.floor(leftsecond/(60*60*24)); 
    		var hour=Math.floor((leftsecond-day*24*60*60)/3600); 
    		var minute=Math.floor((leftsecond-day*24*60*60-hour*3600)/60); 
    		var second=Math.floor(leftsecond-day*24*60*60-hour*3600-minute*60);

    		if (hour == 0 && minute == 0 && second == 0) {//倒计时为0时重新初始化时区
    			// window.location.reload();
    			window.location.href = 'miaosha.html';
    		}

    		hour = hour < 10 ? ("0"+hour):(""+hour) ; 
    		minute = minute < 10 ? ("0"+minute):(""+minute) ; 
    		second = second < 10 ? ("0"+second):(""+second) ;
    		var timeStr = '<div class="time"><samp>'+countdown+'</samp>'
    					 +'<font class="hour">'+hour+'</font><span>∶</span>'
    					 +'<font class="minute">'+minute+'</font><span>∶</span>'
    					 +'<font class="second">'+second+'</font></div>'
    		$("#timebox").html(timeStr);
    		// console.log($scope.miaosha.hour+":"+$scope.miaosha.minute+":"+$scope.miaosha.second);
		},
		toMiaoshaOrder:function(){
			localStorage.miaoshaDetail = JSON.stringify($scope.active);
			window.location.href = 'miaoshaOrder.html';
		}

	}
	$scope.tabShow = 0;
    $scope.tab0={'margin-left':'0.5rem'};
    $scope.tab1={'display':'none'};
    $scope.selectTab = function(index){
        $scope.tabShow = index;
        if(index==0){
            $scope.tab0={'margin-left':'0.5rem'};
            $scope.tab1={'display':'none'};
        }else if(index == 1){
            $scope.tab1={'margin-left':'2.5rem'};
            $scope.tab0={'display':'none'};
        }
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

