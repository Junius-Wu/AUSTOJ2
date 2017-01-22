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

$(function () {
   //转换内容为markdown
    var $preview = $('.preview-panel');
    var content = $preview.text();
    md2Html(content,$preview,function (html) {
        $preview.prepend('<div class="widget"> <aside class="widget"> <h5><span>正文内容</span></h5> </aside> </div>');
        $('pre').addClass("hljs-dark");
        //代码高亮
        $('pre code').each(function(i, block) {
            hljs.highlightBlock(block);
        });
    });

});