$(function(){
		function _fresh()
		{
		    var endtime=new Date("October 25, 2016 10:00:00");
		    var nowtime = new Date();
		    var leftsecond=parseInt((endtime.getTime()-nowtime.getTime())/1000);
		    if(leftsecond<0){leftsecond=0;}
		    __d=parseInt(leftsecond/3600/24);
		    __h=parseInt((leftsecond/3600)%24);
		    __m=parseInt((leftsecond/60)%60);
		    __s=parseInt(leftsecond%60);
		    document.getElementById("_lefttime").innerHTML=__h;
		    document.getElementById("_lefttime1").innerHTML=__m;
		    document.getElementById("_lefttime2").innerHTML=__s;
		}
		_fresh()
		setInterval(_fresh,1000);
})