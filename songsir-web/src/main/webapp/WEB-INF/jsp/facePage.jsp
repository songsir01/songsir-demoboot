<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="no-cache" http-equiv="Cache-Control">
    <title>颜值评分</title>
    <link rel="stylesheet" href="css/yanzhi.css">
    <style type="text/css">
        .loading {
            position: fixed;
            left: 50%;
            top: 40%;
            z-index: 100;
            width: 120px;
            height: 120px;
            margin: 0 0 0 -60px;
            text-align: center;
            border-radius: 10px;
            background: #fff
        }

        .loading .loadimg {
            -webkit-animation: rotate 1.2s linear infinite;
            -ms-animation: rotate 1.2s linear infinite;
            animation: rotate 1.2s linear infinite;
            min-width: 30px;
            min-height: 30px;
            margin: 30px auto 10px;
            background: url(../../image/loading.png) no-repeat 50% 50%
        }

        @-webkit-keyframes rotate {
            from {
                -webkit-transform: rotate(0)
            }
            to {
                -webkit-transform: rotate(360deg)
            }
        }

        @-ms-keyframes rotate {
            from {
                -ms-transform: rotate(0)
            }
            to {
                -ms-transform: rotate(360deg)
            }
        }

        @keyframes rotate {
            from {
                -webkit-transform: rotate(0)
            }
            to {
                -webkit-transform: rotate(360deg)
            }
        }

        .loadMask {
            position: fixed;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
            z-index: 99;
            opacity: .8;
            background: #000
        }
    </style>

</head>

<body>
<div id="startPage" class="startPage">
    <div class="bd">
        <div class="photo">
            <div class="update-img"><img id="showImg" src="../../image/newyanzhi/default_photo.png"><input
                    id="updateImg" type="file" accept="image/*" name="file"></div>
        </div>
    </div>
</div>
<div class="btnGroup">
    <a id="start" href="javascript:;" class="btn btn_big btn_default">生成颜值成绩单</a>
</div>
<div style="display: none">
    <input id="gender">
    <input id="age">
    <input id="beauty">
    <input id="hasFace" value="yes">
</div>
<script src="../../js/jquery-1.7.2.min.js"></script>
<script src="../../js/ajaxfileupload.js"></script>
<script src="../../js/yanzhi.js"></script>

<script>
    $(function () {
        var first = 0;
        $("#updateImg").live("change", upload);
        function upload(event) {
            if (event.target.files.length == 1) {
                if (event.target.files[0].size >= 40960000) {
                    alert('您这张图片过大，应小于40M');
                } else {
                    loading();
                    $.ajaxFileUpload({
                        url: 'photoupload?memo=yanzhi',
                        secureuri: false,
                        fileElementId: 'updateImg',
                        dataType: 'text/html',
                        success: function (data, success) {
                            var json = JSON.parse(data);
                            if (json.success) {
                                $('#showImg').attr('src', "https://songsirimg.oss-cn-beijing.aliyuncs.com/home/img/" + json.msg);
                                if (json.successFace) {
                                    $("#gender").val(json.gender);
                                    $("#age").val(json.age);
                                    $("#beauty").val(json.beauty);
                                } else {
                                    $("#hasFace").val("no");
                                }
                            }
                            setTimeout(closeLoad, 2000);
                        },
                        error: function (data, status, e) {
                            alert("上传图片失败，请稍后重试。");
                        }
                    });
                }
            } else {
                alert('您上传的不是图片，请选择图片上传');
            }
        }
        // 点击展示颜值
        $("#start").click(function () {
            // 判断是否上传图片
            var imgSrc = $("#showImg").attr("src");
            if (imgSrc.indexOf("default_photo") > -1) {
                spTools.msg({
                    con: '<p>请上传一张您的美照！</p>'
                });
                return false;
            }
            if ($("#hasFace").val() == "no") {
                spTools.msg({
                    con: '<p>抱歉，未检测到人脸！</p>'
                });
                return false;
            }
            var gender = $("#gender").val();
            var age = $("#age").val();
            var beauty = $("#beauty").val();
            // 保存一次
            if (first == 0) {
                saveInfo(gender, age, beauty, imgSrc);
            }
            spTools.msg({
                con: "<p>您的性别：" + gender + "</p><p>您的年龄：" + age + "</p><p>您的颜值：" + beauty + "</p>"
            });
            first++;
            return false;
        })
        function saveInfo(gender, age, beauty, imgSrc) {
            $.ajax({
                type: "POST",
                url: 'savaFaceInfo',
                data: {
                    "sex": gender,
                    "age": age,
                    "score": beauty,
                    "imgUrl": imgSrc
                },
                async: false,
                timeout: 60000,
                success: function (data) {
                }
            });
        }
    })

    function loading() {
        $('body').append('<div class="loading"><div class="loadimg"></div>图片较大，正在努力加载...</div><div class="loadMask"></div>');
        $('.loadMask,.loading').css('display', 'block');
    }

    function closeLoad() {
        setTimeout($('.loading,.loadMask').remove(), 2000);
    }

</script>
</body>
</html>