function loadDetail(venderId, venderName) {
	creatIframeCustom("/stat/vender-detail/" + venderId, venderName + "明细");
}

function loadApiDetail(apiName, kind) {
		creatIframeCustom("/stat/api-detail?apiName=" + apiName+"&kind="+kind,"");

}

function loadList(divName, conf) {
	$.getJSON(conf.url, function(data) {
		var tableStyle = "";
		if(conf.width){
			tableStyle =  'style="width:'+conf.width+'px;"';
		}
		var ctt = '<table class="table table-border table-bordered table-bg table-hover table-sort"'+tableStyle+'>';
		ctt += '<thead>';
		ctt += '<tr><th scope="col" colspan="'+(conf.cols.length+1)+'">'+conf.name+'</th></tr>';
		ctt += '<tr class="text-c">';
		ctt += '<th width="50">#</th>';
		for(var i=0;i<conf.cols.length;i++){
			var style = "";
			if(conf.cols[i].width){
				 style= ' width="'+conf.cols[i].width+'"';
			}
			ctt += '<th'+style+'>'+conf.cols[i].name+'</th>';
		}
		ctt += '</tr>';
		ctt += '</thead>';
		ctt += '<tbody>';
		for (var d in data) {
			var index = parseInt(d) + 1;
			ctt += '<tr class="text-c va-m">';
			ctt += '<td>' + index + '</td>';
			for(var i=0;i<conf.cols.length;i++){
				if(conf.cols[i].filter){
					ctt += '<td>' + conf.cols[i].filter(data[d]) + '</td>';
				}else{
					ctt += '<td>' + data[d][conf.cols[i].field] + '</td>';
				}
			}
			ctt += '</tr>';
		}
		ctt+= '</tbody>';
		ctt += '</table>';
		$('#' + divName).html(ctt);
	});
}

function creatIframeCustom(href, titleName) {
	var bStop = false;
	var bStopIndex = 0;
	var topWindow = $(window.parent.parent.parent.document);
	var show_navLi = topWindow.find("#min_title_list li");
	show_navLi.each(function() {
		if ($(this).find('span').attr("data-href") == href) {
			bStop = true;
			bStopIndex = show_navLi.index($(this));
			return false;
		}
	});
	if (!bStop) {
		var show_nav = topWindow.find('#min_title_list');
		show_nav.find('li').removeClass("active");
		var iframe_box = topWindow.find('#iframe_box');
		show_nav.append('<li class="active"><span data-href="' + href + '">' + titleName + '</span><i></i><em></em></li>');
		tabNavallwidthCustom();
		var iframeBox = iframe_box.find('.show_iframe');
		iframeBox.hide();
		iframe_box.append('<div class="show_iframe"><div class="loading"></div><iframe frameborder="0" src=' + href + '></iframe></div>');
		var showBox = iframe_box.find('.show_iframe:visible');
		showBox.find('iframe').attr("src", href).load(function() {
			showBox.find('.loading').hide();
		});
	} else {
		show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
		var iframe_box = topWindow.find("#iframe_box");
		iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("src", href);
	}
}

function tabNavallwidthCustom() {
	var topWindow = $(window.parent.parent.parent.document);
	var taballwidth = 0;
	var $tabNav = topWindow.find(".acrossTab");
	var $tabNavWp = topWindow.find(".Hui-tabNav-wp");
	var $tabNavitem = topWindow.find(".acrossTab li");
	var $tabNavmore = topWindow.find(".Hui-tabNav-more");
	if (!$tabNav[0]) {
		return
	}
	$tabNavitem.each(function(index, element) {
		taballwidth += Number(parseFloat($(this).width() + 60))
	});
	$tabNav.width(taballwidth + 25);
	var w = $tabNavWp.width();
	if (taballwidth + 25 > w) {
		$tabNavmore.show()
	} else {
		$tabNavmore.hide();
		$tabNav.css({
			left : 0
		})
	}
}