// 账号绑定页面Ctrl
app.controller('registCtrl', function($scope, registService) {
	$scope.user = {userName:'',password:'',repassword:'',gender:'man',userType:'student',visiteCode:'',phoneNo:'',name:''}
	$scope.school = {schoolname:'',profession:''}
	$scope.show = {usernamemsg:'',repasswordmsg:'',passwordmsg:'',namemsg:''};
    $scope.proRegist = {
    	validate:function(){
    		if($scope.user.userName.trim()==''){
    			return {success:false,reason:'请输入用户名'};
    		}else if($scope.show.usernamemsg !='' && $scope.show.usernamemsg !='ok'){
    			return {success:false,reason:$scope.show.usernamemsg};
    		}else if($scope.user.password.trim()==''){
    			return {success:false,reason:'请输入登录密码'};
    		}else if($scope.show.passwordmsg !='' && $scope.show.passwordmsg !='ok'){
    			return {success:false,reason:$scope.show.passwordmsg};
    		}else if($scope.user.repassword.trim()==''){
    			return {success:false,reason:'请输入确认密码'};
    		}else if($scope.show.repasswordmsg !='' && $scope.show.repasswordmsg !='ok'){
    			return {success:false,reason:$scope.show.repasswordmsg};
    		}else if($scope.user.phoneNo.trim()==''){
    			return {success:false,reason:'请输入手机号码获取验证码'};
    		}else if($scope.user.name.trim()==''){
    			return {success:false,reason:'请输入用户姓名'};
    		}else if($scope.show.namemsg !='' && $scope.show.namemsg !='ok'){
    			return {success:false,reason:$scope.show.namemsg};
    		}else if($scope.school.schoolname.trim()==''){
    			return {success:false,reason:'请输入学校名称'};
    		}else if($scope.school.profession.trim()==''){
    			return {success:false,reason:'请输入所学专业'};
    		}
    		return {success:true}
    	},
    	toNext:function(){
            if(sessionStorage.tag == null || sessionStorage.tag === undefined){
                alert("页面进入方式错误，请重新打开页面");
                return ;
            }
    		var result = $scope.proRegist.validate();
    		if(!result.success){
    			alert(result.reason);
    			return ;
    		}
    		// console.log(JSON.stringify($scope.user));
    		// console.log(JSON.stringify($scope.school));
    		$scope.user.school = $scope.school ;
            delete $scope.user.repassword;//去除验证的密码
    		sessionStorage.user = JSON.stringify($scope.user);
    		window.location.href = 'setSecurity.html';
    	},
    	checkUserName:function(){
    		console.log($scope.user.userName);
    		registService.checkUserName($scope.user.userName).success(
    			function(response){
    				if(!response.success){
    					$scope.show.usernamemsg = "该用户名已存在" ;
    				}else{
    					$scope.show.usernamemsg = 'ok' ;
    				}
    			});
    	},
    	checkName:function(){
    		if($scope.user.name.trim()==''){
    			$scope.show.namemsg = "姓名不能为空";
    		}else if($scope.user.name.trim().length<2 || $scope.user.name.trim().length>5){
				$scope.show.namemsg = "姓名长度不正确";
    		}else{
    			$scope.show.namemsg = 'ok';
    		}
    	},
    	checkPassword:function(){
    		var reg = new RegExp(/^(?![^a-zA-Z]+$)(?!\D+$)/);
    		if($scope.user.password.trim()==''){
    			$scope.show.passwordmsg = "密码不能为空";
    		}else if($scope.user.password.trim().length<6 || $scope.user.password.trim().length>20){
				$scope.show.passwordmsg = "密码长度不正确";
    		}else if (!reg.test($scope.user.password)){
    			$scope.show.passwordmsg = "密码为字母+数字组合";
    		}else{
    			$scope.show.passwordmsg = 'ok';
    		}
    	},
    	checkRepassword:function(){
    		if($scope.user.password != $scope.user.repassword){
    			$scope.show.repasswordmsg = "两次密码不一致";
    		}else{
    			$scope.show.repasswordmsg = 'ok';
    		}
    	}
    }
    
    $scope.security = [{securityTitle:'最喜欢哪个城市？',securityAnswer:''},{securityTitle:'最喜欢哪个城市？',securityAnswer:''},{securityTitle:'最喜欢哪个城市？',securityAnswer:''}];
    $scope.regist={
    	validate:function(){
    		for (var i = 0; i<$scope.security.length; i++) {
    			if($scope.security[i].securityTitle== '' ||$scope.security[i].securityTitle.trim()== ''){
    				return{success:false,reason:'请输入密保问题'+(i+1)};
    			}else if($scope.security[i].securityAnswer == ''|| $scope.security[i].securityAnswer.trim()== ''){
    				return{success:false,reason:'请输入密保答案'+(i+1)};
    			}
    		}
    		return {success:true};
    	},
    	submit:function(){
            if(sessionStorage.tag == null || sessionStorage.tag === undefined){
                alert("页面进入方式错误，请重新打开页面");
                return ;
            }
            var tag = sessionStorage.tag ;
    		if(sessionStorage.user ==null || sessionStorage.user === undefined){
    			window.location.href = 'regist.html';
    		}
    		var ret = $scope.regist.validate();
    		if(!ret.success){
    			alert(ret.reason);
    			return ;
    		}
    		var user = JSON.parse(sessionStorage.user);
    		user.security = $scope.security ;
    		// console.log(user);
            var post = {user:user}
            console.log(JSON.stringify(post));
    		registService.regist(post).success(function(response){
    			if(response.success){
                    window.location.href = window.nextPage[tag] ;
    			}else{
    				alert(response.reason);
    			}
    		});

    	}
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


