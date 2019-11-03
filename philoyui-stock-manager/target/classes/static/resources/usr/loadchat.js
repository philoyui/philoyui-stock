/**
 * Created by hanzenggui on 2016/12/23.
 */

(function ($) {
    $.fn.chat={
        data:{
            es:null
        },
        init:function (ec,func) {
            $.fn.chat.data.es=ec;
            $("#chatload").bind("click",function () {
                var type=$("#chatTypeSelect").val();
                var datetime=$(this).prev().val();
                var params;
                if ("selectedDay"==type){
                    if (datetime==''){
                        alert("请输入时间");
                        return;
                    }
                    params="?assignedDate="+datetime.substring(0,8);
                }else if("selectedHour"==type){
                    if (datetime==''){
                        alert("请输入时间");
                        return;
                    }
                    params="?assignedDate="+datetime.substring(0,10);
                }else {
                    params="";
                }
                $.fn.chat.loadData(type,params,func);
            })
        },
        loadData:function (durationType,params,func) {
            $(".loadchat").each(function () {
                var chart=$.fn.chat.data.es.init(this,'red')
                chart.clear();
                chart.showLoading({text: '正在努力的读取数据中...'});
                var url=$(this).attr("url")+"/"+durationType;
                var hostvalue=$("#host").val();
                if (hostvalue!=undefined){
                    if (hostvalue!=null){
                        if (hostvalue!=''){
                            url=url+"/"+hostvalue
                        }
                    }
                }
                $.getJSON(url + params, function (option) {
                    chart.setOption(option, true);
                    chart.hideLoading();
                    if (func!=undefined){
                        if (func!=null){
                            chart.on("click",func)
                        }
                    }
                });
            })
        }
    }
    //yyyyMMddHHmmss
 

})($)