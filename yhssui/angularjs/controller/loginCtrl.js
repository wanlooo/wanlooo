// 账号绑定页面Ctrl
app.controller('loginCtrl', function($scope, loginService) {
    var tag = request("tag");
    if(tag != ''){
        sessionStorage.tag = tag ;
    }
    $scope.user = {loginName:'',password:'' };
    $scope.login = {
        validate : function(){
            if(tag == ''){
                return {success:false,reason:'请正确进入该页面'};
            }else if($scope.user.loginName.trim() == ''){
                return {success:false,reason:'请输入用户名'};
            }else if($scope.user.password.trim() == ''){
                return {success:false,reason:'请输入密码'};
            }
            return{success:true}
        },
        toLogin : function(){
            var ret = $scope.login.validate();
            if(!ret.success){
                alert(ret.reason);
                return ;
            }
            loginService.login($scope.user).success(function(response){
                if(response.success){
                    window.location.href = window.nextPage[tag] ;
                }else{
                    alert(response.reason);
                }
            });
        }
    }

    $scope.toRegist = function(){
        if(tag == ''){
            alert("请正确进入该页面");
            return ;
        }
        window.location.href = 'regist.html';
    }

    $scope.findPassword = function(){
        if(tag == ''){
            alert("请正确进入该页面");
            return ;
        }
        window.location.href = 'findPassword.html';
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


