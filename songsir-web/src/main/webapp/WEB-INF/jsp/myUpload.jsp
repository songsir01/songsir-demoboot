<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/11
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>颜值评分</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">

    <script src="../../js/jquery-1.7.2.min.js"></script>
    <script src="../../js/ajaxfileupload.js"></script>
    <style>
        .imgSize{ width:100%; height:90%;}
    </style>
</head>
<body>
<input type="file"  accept="images/*"  id="upload" name="file">
<br>
<div id = "showImg" class="imgSize">

</div>
<div id = "faceShow">
    您的性别：<input id = "gender"><br>
    您的年龄：<input id = "age"><br>
    您的颜值：<input id = "beauty"><br>
</div>
<script>
    $("#upload").live("change",upload);
    function upload(event){
        alert("图片上传较慢，请等待十秒左右");
        if(event.target.files.length == 1 ) {
            if (event.target.files[0].size >= 4096000) {
                alert('您这张图片过大，应小于4M');
            }else{
                $.ajaxFileUpload({url:'photoupload',
                    secureuri:false,
                    fileElementId:'upload',
                    dataType: 'text/html',
                    success: function(data,success){
                        var json=JSON.parse(data);
                        if (json.success) {
                            $("#showImg").append('<img class="imgSize" src="https://songsiraliyun.oss-cn-beijing.aliyuncs.com/images/'+json.msg+'">');
                            if (json.successFace) {
                                $("#gender").val(json.gender);
                                $("#age").val(json.age);
                                $("#beauty").val(json.beauty);
                            }
                        }

                    },
                    error: function (data, status, e){
                        alert("上传图片失败，请稍后重试。");
                    }
                });
            }
        }else{
            alert('您上传的不是图片，请选择图片上传');
        }
    }
</script>
</body>
</html>
