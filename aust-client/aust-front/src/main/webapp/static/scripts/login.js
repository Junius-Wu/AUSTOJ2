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

var loginOrRegister = new Vue({
    el: '.index-one',
    data: {
        nickname: '',//昵称
        email: '',//邮箱
        password: '',//确认密码
        password2: '',//
        codeurl: projectName+'/codeValidate', //验证码地址
        submit: true,
        err_msg: ''
    },
    methods: {
        //检查邮箱是否可用
        checkEmail: function () {
            if (this.email != '') {
                $.ajax({
                    type: 'GET',
                    url: projectName + '/register/check',
                    dataType: 'json',
                    data: {email: this.email},
                    success: function (data) {
                        if (data.status) {
                            loginOrRegister.submit = true;
                            loginOrRegister.err_msg = '';
                        } else {
                            toastr.error(data.msg);
                            loginOrRegister.submit = false;
                            loginOrRegister.err_msg = '邮箱已存在';
                        }
                    }
                });
            }
        },
        //点击更换验证码方法
        changeUrl: function () {
            this.codeurl = projectName+'/codeValidate?time'+new Date().getTime();
        },
        //注册
        register:function () {
            var $registerForm = $('#registerForm');
            $.ajax({
                type: 'POST',
                url: $registerForm.prop('action'),
                data: $registerForm.serialize(),
                dataType: 'json',
                cache: false,
                beforeSend: function () {
                    this.submit = false;
                },
                success: function (result) {
                    this.submit = true;
                    if (!result.status) {
                        toastr.error(result.msg);
                        this.err_msg = result.msg;
                        this.changeUrl();
                    } else {
                        toastr.success('注册成功,正在跳转中...', 'SUCCESS', {progressBar: true});
                        setTimeout(function () {location.href = result.referer;}, 1500);
                    }
                }
            })
        }
    },
    computed: {
        nicknameValid: function () {
            if (this.nickname.length > 20) {
                toastr.remove();
                toastr.error("昵称过长");
                return false;
            }
            return true;
        },
        emailValid: function () {
            var emailReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            if (this.email != '' && !emailReg.test(this.email)) {
                toastr.remove();
                toastr.error("邮箱格式不正确");
                return false;
            }
            return true;
        },
        passwordValid: function () {
            var reg = /^[@A-Za-z0-9!#$%^&*.~]{6,22}$/;
            if (this.password != '' && !reg.test(this.password)) {
                toastr.remove();
                toastr.error("密码格式不正确");
                return false;
            }
            return true;
        },
        password2Valid: function () {
            if (this.password2 != '') {
                if (this.password != this.password2) {
                    toastr.remove();
                    toastr.error("两次密码不一致");
                    return false;
                }
            }
            return true;
        }
    }
});
//记住用户名
remmberMe();
function remmberMe() {
    var $remmberMe = $('#remmberMe');
    if (getCookie('memberUsername') != null) {
        $remmberMe.prop('checked', true);
        $('#username').val(getCookie('memberUsername'));
        $('#password').focus();
    } else {
        $remmberMe.prop('checked', false);
        $('#username').focus();
    }
}
//登录
$('#loginBtn').click(function () {
    var $loginForm = $('#loginForm');
    $.ajax({
        type: 'POST',
        url: $loginForm.prop('action'),
        data: $loginForm.serialize(),
        dataType: 'json',
        cache: false,
        beforeSend: function () {
            $('#loginBtn').prop('disabled', true);
        },
        success: function (result) {
            if ($('#remmberMe').prop('checked')) {
                addCookie('memberUsername', $('#username').val(), {expires: 7 * 24 * 60 * 60});
            } else {
                removeCookie('memberUsername');
            }
            $('#loginBtn').prop('disabled', false);
            if (result.status) {
                toastr.error(result.msg);
                changeUrl();
            } else {
                toastr.success('登录成功,正在跳转中...', 'SUCCESS', {progressBar: true});
                setTimeout(function () {
                    location.href = result.referer;
                }, 1500);
            }
        }
    })
});

//键盘监听
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        loginOrRegister.register();
    }
});