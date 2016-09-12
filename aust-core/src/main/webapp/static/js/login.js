//启动复选框
$(':checkbox').radiocheck();


//点击更换验证码方法
function changeUrl() {
    var code = $('#code-validate');
    var url = code.prop('src');
    url = url.substr(0,url.lastIndexOf('/')+1);
    url = url + (new Date()).valueOf();
    code.prop('src',url);
}

//提交验证
function checkForm(obj) {
    //用户名验证
    if (obj.username.value.length<3 || obj.username.value.length>15){
        to
        return false;
    }
    
    return true;
}
