
/*<![CDATA[*/
$(function() {
    var linkInFieldIdArray = linkInFieldIds.split(",");
    var linkInDomainArray = linkInDomains.split(",");

    for(var i= 0;i<linkInFieldIdArray.length;i++){
        prepare_linkIn_form(linkInFieldIdArray[i],linkInDomainArray[i]);
    }
});

function prepare_linkIn_form(linkInFieldId,linkInDomain){
    $('#link_in_' + linkInFieldId).cxSelect({
      url: '/admin/'+linkInDomain+'/linkIn',               // 如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
      selects: [linkInFieldId + '0', linkInFieldId + '1', linkInFieldId + '2'],  // 数组，请注意顺序
        jsonName: 'value',
        jsonValue: 'name',
        jsonSub: 'sub',
        emptyStyle: 'none'
    });
}


/*]]>*/