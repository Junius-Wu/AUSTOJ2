function voteArticle(id) {
    $.ajax({
        type: 'POST',
        url: projectName+'/article/vote/'+id,
        dataType:'json',
        success: function(result){
            if (consumeStatus(result)){
                var $span = $('#like'+id);
                if (result.data.art_status == 0){
                    $span.parent().removeClass('text-danger').addClass('text-success');
                }else {
                    $span.parent().removeClass('text-success').addClass('text-danger');
                }
                $span.text(result.data.count);
            }
        }
    });
}

$(function () {
    //代码高亮
    $('pre code').each(function(i, block) {
        hljs.highlightBlock(block);
    });
});