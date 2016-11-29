function voteArticle(id) {
    $.ajax({
        type: 'POST',
        url: projectName+'/article/vote/'+id,
        dataType:'json',
        success: function(data){
            if (consumeStatus(data)){
                var $span = $('#like'+id);
                if (data.art_status == 0){
                    $span.parent().removeClass('text-danger').addClass('text-success');
                }else {
                    $span.parent().removeClass('text-success').addClass('text-danger');
                }
                $span.text(data.count);
            }
        }
    });
}