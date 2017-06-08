// 账号绑定页面Ctrl
app.controller('tuangouDetailCtrl', function($scope, tuangouService,$sce,$interval) {
    // var index = $scope.request("index");
    var id = request("id");
    if(id=='' ){
        alert('请正确进入该页面');
        return;
    }

    $scope.inTuangouStatus = {};
    $scope.restCount = 0;//剩余刀数
    $scope.tuangouStatus = [
        //status:0-不可用状态，1-初始可用状态
        {index:0,note:'砍价中',time:[],status:1,active:{}},
        {index:1,note:'销售中',time:[],status:1,active:{}},
        {index:2,note:'即将上线',time:[],status:1,active:{}}
    ];
    var tuanGouDetail = {};
    tuangouService.getTuangouDetail(id).success(function(response){
        if(response.success && response.tuangouActiveDetail!=null){
            var result = initStatusTime(response.tuangouActiveTime);
            if(result.success){
                tuanGouDetail = response.tuangouActiveDetail;
                initTuangouActive(response.tuangouActiveDetail);
                if($scope.inIndex==undefined){
                    alert('该活动时段设置有误，请联系网站管理人员！');
                    return ;
                }   
                $scope.inTuangouStatus = $scope.tuangouStatus[$scope.inIndex];
                $scope.intervalId=window.setInterval(initCountDown, 1000);
                // $scope.intervalId = $interval(initCountDown, 1000);
            }else{
                alert(result.reason);
            }
        }else{
            alert("无对应团购活动数据");
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
        // console.log(JSON.stringify($scope.tuangouStatus));
        angular.forEach($scope.tuangouStatus,function(tuangou,i){//格式化并校验$scope.tuangouStatus
            if(tuangou.time.length < 1){
                tuangou.status = 0 ;
            }else if(tuangou.index==0 && tuangou.time.length>1){
                // return {success:false,reason:'砍价时间设置有误，请联系网站人员！'};
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
        // console.log(JSON.stringify($scope.tuangouStatus));
        return {success:true};
    };
    function initTuangouActive(tuangouActiveDetail){//把团购数据放置到对应状态活动列表中
        var tuangouActive = tuangouActiveDetail;//直接取第一个活动
        //更新当前所处档位和价格
        if(tuangouActive.activeItem.length>0){
            tuangouActive.activeItem = sortByParam(tuangouActive.activeItem,"id");
            console.log(tuangouActive);

            var inner = false ;//还未找到落点
            angular.forEach(tuangouActive.activeItem,function(item,k){
                item.inner = 0 ;//未到该档
                item.obiStyle = {"background":"#C3C1C1","width":"0%","height":"0.13rem"};
                item.circleStyle = {"background-color":"#EB4A3F","margin-top":"-0.5rem"};

                if(!inner && parseInt(item.count)>parseInt(tuangouActive.joinCount)){
                    inner = true ;//找到落点
                    item.inner = 1 ;//落在该档
                    //计算百分比和提示偏移量
                    item.width = tuangouActive.joinCount/item.count;
                    item.restCount = parseInt(item.count)-parseInt(tuangouActive.joinCount) ;
                    $scope.restCount = item.restCount ;
                    item.obiStyle = {"background":"#E4C23C","width":item.width*100+"%","height":"0.13rem"};
                    item.circleStyle = {"background-color":"#E4C23C","margin-top":"-0.5rem"};
                    console.log("in");
                }else if(!inner){
                    item.inner = 2 ;//已过该档
                    item.obiStyle = {"background":"#EB4A3F","width":"100%","height":"0.13rem"};
                    item.circleStyle = {"background-color":"#EB4A3F","margin-top":"-0.5rem"};
                }
                if(parseInt(tuangouActive.joinCount)>=parseInt(item.count)){
                    tuangouActive.presentPrice = item.price ;
                }
            });
        }else{
            alert("团购档位设置有误，请及时联系网站管理人员！");
        }
        //放置团购活动数据

        $scope.inIndex;//所处状态下标
        angular.forEach($scope.tuangouStatus,function(tuangou,j){
            if(tuangou.status == 0){
                //什么都不做
            }
            angular.forEach(tuangou.time,function(t,l){
                if(tuangouActive.start>=t.bargainStart && tuangouActive.start<t.bargainEnd){
                    tuangou.active=tuangouActive;
                    $scope.inIndex = tuangou.index ;
                }
            });
        });
         // console.log(JSON.stringify($scope.tuangouStatus));
    }
    function initCountDown(){
        if($scope.inTuangouStatus.time.length<1){
            var html = '<div class="time"><samp>距开始</samp>'
                     +'<font class="hour">00</font><span>∶</span>'
                     +'<font class="minute">00</font><span>∶</span>'
                     +'<font class="second">00</font></div>'
            // $scope.countDown = html ;
            $("#timebox").html(html);
            // $scope.countDown = $sce.trustAsHtml(html);
            clearInterval($scope.intervalId);
            // $intreval.cancel($scope.intervalId)
            return ;
        }
        var now = new Date();
        // console.log('当前日期：'+now.format("yyyy-MM-dd HH:mm:ss"));
        // var now = new Date(2016, 10, 10, 13, 59, 46, 0); 
        var endBargin = '';
        if($scope.inTuangouStatus.index != 2){//显示距结束倒计时
            var countdown = "距结束" ;
            if($scope.inTuangouStatus.index == 0){//砍价中
                endBargin = $scope.inTuangouStatus.time[0].bargainEnd ;
            }else{//销售中
                //取最小销售截止日期
                angular.forEach($scope.inTuangouStatus.time,function(t,i){
                    if(endBargin == ''){
                        endBargin = t.saleEnd ;
                    }else if(endBargin>t.saleEnd){
                        endBargin = t.saleEnd ;
                    }
                });
            } 
        }else{//显示距开始倒计时
            var countdown = "距开始" ;
            endBargin = $scope.inTuangouStatus.time[0].bargainStart;
        }
        var end = moment(endBargin).toDate();
        // console.log('结束日期：'+end.format("yyyy-MM-dd HH:mm:ss"));

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
    $scope.bargain = function(){
        localStorage.tuanGouDetail = JSON.stringify(tuanGouDetail);
        window.location.href = 'tuangouOrder.html'
    }
    $scope.buy = function(){
        localStorage.tuanGouDetail = JSON.stringify(tuanGouDetail);
        window.location.href = 'tuangouOrder.html'
    }
    $scope.showTab = function(){
        // console.log("in");
        // $("#domMessage").show();
        $.showid('domMessage');
    }
    //获取星级循环
    $scope.getcountlist = function(count){
        var countlist=[];
        for (var i = 0; i < 3; i++) {
            countlist.push(i);
        }
        return countlist;
    }
    $scope.getrestcountlist = function(count){
        var totalcount = 5;
        var countlist=[];
        for (var i = 0; i < totalcount-3; i++) {
            countlist.push(i);
        }
        return countlist;
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

