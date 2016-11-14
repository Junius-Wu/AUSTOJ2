//start表格数据获取
setTable($('#start-table'),'problem/findByStage/1',15);
//practice表格数据获取
setTable($('#practice-table'),'problem/findByStage/1',15);
//master表格数据获取
setTable($('#master-table'),'problem/findByStage/1',15);
//获取用户排名
setTableClient($("#rank-table"),"static/json/rank.json",20);

//获取指定目录下的题目
// var cateid = $('#cateid').val();
// setTable($('#cate-table'),'/problem/findCateProblem/'+cateid);


//表格数据获取
function setTable(obj,url,pageSize) {
  obj.bootstrapTable({
    url: url,//这里配置请求链接
    method: 'get',
    cache: true,					   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,				   //是否显示分页（*）
    queryParams: queryParams,//传递参数（*）
    sidePagination: 'server',		   //分页方式：client客户端分页，server服务端分页（*）
    search: !0,
    striped: true,
    showRefresh: !0,
    pageNumber:1,					   //初始化加载第一页，默认第一页
    pageSize:pageSize,
    pageList:[15, 25, 50, 100],
    showColumns: !0,
    iconSize: 'outline',
    iconsPrefix:'fa',   //图标前缀
    icons: {refresh: 'fa-refresh', columns: 'fa-th-list'},
    undefinedText:'-_-',
    uniqueId: 'id'
  });
}
//表格数据获取,获取本地json
function setTableClient(obj,url,pageSize) {
  obj.bootstrapTable({
    url: url,//这里配置请求链接
    method: 'get',
    cache: true,					   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,				   //是否显示分页（*）
    queryParams: queryParams,//传递参数（*）
    sidePagination: 'client',		   //分页方式：client客户端分页，server服务端分页（*）
    search: !0,
    striped: true,
    showRefresh: !0,
    pageNumber:1,					   //初始化加载第一页，默认第一页
    pageSize:pageSize,
    pageList:[20, 30, 50, 100],
    showColumns: !0,
    iconSize: 'outline',
    iconsPrefix:'fa',   //图标前缀
    icons: {refresh: 'fa-refresh', columns: 'fa-th-list'},
    undefinedText:'-_-',
    uniqueId: 'id'
  });
}


function queryParams(params) {
  return {
    limit:params.limit,
    offset:params.offset,
    order:params.order,
    ordername:params.sort,
    search:params.search
  };
}
//给出语言id显示对应语言
function setlanguage(value, row, index) {
  switch (value){
    case 3:
      value = 'C';break;
    case 4:
      value = 'C++';break;
    case 5:
      value = 'Java';
  }
  return [
    '<div>',
    '<span>' + value + '</span>',
    '</div>'
  ].join('');
}

//设置判题显示
function setVerdict(value, row, index) {
  var msg = 'text-danger';
  switch (value){
    case 1:
      value = 'Compiling';break;
    case 2:
      value = 'Compiling';break;
    case 3:
      value = '编译错误';break;
    case 4:
      value = 'Compiling';break;
    case 5:
      value = 'Accepted';msg = 'text-success';break;
    case 6:
      value = '答案错误';break;
    case 7:
      value = '运行错误';break;
    case 8:
      value = '超时';break;
    case 9:
      value = '内存超出';break;
    case 10:
      value = '格式错误';break;
    case 11:
      value = '输出错误';break;
    case 12:
      value = '非法函数';break;
    case 13:
      value = '竞赛已结束';break;
    case 14:
      value = '未知错误';break;
  }
  return [
    '<div>',
    '<span class="'+msg+'">' + value + '</span>',
    '</div>'
  ].join('');
}
//设置内存显示
function setMemory(value, row, index) {
  return [
    '<div>',
    '<span>' + toDecimal(value/1000) + '</span>',
    '</div>'
  ].join('');
}
//点击查看功能
function setContest(value, row, index) {
  if (value == 0){
    value = '无';
  }
  return [
    '<div>',
    '<span>'+value+'</span>',
    '</div>'
  ].join('');
}
//增加题目title点击功能
function setTitle(value, row, index) {
  return [
    '<div>',
    '<a href="problem/'+row.id+'" target="_blank">' + value + '</a>',
    '</div>'
  ].join('');
}

//更改题目acsubmit
function setAcSubmit(value, row, index) {
  var submit = 0;
  if (row.submit <=0){
    submit =  1;
  }else {
    submit =  row.submit;
  }
  return [
    '<div>',
    '<span>('+row.solved+'/'+row.submit+ ')'+toDecimal(row.solved/submit)*100+'%</span>',
    '</div>'
  ].join('');
}
//更改题目用户的radio
function setUserRadio(value, row, index) {
  var submit = row.submit;
  if (submit == 0){
    submit = 1;
  }
  return [
    '<div>',
    '<span>' + toDecimal(row.solved/submit)*100 + '%</span>',
    '</div>'
  ].join('');
}
//排名id自增功能
function setId(value, row, index) {
  return [
    '<div>',
    '<span>'+(index+1)+'</span>',
    '</div>'
  ].join('');
}
//用户名点击功能
function setUsername(value, row, index) {

  if(value == null){
    value = row.username;
  }
  return [
    '<div>',
    '<a href="/user/'+row.id+'" target="_blank">' + value + '</a>',
    '</div>'
  ].join('');
}
//用户博客点击功能
function setUserBlog(value, row, index) {
  if(value == null){
    return [
      '<div>',
      '<a target="_blank" style="color: gray" title="无博客">无</a>',
      '</div>'
    ].join('');
  }else {
    return [
      '<div>',
      '<a href="'+row.blog+'" target="_blank" title="'+value+'">博客</a>',
      '</div>'
    ].join('');
  }

}
//讨论按钮
function setDiscuss(value, row, index) {
  return [
    '<div>',
    '<a href="commont/pro/'+row.id+'">'+'讨论'+'</a>',
    '</div>'
  ].join('');
}


//功能：将浮点数四舍五入，取小数点后2位
function toDecimal(x) {
  var f = parseFloat(x);
  if (isNaN(f)) {
    return;
  }
  f = Math.round(x*100)/100;
  return f;
}
