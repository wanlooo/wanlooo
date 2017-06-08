// 账号绑定页面Ctrl
app.controller('contactCtrl', function($scope, contactService) {
	//$scope.onIndex = 0 ;
	$scope.contacts = [];
	contactService.getContacts().success(function(response){
		if(response.success){
			$scope.contacts = response.data ;
			
		}
	});
    $scope.layerFormEdit=function(contact){
       //console.log(contact);//{,,,}
       layer.open({
                type: 1,
                shade: false,
                title: "修改联系人", 
                content:getForm(contact),
                //content: 'abc/xxx.action?id='+id, 
                area:['300px','300px'],
                btn:["确定","取消"],
                btn1:function(){    //按钮一的回调
                    
                    
                    var contract=getFormContent(contact.id);
                   
                        $.ajax({
                            type:"post",
                            url:weburl+"/contract/update",
                            dataType:"json",
                            contentType:'application/json;charset=utf-8',
                            data:JSON.stringify(contract),// 
                            success: function(data) {
                              console.info(data);
                     
                                 //GetAddressInfo();//修改地址成功，重新加载数据页面
                                 window.location.reload();
                            }
                        }); 
                     
                }
            });
    }
    $scope.layerFormDel=function(contact){
        layer.confirm('确定要删除该联系人吗？', {
                btn: ['是的','我再想想'] //按钮
                    }, function(){

                        $.ajax({
                            type:"get",
                            url:weburl+"/contract/delete/"+contact.id,
                            dataType:"json",
                            contentType:'application/json;charset=utf-8',
                            success:function(data){
                                if(data.success==true){
                                    layer.msg('删除成功', {icon: 1});
                                     window.location.reload();
                                }
                            }
                        })

                        layer.msg('删除成功', {icon: 1});
                    }
                     );
    }
    $scope.layerFormAdd=function(){
        
                  
        layer.open({
                type: 1,
                shade: false,
                title: "新增联系人", 
                content:getForm(""),
                //content: 'abc/xxx.action?id='+id, 
                area:['300px','300px'],
                btn:["确定","取消"],
                btn1:function(){    //按钮一的回调
                    var contract= getFormContent('');

                        $.ajax({
                            type:"post",
                            url:weburl+"/contract/add",
                            dataType:"json",
                            contentType:'application/json;charset=utf-8',
                            data:JSON.stringify(contract),// 
                            success: function(data) {
                              
                                 //GetAddressInfo();//修改地址成功，重新加载数据页面
                                 window.location.reload();
                            }
                        }); 
                     
                },
                btn2:function(){    //按钮二的回调
                    
                }  
            });
    }
    function getForm(contact){
        if(typeof(contact)=="object"){

            var html = '<form id="formid"><div class="pdladd pdradd"><div class="lineoneadd"><div><span>姓名:</span><input type="" name="name" class="layer-input" value="'+contact.name+'"></div><div><span>电话:</span><input type="" name="phoneno" class="layer-input" value="'+contact.mobileNo+'"></div><div>证件类型:<div class="idtype" ><select name="idType" lay-verify="required" class="layer-select">'
            
            if(contact.idType=="ID"){
                html+='<option value="ID" selected>身份证</option><option value="PP">其他</option>';
            }else {
                html+='<option value="ID" >身份证</option><option selected value="PP">其他</option>';
            }   
            html+='</select></div></div><div>证件号码:<input type="" name="idNo" class="layer-input" value="'+contact.idNo+'"></div></div></div></form>'; 
        }
        else 
            var html = '<form id="formid"><div class="pdladd pdradd"><div class="lineoneadd"><div><span>姓名:</span><input type="" name="name" class="layer-input" value=""></div><div><span>电话:</span><input type="" name="phoneno" class="layer-input" value=""></div><div>证件类型:<div class="idtype" ><select name="idType" lay-verify="required" class="layer-select"><option value="ID">身份证</option><option value="PP">其他</option></select></div></div><div>证件号码:<input type="" name="idNo" class="layer-input" value=""></div></div></div></form>';
        return html;
    }
    function getFormContent(id){
        
        var contract = {name:'',mobileNo:'',idType:'',idNo:''};

        contract.name=$("#formid").find("input").eq(0).val();
        contract.mobileNo=$("#formid").find("input").eq(1).val();
        contract.idNo=$("#formid").find("input").eq(2).val();
        contract.idType=$("#formid").find("select").val();
        if(id!=undefined){
            contract.id=id;
        }
        return contract;
    }
    $scope.initContacts=function(){

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

