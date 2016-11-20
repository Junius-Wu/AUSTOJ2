//得到详细评论
function getCommentDetail(rootId) {
    $.ajax({
        type: 'GET',
        url: projectName+'/commont/pro/detail/'+rootId,
        dataType:'html',
        success: function(data){
            $(".discuss-commont").remove();
            $("#"+rootId).append(data);
        }
    });
}
//   点赞
function voteComment(rootId,status) {
    $.ajax({
        type: 'POST',
        url: projectName+'/commont/pro/vote/'+rootId,
        data:{status:status},
        dataType:'json',
        success: function(data){
            if (consumeStatus(data)){
                $("span[class="+rootId+"]").text(data.count);
            }
        }
    });
}
//举报
function reportComment(rootId) {
    $.ajax({
        type: 'POST',
        url: projectName+'/commont/pro/report/'+rootId,
        data:{status:status},
        dataType:'json',
        success: function(data){
            if (consumeStatus(data)){
                toastr.success("举报成功,管理员审核后会处理该评论","SUCCESS",{
                    progressBar:true,
                    positionClass: 'toast-top-right'
                })
            }
        }
    });
}
//删除评论
function deleteComment(id) {
    $.confirm({
        title: 'Confirm!',
        content: '确定要删除?',
        buttons: {
            confirm: function () {
                $.ajax({
                    type: 'POST',
                    url: projectName+'/commont/pro/del/'+id,
                    dataType:'json',
                    success: function(data){
                        if (consumeStatus(data)){
                            $('#'+id+'del').remove();
                            toastr.success("删除成功","SUCCESS",{
                                progressBar:true,
                                positionClass: 'toast-top-right'
                            })
                        }
                    }
                });
            },
            cancel: function () {
            }
        }
    });
}

//修改评论
function modifyComment(id) {
    var $modal = $("#myModal");
    if ($modal.length == 0){//不存在
        var data = {status:12};
        return consumeStatus(data);
    }
    var $modify = $("#"+id+"content");
    $("textarea[name='content']").html($modify.html());
    var url = projectName+'/commont/pro/modify/'+id;
    var $comment = $("#comment-btn");
    $comment.removeClass('disabled');
    $comment.unbind('click');
    $comment.on("click",{url:url,id:id},modifyCommit);
    $modal.modal('show');
}
//修改提交
function modifyCommit(event) {
    $("#comment-btn").addClass('disabled');
    var url = event.data.url;
    var id = event.data.id;
    $.ajax({
        type: 'POST',
        url: url,
        data:$("#comment_form").serialize(),
        dataType:'json',
        success: function(data){
            if (consumeStatus(data)){
                toastr.success("修改成功","SUCCESS",{
                    progressBar:true,
                    positionClass: 'toast-top-right'
                });
                var $modify = $("#"+id+"content");
                $modify.html(data.content);
                $("#myModal").modal('hide');
            }
        }
    });
}

//暂时性解决th:utext溢出bug
$(function () {
    $(".discuss-content>p").each(function(i){
        $(this).html($(this).text());
    });
});

//发表对题目评论
$("#share_btn").on('click',function () {
    var $modal = $("#myModal");
    if ($modal.length == 0){//不存在
        var data = {status:12};
        return consumeStatus(data);
    }
    $("textarea[name='content']").html(" ");
    var url = projectName+'/commont/pro/add/'+$('#share_btn').prop('title');
    var $comment = $("#comment-btn");
    $comment.removeClass('disabled');
    $comment.unbind('click');
    $comment.on("click",addCommit);
    $modal.modal('show');
});

//添加提交
function addCommit(event) {
    $("#comment_form").submit();
}