window.onload=function()
{
	var outdiv = document.getElementsByClassName('outdiv')[0];
	var oul = outdiv.getElementsByTagName('ul')[0];
	var oli = oul.getElementsByTagName('li');
	oul.style.width = oli[0].offsetWidth*oli.length + 'px';
	oul.style.height = oli[0].offsetHeight + 'px';
	var owith = oli[0].offsetWidth;
	
	function bind(obj, ev, fn) { 
    if (obj.addEventListener) {
        obj.addEventListener(ev, fn, false);
    } else {
        obj.attachEvent('on' + ev, function() {
            fn.call(obj);
        });
    }
   }
	
	function getStyle(obj,attr){
		if(obj.currentStyle){
			return obj.currentStyle[attr];
		}
		else{
			return getComputedStyle(obj,false)[attr];
		}
	}
	
	oul.addEventListener("touchstart",slide,false);
	oul.addEventListener("touchmove",slideMove,false);
//	bind(document,"touchmove",function(ev){
//		ev.preventDefault();
//	});
	
	
	var ix=0;
	var istarTouchX = 0;
	var starX = 0;
	function slide(ev)
	   {
	   	 ev = ev.changedTouches[0];
	   	 istarTouchX = ev.pageX;
	   	 starX = ix;
	   	
	   }
	 
	function slideMove(ev)
	{
		ev = ev.changedTouches[0];

		var iMoveX = ev.pageX-istarTouchX;
		ix = istarTouchX + iMoveX;
		ix = parseInt(ix/2);
		var tag = parseInt(getStyle(oul,'left'));
		
		if(tag>0)
		{
			oul.style.left = 0;
			removeEventListener(oul,slideMove,false);
		}
		else{
			oul.style.left = iMoveX + 'px';
		}
		
		
	}
}
