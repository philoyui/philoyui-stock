(function () {
    $(".xz_imgOriginalFile").each(function () {
        var id = $(this).attr("id");
        $(this).on("change", (function (fileId) {
            return function () {
                $("#" + fileId).parent().find(".uploadStr").val($("#" + fileId).val());
            }
        })(id))
    })
    $(".xz_imgTapDiv").each(function () {
        $(this).on("click", (function () {
            $(this).parent().prev().trigger("click");
        }))
    })
    $(".xz_file_upload").each(function () {
        var id = $(this).parents(".aw_img").prev().attr("id");
        $(this).on("click", (function (fileId) {
            return function () {
                var file = document.getElementById(fileId).files[0];
                if (file.value == "") {
                    return;
                }
                if (!/image\/\w+/.test(file.type)) {
                    alert("文件类型有误！");
                    return false;
                }
                var size = file.size;
                if (size > 5000000) {
                    alert("图片大小请控制在5M以下");
                    $('#' + fileId).val('');
                    return;
                }
                //开始上传文件
                savePath(fileId)
                $("#" + fileId).val('');
                $("#" + fileId).parent().find(".uploadStr").val('');
            }
        })(id))

        //上传成功之后
        function savePath(fileId) {
            var a = new FormData();
            a.append("image_file", $("#" + fileId)[0].files[0]);
            a.append("id", 1);
            $.ajax({
                url: "/common/upload",
                xhrFields: {
                    withCredentials: true
                },
                type: "POST",
                cache: false,
                data: a,
                processData: false,
                contentType: false,
                async: false,
                success: function (result) {
                    if (result) {
                        addPath(result, fileId, 1);
                        $("<a/>").attr({
                            "href": result,
                            "target": "_blank"
                        }).addClass("xz_img_show").text("查看").appendTo($("#" + fileId).parent().find(".xz_img_showDiv"));
                        $("<span/>").text("删除").on("click", function () {
                            $(this).prev().remove();
                            $(this).remove();
                            addPath(result, fileId, 0);
                        }).addClass("xz_img_del").appendTo($("#" + fileId).parent().find(".xz_img_showDiv"));
                    }
                }
            })
        }

        function addPath(path, id, flag) {
            var val = $("#" + id).prev().val();
            if (flag) {//添加
                val = val + path + ";";
            } else {//删除
                val = val.replace(path, "");
            }
            $("#" + id).prev().val(val);
        }
    })

})()