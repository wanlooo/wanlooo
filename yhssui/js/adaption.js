(function(doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function() {
            var screenwidth = win.screen.width;
            if (!screenwidth) return;
            //Android系统下的UC浏览器就是一个坑
            if (navigator.userAgent.indexOf("Android") > 0 && navigator.userAgent.indexOf("UCBrowser") > 0) {
                docEl.style.fontSize = 100 * (screenwidth / 1920) + 'px';
            } else {
                docEl.style.fontSize = 100 * (screenwidth / 750) + 'px';
            }
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);

})(document, window);
