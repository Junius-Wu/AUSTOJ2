//toastr设置
toastr.options = {
    "closeButton": true,
    "debug": false,
    "progressBar": false,
    "positionClass": "toast-top-center",
    "showDuration": "400",
    "hideDuration": "1000",
    "timeOut": "4000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};

//启动复选框
$(':checkbox').radiocheck();


//点击更换验证码方法
function changeUrl() {
    var code = $('#code-validate');
    console.log(code);
    var url = code.prop('src');
    url = url.substr(0,url.lastIndexOf('=')+1);
    url = url + (new Date()).valueOf();
    code.prop('src',url);
}

//登录
$("#loginBtn").click(function () {
    var $loginForm = $("#loginForm");
    $.ajax({
        type:"POST",
        url:$loginForm.prop('action'),
        data:$loginForm.serialize(),
        dataType:"json",
        cache:false,
        beforeSend:function () {
            $("#loginBtn").prop("disabled", true);
        },
        success:function (result) {
            if ($("#remmberMe").prop("checked")){
                addCookie("memberUsername", $("#username").val(), {expires: 7 * 24 * 60 * 60});
            }else {
                removeCookie("memberUsername");
            }
            $("#loginBtn").prop("disabled", false);
            if(result.status !=0){
               toastr.error(result.msg);
                changeUrl();
            }else {
                location.href = result.referer;
            }
        }
    })
});

//提交验证
function checkForm(obj) {
    var usernameReg = /^[a-zA-Z0-9_]{3,16}$/;
    //用户名验证
    if (!obj.username.value.test(usernameReg)){
        toastr.error("用户名只允许数字字母下划线,且3-16个字符");
        return false;
    }
    
    return true;
}
// 添加Cookie
function addCookie(name, value, options) {
    if (arguments.length > 1 && name != null) {
        if (options == null) {
            options = {};
        }
        if (value == null) {
            options.expires = -1;
        }
        if (typeof options.expires == "number") {
            var time = options.expires;
            var expires = options.expires = new Date();
            expires.setTime(expires.getTime() + time * 1000);
        }
        if (options.path == null) {
            options.path = "/";
        }
        if (options.domain == null) {
            options.domain = "";
        }
        document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires != null ? "; expires=" + options.expires.toUTCString() : "") + (options.path != "" ? "; path=" + options.path : "") + (options.domain != "" ? "; domain=" + options.domain : "") + (options.secure != null ? "; secure" : "");
    }
}

// 获取Cookie
function getCookie(name) {
    if (name != null) {
        var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
        return value ? decodeURIComponent(value[1]) : null;
    }
}

// 移除Cookie
function removeCookie(name, options) {
    addCookie(name, null, options);
}