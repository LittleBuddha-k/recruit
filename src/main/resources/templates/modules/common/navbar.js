$(document).ready(function () {
    $("#logout").click(function () {
        $.ajax({
            url: "/recruit/system/logout",    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: $("#register-form").serialize(),    //参数值
            type: "POST",   //请求方式
            beforeSend: function () {
                //请求前的处理
            },
            success: function (result) {
                //请求成功时处理
                if ("退出成功" == result.msg) {
                    alert(result.msg)
                    setTimeout(function () {
                        window.location.href = '/recruit/system/loginPage';
                    }, 2000);
                }
            },
            complete: function () {
                //请求完成的处理
            },
            error: function () {
                //请求出错处理
            }
        })
    })
})