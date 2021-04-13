(function ($) {
    $.fn.serializeJson = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name]) && this.value != "") {
                    serializeObj[this.name].push(this.value);
                } else {
                    if (this.value != "") {
                        serializeObj[this.name] = [serializeObj[this.name], this.value];
                    }
                }
            } else {
                if (this.value != "") {
                    serializeObj[this.name] = this.value;
                }
            }
        });
        return serializeObj;
    };

    //转换时间格式
    rc = {
        dateFormat: function resolvingDate(date) {
            //date是传入的时间
            var d = new Date(date);

            var month = (d.getMonth() + 1) < 10 ? '0' + (d.getMonth() + 1) : (d.getMonth() + 1);
            var day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate();
            var hours = d.getHours() < 10 ? '0' + d.getHours() : d.getHours();
            var min = d.getMinutes() < 10 ? '0' + d.getMinutes() : d.getMinutes();
            var sec = d.getSeconds() < 10 ? '0' + d.getSeconds() : d.getSeconds();

            var times = d.getFullYear() + '-' + month + '-' + day + ' ' + hours + ':' + min + ':' + sec;

            return times
        },
        post: function post(url, data) {
            $.ajax({
                url: url,    //请求的url地址
                dataType: "json",   //返回格式为json
                async: true,//请求是否异步，默认为异步，这也是ajax重要特性
                data: data,    //参数值
                type: "POST",   //请求方式
                success: function (result) {
                    //请求成功时处理
                    alert(result.msg);
                    //重新刷新页面
                    //window.location.reload();
                    refresh();
                }
            });
        },
        get: function get(url, data) {
            $.ajax({
                url: url,    //请求的url地址
                dataType: "json",   //返回格式为json
                async: true,//请求是否异步，默认为异步，这也是ajax重要特性
                data: data,    //参数值
                type: "GET",   //请求方式
                success: function (result) {
                    //请求成功时处理
                    alert(result.msg);
                    //重新刷新页面
                    //window.location.reload();
                    refresh();
                }
            });
        },
        open: function open(url, title) {
            window.open(url, title, 'height=600, width=800, top=30%,left=30%, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
        }
    }
})(jQuery);