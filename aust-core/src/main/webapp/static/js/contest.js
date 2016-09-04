$(function () {
    $(".contesttitle").click(function () {
        if($("#session").val() == 0){
            alert("请登陆后再操作");
            return false;
        }
        var url = this.href;
        var index = this.href.split("/").length-1;
        var contest = this.href.split("/")[index-1];
        var type = this.href.split("/")[index];
        $.ajax({
            url:this.href,
            type:'post',
            dataType:'json',
            success:function (data) {
                if(data.error){
                    window.location.href = '/404';
                }
                if(data.time == 1){
                    alert("未到比赛时间,请稍后再打开");
                }
                if(data.public){//公开的比赛
                    window.location.href = url;
                }
                if(data.private){//私有的比赛(也就是设置了密码的比赛)
                    $("#contest_id").val(contest);
                    $("#type").val(type);
                    $('#myModal').modal('show');
                }

            }
        });
        return false;
    })
});

$(function () {
   $("#contestComit").click(function () {
       $.ajax({
           url:'contest/piv',
           type:'post',
           data:$("#contestJudgeForm").serialize(),
           dataType:'json',
           success:function (data) {
               if(data.error){
                   window.location.href = '/404';
               }
               if(data.time == 1){
                   alert("未到比赛时间,请稍后再打开");
               }
               if(data.result){
                   window.location.href = '/contest/'+$("#contest_id").val()+'/'+$("#type").val();
               }else {
                   alert("密码错误");
               }
           }
       })
   })

});