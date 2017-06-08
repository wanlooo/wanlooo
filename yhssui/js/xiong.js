/*倒计时*/
var dDay=0;
var dHour=0;
var dMinute=0;
var dSec=0;
var iMore=1373; // 需要倒计时的秒数.
function doubleZore(num){
	if(num<10){
		num='0'+num;
	}
	return num;
}
function countTime(){
	dDay=Math.floor(iMore/(24*60*60));
	dHour=Math.floor((iMore-dDay*24*60*60)/(60*60));
	dMinute=Math.floor((iMore-dDay*24*60*60-dHour*60*60)/60);
	dSec=Math.floor(iMore-dDay*24*60*60-dHour*60*60-dMinute*60);
	$('.time .hour').text(doubleZore(dHour));
	$('.time .minute').text(doubleZore(dMinute));
	$('.time .second').text(doubleZore(dSec));
	iMore--;
	if(iMore==0){
		iMore=900;
	}
}
setInterval(countTime,1000)
/*倒计时*/


/*图文手滑*/
$(function() {
	$('.carousel').carousel({
		itemWidth: 480,
		itemHeight: 378,
		distance: 0,
		selectedItemDistance: 35,
		selectedItemZoomFactor: 0.5,
		unselectedItemZoomFactor: 0.4,
		unselectedItemAlpha: 0.3,
		motionStartDistance: 140,
		topMargin: 30,
		gradientStartPoint: 0.36,
		gradientOverlayColor: "#ffffff",
		gradientOverlaySize: 190,
		selectByClick: true
	});
});
/*图文手滑*/

/*选项卡*/
function ChanceBtn(id,btnClass,contClass,fnEnd){
	var _this=this;

	this.k=0;
	
	$(id).find(btnClass).click(function(){
		_this.k=$(this).index();
		$(this).siblings().removeClass('on');
		$(this).addClass('on');
		$(this).parents(id).find(contClass).removeClass('on');
		$(this).parents(id).find(contClass).eq(_this.k).addClass('on');

		if(fnEnd)fnEnd();
	})
}


jQuery(document).ready(function($) {
	new ChanceBtn('.hotactivity','.nav .swiper-container .swiper-wrapper .swiper-slide','.linecontent');

});
/*选项卡*/



