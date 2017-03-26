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

var login = new Vue({
    el: '.index-one',
    data: {
        email: '',//邮箱
        password: '',//确认密码
        codeurl: projectName+'/codeValidate', //验证码地址
        submit: true,
        err_msg: ''
    },
    methods: {
        //点击更换验证码方法
        changeUrl: function () {
            this.codeurl = projectName+'/codeValidate?time'+new Date().getTime();
        },
        login:function () {
            var $loginForm = $('#loginForm');
            $.ajax({
                type: 'POST',
                url: $loginForm.prop('action'),
                data: $loginForm.serialize(),
                dataType: 'json',
                cache: false,
                beforeSend: function () {
                    login.submit = false;
                },
                success: function (result) {
                    login.submit = true;
                    if ($('#remmberMe').prop('checked')) {
                        addCookie('email', $('#email').val(), {expires: 7 * 24 * 60 * 60});
                    } else {
                        removeCookie('email');
                    }
                    if (result.status === 0) {
                        toastr.success('登录成功,正在跳转中...', 'SUCCESS', {progressBar: true});
                        setTimeout(function () {
                            location.href = result.data.referer;
                        }, 1500);
                    } else {
                        toastr.error(result.msg);
                        login.err_msg = result.msg;
                        login.changeUrl();
                    }
                }
            })
        }
    },
    computed: {
        emailValid: function () {
            var emailReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            if (this.email !== '' && !emailReg.test(this.email)) {
                return false;
            }
            return true;
        },
        passwordValid: function () {
            var reg = /^[@A-Za-z0-9!#$%^&*.~]{6,22}$/;
            if (this.password !== '' && !reg.test(this.password)) {
                return false;
            }
            return true;
        }
    }
});
//记住用户名
remmberMe();
function remmberMe() {
    var $remmberMe = $('#remmberMe');
    if (getCookie('email') !== null) {
        $remmberMe.prop('checked', true);
        login.email = getCookie('email');
        $('#password').focus();
    } else {
        $remmberMe.prop('checked', false);
        $('#email').focus();
    }
}

//键盘监听
$(document).keyup(function (event) {
    if (event.keyCode === 13 && login.emailValid && login.passwordValid
    && $('.code-input').val().length > 3) {
            login.login();
    }
});