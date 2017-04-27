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

var Register =
    new Vue({
              el: '.index-one',
              data: {
                nickname: '',//昵称
                email: '',//邮箱
                password: '',//确认密码
                password2: '',//
                codeurl: projectName + '/codeValidate', //验证码地址
                submit: true,
                err_msg: ''
              },
              methods: {
                //检查邮箱是否可用
                checkEmail: function () {
                  if (this.email !== '') {
                    $.ajax({
                             type: 'GET',
                             url: projectName + '/register/check',
                             dataType: 'json',
                             data: {email: this.email},
                             success: function (data) {
                               if (data.status === 0) {
                                 Register.submit = true;
                                 Register.err_msg = '';
                               } else {
                                 toastr.error(data.msg);
                                 Register.submit = false;
                                 Register.err_msg = '邮箱已存在';
                               }
                             }
                           });
                  }
                },
                //点击更换验证码方法
                changeUrl: function () {
                  this.codeurl =
                      projectName + '/codeValidate?time' + new Date().getTime();
                },
                //注册
                register: function () {
                  var $registerForm = $('#registerForm');
                  $.ajax({
                           type: 'POST',
                           url: $registerForm.prop('action'),
                           data: $registerForm.serialize(),
                           dataType: 'json',
                           cache: false,
                           beforeSend: function () {
                             Register.submit = false;
                           },
                           success: function (result) {
                             Register.submit = true;
                             if (result.status === 0) {
                               toastr.success('注册成功,正在跳转中...', 'SUCCESS',
                                              {progressBar: true});
                               setTimeout(function () {
                                 location.href = '/login';
                               }, 1500);
                             } else {
                               toastr.error(result.msg);
                               Register.err_msg = result.msg;
                               Register.changeUrl();
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
                },
                password2Valid: function () {
                  if (this.password2 !== '') {
                    if (this.password !== this.password2) {
                      return false;
                    }
                  }
                  return true;
                }
              }
            });

//键盘监听
$(document).keyup(function (event) {
  if (event.keyCode === 13 && Register.nicknameValid && Register.emailValid
      && Register.passwordValid && Register.password2Valid && $('.code-input').val().length > 3) {
    Register.register();
  }
});