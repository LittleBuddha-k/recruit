$(document).ready(function () {
    $("#logout").click(function () {
        $.ajax({
            url: "/recruit/system/logout",    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: "",    //参数值
            type: "POST",   //请求方式
            success: function (result) {
                //请求成功时处理
                if ("退出成功" == result.msg) {
                    alert(result.msg);
                    window.location.href = '/recruit/system/loginPage';
                }
            }
        })
    });

    //主页的搜索
    $("#navSearchButton").click(function () {
        let navSearchParam = $("#navSearchParam").val();
        $("#navSearch").val(navSearchParam);
        $('#portalTable').bootstrapTable('refresh');
    })
})