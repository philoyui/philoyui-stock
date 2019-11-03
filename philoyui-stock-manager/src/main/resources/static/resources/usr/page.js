function page(number,size){
    var url=$("#pageUrl").val();
    var frm=$("body form:eq(0)");
    if (frm==undefined||frm.length==0||frm.context==undefined){
        window.location=url+"?pagenumber="+number+"&pagesize="+size;
    }else {
        var numberInput=$("<input type='hidden' name='pagenumber'/>");
        numberInput.val(number);
        var sizeInput=$("<input type='hidden' name='pagesize'/>");
        sizeInput.val(size);
        frm.append(numberInput);
        frm.append(sizeInput);
        frm.get(0).submit();
    }

}