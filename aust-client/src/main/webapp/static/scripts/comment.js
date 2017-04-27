//得到详细评论
function getCommentDetail(rootId) {
    $.ajax({
        type: 'GET',
        url: projectName+'/comment/pro/detail/'+rootId,
        dataType:'html',
        success: function(data){
            $(".discuss-comment").remove();
            $("#"+rootId).append(data);
        },
        complete: function(XMLHttpRequest, textStatus) {
            var $replyBtn = $("#"+rootId+"form .btn-inverse");
            $replyBtn.unbind('click');
            $replyBtn.bind('click',{rootId:rootId},replyCommit);
        }
    });
}
//   点赞
function voteComment(rootId,status) {
    $.ajax({
        type: 'POST',
        url: projectName+'/comment/pro/vote/'+rootId,
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
        url: projectName+'/comment/pro/report/'+rootId,
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
                    url: projectName+'/comment/pro/del/'+id,
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
    var url = projectName+'/comment/pro/modify/'+id;
    var $comment = $("#comment-btn");
    $comment.removeClass('disabled');
    $comment.unbind('click');
    $comment.bind("click",{url:url,id:id},modifyCommit);
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
    var $comment = $("#comment-btn");
    $comment.removeClass('disabled');
    $comment.unbind('click');
    $comment.bind("click",addCommit);
    $modal.modal('show');
});

//添加提交
function addCommit(event) {
    $("#comment_form").submit();
}
//回复评论
function replyComment(rootId,friendId,friendName) {
    if (friendId && friendName && friendName != ""){
        var $replay = $("#"+rootId+"form span a");
        $replay.text(friendName);
        $replay.prop("href","hhhhhhhhhh");
        $replay.parent('span').removeClass("hidden");
        var $replyBtn = $("#"+rootId+"form .btn-inverse");
        $replyBtn.unbind('click');
        $replyBtn.bind('click',{rootId:rootId,friendId:friendId},replyCommit);
    }
}

//回复提交
function replyCommit(event) {
    $(this).addClass('disabled');
    var friendId = event.data.friendId;
    if (!friendId){
        friendId = 0;
    }
    var rootId = event.data.rootId;
    $.ajax({
        type: 'POST',
        url: projectName+"/comment/pro/reply/"+rootId,
        data:"friendId="+friendId+"&"+$("#"+rootId+"form form").serialize(),
        dataType:'json',
        success: function(data){
            if (consumeStatus(data)){
                toastr.success("评论成功","SUCCESS",{
                    progressBar:true,
                    positionClass: 'toast-top-right'
                });
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            $(this).removeClass('disabled');
            $(".reply-friend").addClass('hidden');
            getCommentDetail(rootId);
        }
    });
}