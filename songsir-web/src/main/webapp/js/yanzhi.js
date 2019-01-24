//工具箱
var spTools = {
    msg : function (info){
        var dialogHtml = '<div class="jDialog"><div class="dialog msg"><div class="content">' + info.con +  '</div><i class="close jClose"></i></div><div class="mask"></div></div>';
        spTools.creatDom(dialogHtml, 2000);
    },
    alert : function (info){
        var dialogHtml = '<div class="jDialog"><div class="dialog alert"><div class="content">' + info.con +  '<p class="g">' + info.btn + '</p></div><i class="close jClose"></i></div><div class="mask"></div></div>';
        spTools.creatDom(dialogHtml);
    },
    loading : function (info){
        var dialogHtml = '<div class="jDialog"><div class="dialog loading"><div class="content">' + info.con +  '</div><i class="close jClose"></i></div><div class="mask"></div></div>';
        spTools.creatDom(dialogHtml);
    },
    creatDom : function (dom, timer){
        //禁止页面滚动
        window.ontouchmove=function(e){
            e.preventDefault && e.preventDefault();
            e.returnValue = false;
            e.stopPropagation && e.stopPropagation();
            return false;
        }
        $(".jDialog").remove();
        $("body").append(dom);
        $(".jDialog").show("fast");
            $(".jClose").click(function(event) {
                spTools.closeDialog();
            });
        if(timer != undefined){
            setTimeout(spTools.closeDialog, timer)
        }
    },
    closeDialog: function (){
        //允许页面滚动
        window.ontouchmove=function(e){
            e.preventDefault && e.preventDefault();
            e.returnValue = true;
            e.stopPropagation && e.stopPropagation();
            return true;
        }
        $(".jDialog").hide("fast", function(){
            $(this).remove();
        });
    }
}
