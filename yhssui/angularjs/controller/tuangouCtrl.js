// 账号绑定页面Ctrl
app.controller('tuangouCtrl', function($scope, tuangouService,$sce,$interval) {
	$scope.selectIndex = 0;
    $scope.selectTuangouStatus = {};
    $scope.tuangouStatus = [
        //status:0-不可用状态，1-初始可用状态
        {index:0,note:'砍价中',time:[],status:1,actives:[]},
        {index:1,note:'销售中',time:[],status:1,actives:[]},
        {index:2,note:'即将上线',time:[],status:1,actives:[]}
    ];
    tuangouService.getTuangouList().success(function(response){
        if(response.success){
            var result = initStatusTime(response.tuangouActiveTime);
            if(result.success){
                initTuangouActives(response.tuangouActiveList);
                $scope.selectTuangouStatus = $scope.tuangouStatus[$scope.selectIndex];
                $scope.intervalId=window.setInterval(initCountDown, 1000);
                // $scope.intervalId = $interval(initCountDown, 1000);
                // initCountDown();
            }else{
                alert(result.reason);
            }
        }
    });
    function initStatusTime(tuangouActiveTime){
        var now = moment().format("YYYY-MM-DD HH:mm:ss");
        angular.forEach(tuangouActiveTime,function(time,i){
            if(now >= time.saleEnd){//销售截止时间已过，活动已结束时间
                // 什么都不做
            }else if(now >= time.bargainEnd){//砍价时间已过，销售中时间
                $scope.tuangouStatus[1].time.push(time);
            }else if(now >= time.bargainStart){//砍价中时间
                $scope.tuangouStatus[0].time.push(time);
            }else{//即将上线时间
                $scope.tuangouStatus[2].time.push(time);
            }
        });
        console.log($scope.tuangouStatus);
        angular.forEach($scope.tuangouStatus,function(tuangou,i){//格式化并校验$scope.tuangouStatus
            if(tuangou.time.length < 1){
                tuangou.status = 0 ;
            }else if(tuangou.index==0 && tuangou.time.length>1){
                return {success:false,reason:'砍价时间设置有误，请联系网站人员！'};
            }else if(tuangou.index==2 && tuangou.time.length>1){//获取最小的即将开始时间
                var tmpTime = [], minIndex = 0;
                angular.forEach(tuangou.time,function(t,j){
                    if(t.bargainStart<tuangou.time[minIndex].bargainStart){
                        minIndex = j ;
                    }
                });
                tmpTime.push(tuangou.time[minIndex]);
                tuangou.time = tmpTime ;
            }else if(tuangou.index==1){//验证销售中时间是否有冲突

            }
        });
        console.log($scope.tuangouStatus);
        return {success:true};
    };
    function initTuangouActives(tuangouActiveList){//把团购数据放置到对应状态活动列表中
         angular.forEach(tuangouActiveList,function(tuangouActive,i){

            //更新当前所处档位和价格
            if(tuangouActive.activeItem.length>0){
                //activeItem排序
                tuangouActive.activeItem = sortByParam(tuangouActive.activeItem,"id");
                console.log(tuangouActive);
                
                var inner = false ;//还未找到落点
                angular.forEach(tuangouActive.activeItem,function(item,k){
                    item.inner = 0 ;//未到该档
                    item.innerStyle = {"background":"#fdba04","width":"0"};
                    item.quanStyle = {"background":"#fdba04"};

                    if(!inner && parseInt(item.count)>parseInt(tuangouActive.joinCount)){
                        inner = true ;//找到落点
                        item.inner = 1 ;//落在该档
                        //计算百分比和提示偏移量
                        item.width = tuangouActive.joinCount/item.count;
                        item.restCount = item.count-tuangouActive.joinCount ;
                        item.fontStyle = {"margin-left":item.width*2.4+"rem"};
                        item.innerStyle = {"background":"#fdba04","width":item.width*100+"%"};
                        item.quanStyle = {"background":"#fdba04"};
                    }else if(!inner){
                        item.inner = 2 ;//已过该档
                        item.innerStyle = {"background":"#eb4a3f","width":"100%"};
                        item.quanStyle = {"background":"#eb4a3f"};
                    }
                    if(parseInt(tuangouActive.joinCount)>=parseInt(item.count)){
                        tuangouActive.presentPrice = item.price ;
                    }
                });
            }else{
                alert("团购档位设置有误，请及时联系网站管理人员！");
            }

            angular.forEach($scope.tuangouStatus,function(tuangou,j){
                if(tuangou.status == 0){
                    //什么都不做
                }
                angular.forEach(tuangou.time,function(t,l){
                    if(tuangouActive.start>=t.bargainStart && tuangouActive.start<t.bargainEnd){
                        tuangou.actives.push(tuangouActive);
                    }
                });
            });
         });
         console.log($scope.tuangouStatus);
    }

    function sortByParam(list,objParam){
        for(var i=0;i<list.length;i++){
            for (var j=i+1;j<list.length;j++) {
                if(list[i][objParam]>list[j][objParam]){
                    var obj = list[i];
                    list[i]=list[j];
                    list[j]=obj;
                }
            }
        }
        return list ;
    }
    function initCountDown(){
        if($scope.selectTuangouStatus.time.length<1){
            var html = '<div class="time"><samp>距开始</samp>'
                     +'<font class="hour">00</font><span>∶</span>'
                     +'<font class="minute">00</font><span>∶</span>'
                     +'<font class="second">00</font></div>';
            // $scope.countDown = html ;
            $("#timebox").html(html);
            // $scope.countDown = $sce.trustAsHtml(html);
            clearInterval($scope.intervalId);
            // $interval.cancel($scope.intervalId);
            return ;
        }
        var now = new Date();
        // console.log('当前日期：'+now.format("yyyy-MM-dd HH:mm:ss"));
        // var now = new Date(2016, 10, 10, 13, 59, 46, 0); 
        var endBargin = '';
        if($scope.selectTuangouStatus.index != 2){//显示距结束倒计时
            var countdown = "距结束" ;
            if($scope.selectTuangouStatus.index == 0){//砍价中
                endBargin = $scope.selectTuangouStatus.time[0].bargainEnd ;
            }else{//销售中
                //取最小销售截止日期
                angular.forEach($scope.selectTuangouStatus.time,function(t,i){
                    if(endBargin == ''){
                        endBargin = t.saleEnd ;
                    }else if(endBargin>t.saleEnd){
                        endBargin = t.saleEnd ;
                    }
                });
            } 
        }else{//显示距开始倒计时
            var countdown = "距开始" ;
            endBargin = $scope.selectTuangouStatus.time[0].bargainStart;
        }
        // var end = new Date(endBargin.replace('-','/'));
        var end = moment(endBargin).toDate();
        // alert('结束日期：'+end);

        var leftTime=end.getTime()-now.getTime(); 
        var leftsecond = parseInt(leftTime/1000); 

        var day=Math.floor(leftsecond/(60*60*24)); 
        var hour=Math.floor((leftsecond-day*24*60*60)/3600); 
        var minute=Math.floor((leftsecond-day*24*60*60-hour*3600)/60); 
        var second=Math.floor(leftsecond-day*24*60*60-hour*3600-minute*60);

        if (day==0 && hour==0 && minute==0 && second==0) {//倒计时为0时重新初始化时区
            // clearInterval($scope.miaosha.intervalId);
            // $scope.miaosha.initperiodstatus();
            window.location.reload();
        }
        var html = '<div class="time"><samp>'+countdown+'</samp>';
        if(day > 0){
            // day = day < 10 ? ("0"+day):(""+day) ; 
            html += '<font class="hour">'+day+'</font><span>&nbsp;天&nbsp;</span>';
        }
        hour = hour < 10 ? ("0"+hour):(""+hour) ; 
        minute = minute < 10 ? ("0"+minute):(""+minute) ; 
        second = second < 10 ? ("0"+second):(""+second) ;
        html += '<font class="hour">'+hour+'</font><span>∶</span>'
                +'<font class="minute">'+minute+'</font><span>∶</span>'
                +'<font class="second">'+second+'</font></div>'
        // $("#timebox").html(timeStr);
        // $scope.countDown = html ;
        $("#timebox").html(html);
        // $scope.countDown = $sce.trustAsHtml(html);
        // console.log("倒计时："+day+"天 "+hour+":"+minute+":"+second);
    }
    $scope.changeStatus = function(index){
        $scope.selectIndex = index;
        $scope.selectTuangouStatus = $scope.tuangouStatus[$scope.selectIndex];
        // $scope.intervalId = window.setInterval(initCountDown, 1000);
        $scope.intervalId = $interval(initCountDown, 1000);
        // initCountDown();
    }
    $scope.toDetail = function(id){
        window.location.href = 'tuangouDetails.html?id='+id;
    }
    $scope.showTab = function(){
        // console.log("in");
        // $("#domMessage").show();
        $.showid('domMessage');
    }
    //获取星级循环
    $scope.getcountlist = function(count){
        var countlist=[];
        for (var i = 0; i < 4; i++) {
            countlist.push(i);
        }
        return countlist;
    }
    $scope.getrestcountlist = function(count){
        var totalcount = 5;
        var countlist=[];
        for (var i = 0; i < totalcount-4; i++) {
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
app.directive('dyCompile',function($compile){
    return{
        replace:true,
        restrict:'EA',
        link:function(scope,elm,iAttrs){
            var DUMMY_SCOPE = {
                $destroy:angular.noop
            },root=elm,childScope,
            destroyChildScope=function(){
                (DUMMY_SCOPE || childScope).$destroy();
            };

            iAttrs.$observe('html',function(html){
                if(html){
                    destroyChildScope();
                    childScope = scope.$new(false);
                    var content = $compile(html)(childScope);
                    root.replaceWith(content);
                    root = content ;
                }
                scope.$on('$destroy',destroyChildScope);
            });
        }
    };
});

