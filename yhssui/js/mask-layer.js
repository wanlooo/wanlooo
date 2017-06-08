/**
 * 遮掩层效果
 */
$.showid=function(idname){
var isIE = (document.all) ? true : false;
var isIE6 = isIE && ([/MSIE (\d)\.0/i.exec(navigator.userAgent)][0][1] == 6);
var newbox=document.getElementById(idname);
newbox.style.zIndex="9999";
newbox.style.display="block"
newbox.style.position = !isIE6 ? "fixed" : "absolute";
newbox.style.top =newbox.style.left = "50%";
// newbox.style.top ="20%";
// newbox.style.left = "80%";
// newbox.style.marginTop = - newbox.offsetHeight / 2 + "px";
// newbox.style.marginLeft = - newbox.offsetWidth / 2 + "px";
newbox.style.marginTop = "-6rem";
newbox.style.marginLeft = "-0.5rem";  
var layer=document.createElement("div");
layer.id="layer";
layer.style.width=layer.style.height="100%";
layer.style.position= !isIE6 ? "fixed" : "absolute";
layer.style.top=layer.style.left=0;
layer.style.backgroundColor="#000";
layer.style.zIndex="9998";
layer.style.opacity="0.7";
document.body.appendChild(layer);
var sel=document.getElementsByTagName("select");
for(var i=0;i<sel.length;i++){        
sel[i].style.visibility="hidden";
}
function layer_iestyle(){      
layer.style.width=Math.max(document.documentElement.scrollWidth, document.documentElement.clientWidth)
+ "px";
layer.style.height= Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight) +
"px";
}
function newbox_iestyle(){      
newbox.style.marginTop = document.documentElement.scrollTop - newbox.offsetHeight / 2 + "px";
newbox.style.marginLeft = document.documentElement.scrollLeft - newbox.offsetWidth / 2 + "px";
// newbox.style.marginTop = "-10px";
// newbox.style.marginLeft = "-10px";
}
if(isIE){layer.style.filter ="alpha(opacity=60)";}
if(isIE6){  
layer_iestyle()
newbox_iestyle();
window.attachEvent("onscroll",function(){                              
newbox_iestyle();
})
window.attachEvent("onresize",layer_iestyle)          
}  
layer.onclick=function(e){e.preventDefault();newbox.style.display="none";layer.style.display="none";for(var i=0;i<sel.length;i++){
sel[i].style.visibility="visible";
}}
var layer1=document.getElementById("domMessage");
layer1.onclick=function(e){e.preventDefault();newbox.style.display="none";layer.style.display="none";for(var i=0;i<sel.length;i++){
	sel[i].style.visibility="visible";
	}}
}