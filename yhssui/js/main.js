$(function() {
    /*****************日期切换*********************/
    (function() {
        $(".travel_date_list").find("li").click(function() {
            $(this).addClass("cur");
            $(this).siblings("li").removeClass("cur");
        })
        if ($(".travel_date_list").find("li").length > 5) {
            $(".right_btn").addClass("cur");
        } else {
            $(".right_btn").removeClass("cur");
        }
        if ($(".travel_date_list ul").length > 0) {
            if ($(".travel_date_list ul").offset().left < 0) {
                $(".left_btn").addClass("cur");
            } else {
                $(".left_btn").removeClass("cur");
            }
        }

        var maxn = Math.ceil($(".travel_date_list").find("li").length / 5) - 1;
        var n = maxn;
        $(".left_btn").click(function() {
            $(".right_btn").addClass("cur");
            n++;
            if (n <= maxn) {
                $(".travel_date_list ul").animate({
                    marginLeft: '+=14.4rem'
                }, 500);
            } else {
                n = maxn;
                $(".left_btn").removeClass("cur");
            }
        })

        $(".right_btn").click(function() {
            $(".left_btn").addClass("cur");
            n--;
            if (n >= 0) {
                $(".travel_date_list ul").animate({
                    marginLeft: '-=14.4rem'
                }, 500);
            } else {
                $(".right_btn").removeClass("cur");
                n = 0;
            }
        })
    })();
    /*****************tab*********************/
    $(".travel_tab_tit span").click(function() {
        $(this).addClass("cur");
        $(this).siblings().removeClass("cur");
        $(".travel_tab_con li").eq($(this).index()).show();
        $(".travel_tab_con li").eq($(this).index()).siblings().hide();
    });
    /*****************icon**************************/
    $(".order_pass_list_tit").children("span").click(function() {
        if ($(this).hasClass("down")) {
            $(this).removeClass("down");
            $(this).addClass("up");
            $(this).parent(".order_pass_list_tit").siblings(".order_pass_info").show();
        } else {
            $(this).removeClass("up");
            $(this).addClass("down");
            $(this).parent(".order_pass_list_tit").siblings(".order_pass_info").hide();
        };
    });
    /*****************pass type**************************/
    $(".tj_cardtype_pare").click(function() {
        $(".tj_layer_bg").show();
    });
    $(".tj_layer_close").add(".tj_layer_cancel").click(function() {
        $(".tj_layer_bg").hide();
    });
    $(".tj_layer_confirm").click(function() {
        $(".th_layer_cardtype input").each(function(index, element) {
            if ($(this).is(":checked") && $(this).parent("label").index() == 0) {
                $(".tj_cardtype").val("身份证");
            } else if ($(this).is(":checked") && $(this).parent("label").index() == 1) {
                $(".tj_cardtype").val("护照");
            }
        })
        $(".tj_layer_bg").hide();
    });

})