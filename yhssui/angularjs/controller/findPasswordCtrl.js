// 账号绑定页面Ctrl
app.controller('findPasswordCtrl', function($scope, findPasswordService) {
    $scope.userName = '' ;
    $scope.showSecurity = false ;
    $scope.msg = '';
    $scope.securityList = [];
    $scope.getSecurityList = function(){
        $scope.showSecurity = false ;
        findPasswordService.getUserSecurity($scope.userName).success(function(response){
            if(response.success){
                $scope.securityList = [];
                var securityTitles = response.securityTitles ;
                angular.forEach(securityTitles,function(title){
                    var security ={securityTitle:title,securityAnswer:''}
                    $scope.securityList.push(security);
                });
                $scope.showSecurity = true ;
                $scope.msg = ''
            }else{
                $scope.msg = response.reason;
            }
        });
    }
    $scope.submit = function(){
        var result = validateSecurity();
        if(!result.success){
            alert(result.reason);
            return ;
        }
        var post = {
            userName:$scope.userName,
            security:$scope.securityList
        }
        findPasswordService.checkUserSecurity(post).success(function(response){
            if(response.success){
                sessionStorage.userName = $scope.userName ;
                window.location.href = 'resetPassword.html';
            }else{
                alert(response.reason);
            }
        });

    }

    function validateSecurity(){
        if(!$scope.showSecurity){
            return{success:false,reason:'请输入有效的用户名'}
        }else if($scope.securityList.length != 3){
            return{success:false,reason:'密保设置有问题，请及时联系管理人员'}
        }
        angular.forEach($scope.securityList,function(security,i){
            if(security.securityTitles==''){
                return{success:false,reason:'密保问题'+i+'不能为空'}
            }else if(security.securityAnswer==''||security.securityAnswer.trim()==''){
                return{success:false,reason:'密保答案'+i+'不能为空'}
            }
        });
        return {success:true};
    }

    $scope.newPassword = '';
    $scope.newRepassword = '';
    $scope.resetPassword = function(){
        var result = validatePassword();
        if(!result.success){
            alert(result.reason);
            return ;
        }
        var user = {
            userName:sessionStorage.userName,
            password:$scope.newPassword
        }
        findPasswordService.resetPassword(user).success(function(response){
            if(response.success){
                window.location.href = 'login.html?tag='+sessionStorage.tag;
            }else{
                alert(response.reason);
            }
        });

    }
    function validatePassword(){
        if(sessionStorage.tag == null || sessionStorage.tag === undefined){
            return{success:false,reason:'页面进入方式错误，请重新打开页面'}
        }
        if(sessionStorage.userName == null || sessionStorage.userName === undefined){
            return{success:false,reason:'页面进入方式错误，请重新打开页面'}
        }
        var reg = new RegExp(/^(?![^a-zA-Z]+$)(?!\D+$)/);
        if($scope.newPassword.trim()==''){
            return{success:false,reason:'密码不能为空'}
        }else if($scope.newPassword.trim().length<6 || $scope.newPassword.trim().length>20){
            return{success:false,reason:'密码长度为6-20位'}
        }else if (!reg.test($scope.newPassword)){
            return{success:false,reason:'密码为字母+数字组合'}
        }else if($scope.newPassword != $scope.newRepassword){
            return{success:false,reason:'两次密码不一致'}
        }
        return {success:true};
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


