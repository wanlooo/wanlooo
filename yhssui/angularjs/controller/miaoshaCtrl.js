// 账号绑定页面Ctrl
app.controller('miaoshaCtrl', function($scope, miaoshaService) {
	//秒杀时段，可变，二期可考虑放到数据库维护
	//参数说明：status:0-即将开始，1-进行中，2-已结束    actives:对应时段的活动列表
	$scope.time_periods = window.miaoshaParam.time_periods;
	$scope.miaosha = {
		selectedindex:-1,
		actives:[], //前台显示列表
		init:function(){
			//当前所处时段判断，倒计时显示
			$scope.miaosha.initperiodstatus();
			// window.setInterval($scope.miaosha.countdown, 1000);

			//获取秒杀活动数据并显示
			miaoshaService.miaoshalist().success(function(response){
				// $scope.sleep(2000);//请求耗时测试
				//将返回数据封装到对应时段产品列表
				if(response.success && response.data.length>0){
					var list = response.data ;
					for (var i = 0; i < list.length; i++) {
						var active = list[i]; 
						for (var j = $scope.time_periods.length - 1; j >= 0; j--) {
							if(active.limitTime >= $scope.time_periods[j].period){
								$scope.time_periods[j].actives.push(active);
								break ;
							}
						}
					}
				}
				$scope.miaosha.activeinfo = $scope.time_periods[$scope.miaosha.selectedindex] ;
			});

		},
		//初始化时区状态,并显示倒计时
		intervalId:0,
		initperiodstatus:function(){
			$scope.miaosha.selectedindex = -1;//初始选择
			var now = new Date();
			// var now = new Date(2016, 10, 10, 13, 59, 45, 0); 
			// console.log(now.toLocaleString());
			for (var i = 0; i < $scope.time_periods.length; i++) {
				if(now.getHours() < $scope.time_periods[i].period){//当前时间小于本时段
					if($scope.miaosha.selectedindex == -1){//时区未选定，当前时间大于前面所有时区活动时间
						$scope.miaosha.selectedindex = i;
						$scope.time_periods[i].status = 0;
						$scope.time_periods[i].title = "即将开始";
					}else{//时区已选定，且处于本时区前面
						$scope.time_periods[i].status = 0;
						$scope.time_periods[i].title = "即将开始";
					}
				}
				//当前时间等于本时段且在活动期间内
				else if(now.getHours()==$scope.time_periods[i].period && now.getMinutes()< window.miaoshaParam.countdownminute){
					$scope.miaosha.selectedindex = i;
					$scope.time_periods[i].status = 1;
					$scope.time_periods[i].title = "进行中";
				}
				//当前时间大于本时区活动期，且为最后一个时区
				else if(i==$scope.time_periods.length-1 ){
					$scope.miaosha.selectedindex = 0;//设定当前选区为第一个
					$scope.time_periods[i].status = 2;
					$scope.time_periods[i].title = "已结束";
				}
				//当前时间大于本时区活动期
				else{
					$scope.time_periods[i].status = 2;
					$scope.time_periods[i].title = "已结束";
				}
			}

			//倒计时启动
			$scope.miaosha.intervalId = window.setInterval($scope.miaosha.countdown, 1000);
		},
		//设置倒计时
		countdown:function(){
			var now = new Date();
			// var now = new Date(2016, 10, 10, 13, 59, 46, 0); 
			var end = new Date();
			if($scope.time_periods[$scope.miaosha.selectedindex].status != 1){//显示距开始倒计时
				var countdown = "距开始" ;
				if(now.getHours() < $scope.time_periods[$scope.miaosha.selectedindex].period){//时间还没过
					end.setHours($scope.time_periods[$scope.miaosha.selectedindex].period);
					end.setMinutes(0);
					end.setSeconds(0); 
					end.setMilliseconds(0);
				}else{//时间已过
					end.setDate(end.getDate()+1);
					end.setHours($scope.time_periods[$scope.miaosha.selectedindex].period);
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
    			// clearInterval($scope.miaosha.intervalId);
    			// $scope.miaosha.initperiodstatus();
    			window.location.reload();
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
		//切换显示时段
		changeperiod:function(period){
			$scope.miaosha.selectedindex = period ;

			//倒计时启动
			// clearInterval($scope.miaosha.intervalId);//清除上一个计时器
			$scope.miaosha.intervalId = window.setInterval($scope.miaosha.countdown, 1000);

			//产品列表切换
			$scope.miaosha.activeinfo = $scope.time_periods[period] ;
			
		},
		toMiaoshaDetail:function(time_period,active){
			var actives = [];
			actives.push(active);
			time_period.actives = actives ;
			sessionStorage.miaoshaPeriod = JSON.stringify(time_period);
			// console.log(sessionStorage.miaoshaPeriod);
			window.location.href = 'miaoshaDetail.html';
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

	$scope.sleep = function(numberMillis){
		var now = new Date(); 
		var exitTime = now.getTime() + numberMillis; 
		while (true) { 
			now = new Date(); 
			if (now.getTime() > exitTime){
				return;
			} 
			 
		} 
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

