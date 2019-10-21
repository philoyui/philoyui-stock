(function ($,require) {
    $.fn.table={
        ec:null,
        url:null,
        data:{
            arr:null,
        },
       /* [{type,id,url,{adddata},bindfunction:{type,func},pageconfig}]*/
        init:function (cdata) {
            $.extend($.fn.table.data,cdata);
            $.fn.table.initec();

        },
        initec:function () {
            require.config({
                paths: {
                    echarts: 'http://echarts.baidu.com/build/dist'
                }
            });
            require([
                'echarts',
                'echarts/chart/bar',
                'echarts/chart/pie'
            ],function (ec) {
                $.fn.table.ec=ec;
                $.fn.table.loaddataall()
                $("#chatload").bind("click",$.fn.table.loadbutton);
            })
        },
        loaddataall:function () {
            for(var i=0;i<$.fn.table.data.arr.length;i++){
                datachild=$.fn.table.data.arr[i];
                $.fn.table.loaddataone(datachild);
            }
        },
        loaddataone:function (datachild) {
            var stype=$("#chatTypeSelect").val();
            var sdate=$("#assigndate").val();
            if (sdate==""){
                sdate="aa"
            }
            $.fn.table.url=datachild.url+"/"+ stype+"/"+ sdate;
            switch(datachild.type){
                case "echart":
                    $.fn.table.loadechart(datachild);
                    break;
                case "table":
                    $.fn.table.loadtable(datachild);
                    break;
                default:
                    break;
            }
        },
        loadechart:function (datachild) {                                                   //加载echart
            var chart = $.fn.table.ec.init(document.getElementById(datachild.id),'red');
            chart.clear();
            chart.showLoading({text: '正在努力的读取数据中...'});
            var url=$.fn.table.url;
            $.getJSON(url,datachild.data,function (option) {
                chart.setOption(option, true);
                chart.hideLoading();
                if(!$.isEmptyObject(datachild.bindfunction)){
                    for (var j =0;j<datachild.bindfunction.length;j++){
                        chart.on(datachild.bindfunction[j].type,datachild.bindfunction[j].func);
                    }
                }
            });
        },
        loadtable:function (datachild) {                                                  //加载table
            datachild.pageconfig.url=$.fn.table.url;
            $("#"+datachild.id).bootstrapTable('destroy');
            $("#"+datachild.id).bootstrapTable( datachild.pageconfig);
        },
        loadbutton:function () {                           //load按钮绑定事件
            var type=$("#chatTypeSelect").val();
            var datetime=$("#assigndate").val();
            if ("selectedDay"==type){
                if (datetime==''){
                    alert("请输入时间");
                    return;
                }
            }else if("selectedHour"==type){
                if (datetime==''){
                    alert("请输入时间");
                    return;
                }
            }
            $.fn.table.loaddataall();
        }

    }
})($,require)
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}