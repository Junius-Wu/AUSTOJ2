//查看竞赛详情
function getContestDetail(contest_id, type) {
    var $contestBtn = $("#contestDetail");
    $contestBtn.unbind('click');
    $contestBtn.bind('click', {id: contest_id}, getContestCommit);
    if (type == "校内赛") {//校内赛,需要密码
        $("#myModal").modal("show");
    }else {
        var event = {data:{id:contest_id}};
        getContestCommit(event);
    }
}
//查看提交
function getContestCommit(event) {
    var id = event.data.id;
    $.ajax({
        type: 'POST',
        url: projectName + "/contest/" + id,
        data: $("#contestJudgeForm").serialize(),
        dataType: 'json',
        success: function (data) {
            if (consumeStatus(data)) {
                window.location.href = projectName+"/contest/"+id;
            }
        }
    });
}
