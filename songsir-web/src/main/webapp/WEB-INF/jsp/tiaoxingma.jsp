<%--
  Created by IntelliJ IDEA.
  User: Think
  Date: 2019/8/5
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>生成条形码</title>
    <script src="js/barcode.js"></script>
    <script src="js/jquery-1.7.2.min.js"></script>
    <style>
        .barcode { margin-top: 20px; text-align: center}
        .barcode + * { clear:both}
        #barcode { white-space: nowrap; zoom: .5;/* -webkit-transform: scaleX( .5); transform: scaleX(.5);*/}
        #barcode div { display: inline-block; height: 120px}
        .barcode .bar1 { border-left:2px solid black}
        .barcode .bar2 { border-left:4px solid black}
        .barcode .bar3 { border-left:6px solid black}
        .barcode .bar4 { border-left:8px solid black}
        .barcode .space0 { margin-right:0}
        .barcode .space1 { margin-right:2px}
        .barcode .space2 { margin-right:4px}
        .barcode .space3 { margin-right:6px}
        .barcode .space4 { margin-right:8px}
        .barcode label { clear:both; display:block; text-align:center; font: 16px/34px helvetica}
    </style>
</head>

<body>
<div class="barcode code-box">
    <div id="barcode"></div>
    <input type="hidden" value="${sphone}" id="sphone">
</div>
<script>
    var sphone = $("#sphone").val();
    $(function () {
        // showDiv：代表需要显示的divID，
        // textVlaue ： 代表需要生成的值，
        // barcodeType：代表生成类型(A、B、C)三种类型
        createBarcode('barcode', sphone, 'B')
    });
</script>
</body>
</html>
