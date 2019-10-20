function loadCallList(divName, conf) {
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
					ctt += '<td><a href="/api-call-stat/1?apiName='+data[d][conf.cols[i].field]+'">' + data[d][conf.cols[i].field] + '</a></td>';
				}
			}
			ctt += '</tr>';
		}
		ctt+= '</tbody>';
		ctt += '</table>';
		$('#' + divName).html(ctt);
	});
}