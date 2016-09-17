//首页展示信息
$.ajax({
    //请求方式为get
    type: "GET",
    //json文件位置
    url: "../static/json/user.json",
    //返回数据格式为json
    dataType: "json",
    //请求成功完成后要执行的方法
    success: function (data) {
        //使用$.each方法遍历返回的数据date,插入到id为#result中
        $.each(data, function (i, item) {
            if (i < 3) {
                $(".showUser1").append(
                    '<div class="list-view-item">' +
                    '<img src="' + item.avatar + '" class="img-circle" width="55px" height="55px" style="max-width: 100%">' +
                    '<h5>' + item.nickname + '</h5>' +
                    '<p>' + item.honor + '</p></div>'
                );
            } else {
                $(".showUser2").append(
                    '<div class="list-view-item">' +
                    '<img src="' + item.avatar + '" class="img-circle" width="55px" height="55px" style="max-width: 100%">' +
                    '<h5>' + item.nickname + '</h5>' +
                    '<p>' + item.honor + '</p></div>'
                );
            }
        })
    }
});
//导航颜色监听
$(window).scroll(function () {
    var nav = $('#nav');
    var scrollTop = parseInt(document.body.scrollTop||document.documentElement.scrollTop);
    if(scrollTop > 0){
        nav.addClass("nav-backcolor");
    }else {
        nav.removeClass("nav-backcolor");
    }
});

//邮箱提交检查
function checkEmail(obj) {
    var value = obj.email.value;
    var emailReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (!emailReg.test(value)){
        toastr.error("填写邮箱不正确!");
        return false;
    }
    return true;
}