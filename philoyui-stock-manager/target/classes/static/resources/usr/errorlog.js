function loadErrorContent(host) {
    var aa="";
    $.getJSON('/stat/host/error?hostname='+host, function (option) {
        for (var  pro in option){
            aa=option[pro];
            $("#errortop").append(" <h5>"+aa+"</h5>")
        }
    });
}