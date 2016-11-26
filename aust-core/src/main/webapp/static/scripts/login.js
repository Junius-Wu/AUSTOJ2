//toastr设置
toastr.options = {
    'closeButton': true,
    'debug': false,
    'progressBar': false,
    'positionClass': 'toast-top-center',
    'showDuration': '400',
    'hideDuration': '1000',
    'timeOut': '4000',
    'extendedTimeOut': '1000',
    'showEasing': 'swing',
    'hideEasing': 'linear',
    'showMethod': 'fadeIn',
    'hideMethod': 'fadeOut'
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
//记住用户名
remmberMe();
function remmberMe() {
    var $remmberMe = $('#remmberMe');
    if (getCookie('memberUsername') != null){
        $remmberMe.prop('checked',true);
        $('#username').val(getCookie('memberUsername'));
        $('#password').focus();
    }else {
        $remmberMe.prop('checked',false);
        $('#username').focus();
    }
}
//注册 密码验证
function checkpwd(obj) {
    var $submitBtn = $('#registerBtn');
    var reg = /^[@A-Za-z0-9!#$%^&*.~]{6,22}$/;
    if (obj.value != '' && !reg.test(obj.value)){
        toastr.error('密码只能有数字,字母,特殊符号组成的6-23位序列');
        $submitBtn.addClass('disabled');
    }
    $submitBtn.removeClass('disabled');
}
//注册 邮箱验证
function checkemail(obj) {
    var $submitBtn = $('#registerBtn');
    var emailReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (obj.value != '' && !emailReg.test(obj.value) ){
        toastr.error('填写邮箱不正确!');
        $submitBtn.addClass('disabled');
    }
    if (obj.value != ''){
        $.ajax({
                   type: 'GET',
                   url: 'checkemail',
                   dataType:'json',
                   data:{email:obj.value},
                   success: function(data){
                       if (data.status == 0){
                           $submitBtn.removeClass('disabled');
                       }else {
                           toastr.error(data.msg);
                           $submitBtn.addClass('disabled');
                       }
                   }
               });
    }
}
//注册 确认密码验证
function checkpwd2(input){
    var $submitBtn = $('#registerBtn');
    var password = $('#password').val();
    if (password != input.value){
        toastr.error('两次输入密码不一致');
        $submitBtn.addClass('disabled');
    }else {
        $submitBtn.removeClass('disabled');
    }
}
//注册 用户名验证
function checkuser(obj){
    var $submitBtn = $('#registerBtn');
    if (obj.value != ''){
        $.ajax({
                   type: 'GET',
                   url: 'check/'+obj.value,
                   dataType:'json',
                   success: function(data){
                       if (data.status == 0){
                           $submitBtn.removeClass('disabled');
                       }else {
                           toastr.error(data.msg);
                           $submitBtn.addClass('disabled');

                       }
                   }
               });
    }
}

//注册
$('#registerBtn').click(function () {
    var $registerForm = $('#registerForm');
    $.ajax({
        type:'POST',
        url:$registerForm.prop('action'),
        data:$registerForm.serialize(),
        dataType:'json',
        cache:false,
        beforeSend:function () {
            $('#registerBtn').prop('disabled', true);
        },
        success:function (result) {
            $('#registerBtn').prop('disabled', false);
            if(result.status !=0){
               toastr.error(result.msg);
                changeUrl();
            }else {
                toastr.success('注册成功,正在跳转中...','SUCCESS',{progressBar: true});
                setTimeout(function () {
                    location.href = result.referer;
                },1500);
            }
        }
    })
});
//登录
$('#loginBtn').click(function () {
    var $loginForm = $('#loginForm');
    $.ajax({
        type:'POST',
        url:$loginForm.prop('action'),
        data:$loginForm.serialize(),
        dataType:'json',
        cache:false,
        beforeSend:function () {
            $('#loginBtn').prop('disabled', true);
        },
        success:function (result) {
            if ($('#remmberMe').prop('checked')){
                addCookie('memberUsername', $('#username').val(), {expires: 7 * 24 * 60 * 60});
            }else {
                removeCookie('memberUsername');
            }
            $('#loginBtn').prop('disabled', false);
            if(result.status !=0){
               toastr.error(result.msg);
                changeUrl();
            }else {
                toastr.success('登录成功,正在跳转中...','SUCCESS',{progressBar: true});
                setTimeout(function () {
                    location.href = result.referer;
                },1500);
            }
        }
    })
});


document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
        var $Sign = $("#signup");
        var $Register = $("#registerBtn");
        if ($Sign.length != 0){
            $Sign.click();
            return false;
        }
        if ($Register.length != 0){
            $Register.click();
            return false;
        }

    }
};
