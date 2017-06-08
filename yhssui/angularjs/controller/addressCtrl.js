// 地址页面Ctrl
app.controller('addressCtrl', function($scope, addressService) {
	$scope.user = {name:'',phoneNo:''}
	$scope.address={area:'',province:'',city:'',county:'',address:''}
	$scope.show = {usernamemsg:'',namemsg:''};
    $scope.proAddress = {
    		// 选择联系人
    		queryUser:function(){
    			debugger
        		addressService.queryUser($scope.user.userName).success(
        			function(response){
        				if(!response.success){
        					$scope.show.namemsg = response.reason ;
        				}else{
        					$scope.show.namemsg = 'ok';
        				}
        			});
        	},
        	
        	// 保存地址
        	save:function(){
        		debugger
                if(sessionStorage.tag == null || sessionStorage.tag === undefined){
                    alert("页面进入方式错误，请重新打开页面");
                    return ;
                }
        		var result = $scope.proAddress.validate();
        		if(!result.success){
        			alert(result.reason);
        			return ;
        		}
        		$scope.user.school = $scope.school ;
        		sessionStorage.user = JSON.stringify($scope.user);
        		
        	},
        	// 信息输入验证
    	validate:function(){
    		if($scope.user.name.trim()==''){
    			return {success:false,reason:'请输入收货人'};
    		}else if($scope.user.phoneNo.trim()==''){
    			return {success:false,reason:'请输入联系方式'};
    		}else if($scope.address.area==''){
    			return {success:false,reason:'请输入所在地区'};
    		}else if($scope.address.province.trim()=='' || $scope.address.city.trim()=='' || $scope.address.county.trim()=='' || $scope.address.address.trim()==''){
    			return {success:false,reason:'请输入详细地址'};
    		}
    		return {success:true}
    	},

    }
    
	
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


