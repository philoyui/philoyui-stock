
/*<![CDATA[*/

$(function() {

    var editorIdArray = editorIds.split(",");
    for(var i= 0;i<editorIdArray.length;i++){
        editor(editorIdArray[i]);
    }

});


function editor(idName){

        var E = window.wangEditor

        var editor = new E('#'+idName)
        var $text1 = $('#textarea_'+idName)

        editor.customConfig.uploadImgServer = '/uploadImg'

        editor.customConfig.uploadFileName = 'filedata'

        editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024

        // 自定义菜单配置
        editor.customConfig.menus = [
            'head',  // 标题
            'bold',  // 粗体
            'fontSize',  // 字号
            'fontName',  // 字体
            'italic',  // 斜体
            'underline',  // 下划线
            'strikeThrough',  // 删除线
            'foreColor',  // 文字颜色
            'backColor',  // 背景颜色
            'link',  // 插入链接
            'list',  // 列表
            'justify',  // 对齐方式
            'quote',  // 引用
            'image',  // 插入图片
            'table',  // 表格
            'code',  // 插入代码
        ]

        editor.customConfig.onchange = function (html) {
            $text1.val(html)
        }

        editor.create()
        $text1.val(editor.txt.html())

}
/*]]>*/