
# 一、需求目的

 - 由于特殊场景需求，需要将一些字符码生成一个条形码方便使用。比如：优惠券条形码方便扫码枪核销。
 - 以下使用JS生成，并使用简单的CSS优化样式

# 二、页面

## 1、新建html
```
<!--
Created by IntelliJ IDEA.
User: songsir
Date: 2018/11/26
Time: 10:49
-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JS生成条形码</title>
</head>
<body>

</body>
</html>
```
## 2、导入相关JS


```
// 后文提供js源码
<script src="barcode.js"></script>
<script src="jquery-1.8.2.js"></script>
```
## 3、添加一个div，用于展示条形码


```
<div class="barcode code-box">
    <div id="barcode"></div>
</div>
```
## 4、调用生成条形码方法


```
$(function () {
        // showDiv：代表需要显示的divID，
        // textVlaue ： 代表需要生成的值，
        // barcodeType：代表生成类型(A、B、C)三种类型
        createBarcode('barcode', 'SONGSIR', 'B')
    });
```


## 5、查看效果

![在这里插入图片描述](https://img-blog.csdnimg.cn/20181126111714651.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)

发现并没有得到想要的结果，页面审查元素
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181126111921507.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
发现，条形码貌似生成了，但是并没有展示。此时需要添加二维码CSS样式。

```
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
```
接下来，见证奇迹：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181126112259201.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)

## 6、完整代码

### （1）页面

```
<!--
Created by IntelliJ IDEA.
User: songsir
Date: 2018/11/26
Time: 10:49
-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JS生成二维码</title>
    <script src="barcode.js"></script>
    <script src="jquery-1.8.2.js"></script>
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
</div>
<script>
    $(function () {
        // showDiv：代表需要显示的divID，
        // textVlaue ： 代表需要生成的值，
        // barcodeType：代表生成类型(A、B、C)三种类型
        createBarcode('barcode', 'SONGSIR', 'B')
    });
</script>
</body>
</html>
```
### （2）barcode.js

```
/**
 * @Description 生成条形码
 * @Author songsir
 * @Date 2018/11/26
 */
/*****************************************************************start*****************************************************/
(function () {
    if (!exports) var exports = window;
    var BARS = [212222, 222122, 222221, 121223, 121322, 131222, 122213, 122312, 132212, 221213, 221312, 231212, 112232, 122132, 122231, 113222, 123122, 123221, 223211, 221132, 221231, 213212, 223112, 312131, 311222, 321122, 321221, 312212, 322112, 322211, 212123, 212321, 232121, 111323, 131123, 131321, 112313, 132113, 132311, 211313, 231113, 231311, 112133, 112331, 132131, 113123, 113321, 133121, 313121, 211331, 231131, 213113, 213311, 213131, 311123, 311321, 331121, 312113, 312311, 332111, 314111, 221411, 431111, 111224, 111422, 121124, 121421, 141122, 141221, 112214, 112412, 122114, 122411, 142112, 142211, 241211, 221114, 413111, 241112, 134111, 111242, 121142, 121241, 114212, 124112, 124211, 411212, 421112, 421211, 212141, 214121, 412121, 111143, 111341, 131141, 114113, 114311, 411113, 411311, 113141, 114131, 311141, 411131, 211412, 211214, 211232, 23311120]
        , START_BASE = 38
        , STOP = 106;
    function code128(code, barcodeType) {
        if (arguments.length < 2)
            barcodeType = code128Detect(code);
        if (barcodeType == 'C' && code.length % 2 == 1)
            code = '0' + code;
        var a = parseBarcode(code, barcodeType);
        return bar2html(a.join('')) + '<label>' + code + '</label>';
    }
    function bar2html(s) {
        for (var pos = 0, sb = []; pos < s.length; pos += 2) {
            sb.push('<div class="bar' + s.charAt(pos) + ' space' + s.charAt(pos + 1) + '"></div>');
        }
        return sb.join('');
    }
    function code128Detect(code) {
        if (/^[0-9]+$/.test(code)) return 'C';
        if (/[a-z]/.test(code)) return 'B';
        return 'A';
    }
    function parseBarcode(barcode, barcodeType) {
        var bars = [];
        bars.add = function (nr) {
            var nrCode = BARS[nr];
            this.check = this.length == 0 ? nr : this.check + nr * this.length;
            this.push(nrCode || ("UNDEFINED: " + nr + "->" + nrCode));
        };
        bars.add(START_BASE + barcodeType.charCodeAt(0));
        for (var i = 0; i < barcode.length; i++) {
            var code = barcodeType == 'C' ? +barcode.substr(i++, 2) : barcode.charCodeAt(i);
            converted = fromType[barcodeType](code);
            if (isNaN(converted) || converted < 0 || converted > 106) throw new Error("Unrecognized character (" + code + ") at position " + i + " in code '" + barcode + "'.");
            bars.add(converted);
        }
        bars.push(BARS[bars.check % 103], BARS[STOP]);
        return bars;
    }
    var fromType = {
        A: function (charCode) {
            if (charCode >= 0 && charCode < 32) return charCode + 64;
            if (charCode >= 32 && charCode < 96) return charCode - 32;
            return charCode;
        },
        B: function (charCode) {
            if (charCode >= 32 && charCode < 128) return charCode - 32;
            return charCode;
        },
        C: function (charCode) {
            return charCode;
        }
    };
    //--| Export
    exports.code128 = code128;
})();
/**
 * showDiv：代表需要显示的divID，
 * textVlaue ： 代表需要生成的值，
 * barcodeType：代表生成类型(A、B、C)三种类型
 */
function createBarcode(showDiv, textValue, barcodeType) {
    var divElement = document.getElementById(showDiv);
    divElement.innerHTML = code128(textValue, barcodeType);
}

```
# 三、Over
