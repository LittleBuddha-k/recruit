(function($){
    $.fn.serializeJson=function(){
        var serializeObj={};
        var array=this.serializeArray();
        var str=this.serialize();
        $(array).each(function(){
            if(serializeObj[this.name]){
                if($.isArray(serializeObj[this.name])&&this .value!=""){
                    serializeObj[this.name].push(this.value);
                }else {
                    if(this .value!=""){
                        serializeObj[this.name]=[serializeObj[this.name],this.value];
                    }
                }
            }else{
                if(this .value!=""){
                    serializeObj[this.name]=this.value;
                }
            }
        });
        return serializeObj;
    };

    //转换时间格式
    rc={
        dateFormat:function resolvingDate(date){
        //date是传入的时间
        let d = new Date(date);

        let month = (d.getMonth() + 1) < 10 ? '0'+(d.getMonth() + 1) : (d.getMonth() + 1);
        let day = d.getDate()<10 ? '0'+d.getDate() : d.getDate();
        let hours = d.getHours()<10 ? '0'+d.getHours() : d.getHours();
        let min = d.getMinutes()<10 ? '0'+d.getMinutes() : d.getMinutes();
        let sec = d.getSeconds()<10 ? '0'+d.getSeconds() : d.getSeconds();

        let times=d.getFullYear() + '-' + month + '-' + day + ' ' + hours + ':' + min + ':' + sec;

        return times
    }
    }
})(jQuery);