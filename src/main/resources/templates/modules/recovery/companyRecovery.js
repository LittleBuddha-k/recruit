$(document).ready(function () {
    $(function () {
        //1.初始化Table
        var oTable = new TableInit();
        oTable.Init();

        //2.初始化Button的点击事件
        var oButtonInit = new ButtonInit();
        oButtonInit.Init();

        //3.在表格右上角工具按钮处加入自定义按钮
        let html = $("#toolButton").html();
        $(".columns.columns-right.btn-group.pull-right").append(html);
    });

    var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            $('#companyTable').bootstrapTable({
                url: '/recruit/manager/company/recoveryData',         //请求后台的URL（*）
                method: 'post',                      //请求方式（*）
                //类型json
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                queryParams: function (params) {
                    var searchParam = $("#companySearchForm").serializeJson();
                    searchParam.pageNo = params.limit === undefined ? "1" : params.offset / params.limit + 1;
                    searchParam.pageSize = params.limit === undefined ? -1 : params.limit;
                    searchParam.orderBy = params.sort === undefined ? "" : params.sort + " " + params.order;
                    return searchParam;
                },//传递参数（*）
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                strictSearch: true,
                showColumns: true,                  //是否显示所有的列
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                columns: [
                    {
                        checkbox: true
                    }, {
                        field: 'legalPerson',
                        title: '法人代表',
                        sortable: "true",
                        sortName: "a.legal_person"
                    }, {
                        field: 'chineseName',
                        sortable: "true",
                        title: '中文名称'
                    }, {
                        field: 'englishName',
                        sortable: "true",
                        title: '英文名称'
                    }, {
                        field: 'companyName',
                        sortable: "true",
                        title: '公司名称'
                    }, {
                        field: 'headquartersAddress',
                        sortable: "true",
                        title: '总部地址'
                    },{
                        field: 'establishDate',
                        sortable: "true",
                        title: '成立日期',
                        formatter: function(value,row,index){
                            return rc.dateFormat(value);
                        }
                    },{
                        field: 'phone',
                        sortable: "true",
                        title: '联系电话'
                    },{
                        field: 'introduction',
                        sortable: "true",
                        title: '介绍'
                    },{
                        field: 'ranging',
                        sortable: "true",
                        title: '经营范围'
                    },{
                        field: 'nature',
                        sortable: "true",
                        title: '公司性质'
                    },{
                        field: 'registeredCapital',
                        sortable: "true",
                        title: '注册资本'
                    },{
                        field: 'scale',
                        sortable: "true",
                        title: '规模'
                    },{
                        field: 'pictures',
                        sortable: "true",
                        title: '公司图片宣传'
                    },{
                        field: 'video',
                        sortable: "true",
                        title: '公司视频宣传'
                    },{
                        field: 'phone',
                        title: '操作',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return '<button class="btn btn-primary btn-sm" onclick="recoveryData(\'' + row.id + '\')">恢复</button>' +
                                   '<button class="btn btn-primary btn-sm" onclick="deleteData(\'' + row.id + '\')">删除</button>';
                        }
                    }
                ]
            });
        };

        return oTableInit;
    };

    var ButtonInit = function () {
        var oInit = new Object();
        var postdata = {};

        oInit.Init = function () {
            //初始化页面上面的按钮事件
        };

        return oInit;
    };

    //查询按钮
    $("#search").click(function () {
        //只需刷新bootstraptable，bootstraptable就会去/data接口下带着form参数请求数据
        $('#companyTable').bootstrapTable('refresh');
    })

    //重置按钮
    $("#reset").click(function () {
        //先将查询form的值全部置空
        $("#companySearchForm  input").val("");
        //只需刷新bootstraptable，bootstraptable就会去/data接口下带着form参数请求数据
        $('#companyTable').bootstrapTable('refresh');
    })
})

//获取点击的行的数据id
function getIdSelections() {
    return $.map($("#companyTable").bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

//刷新列表
function refresh() {
    $('#companyTable').bootstrapTable('refresh');
}

function add() {
    window.open('/recruit/manager/company/form/add', "新建招聘公司信息", 'height=600, width=800, top=30%,left=30%, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
}

function edit() {
    let id = getIdSelections();
    if (id.toString().length > 32) {
        alert("只能选择一条数据")
    } else if (id.toString().length < 32) {
        alert("请至少选择一条数据")
    } else if (id.toString().length = 32) {
        window.open('/recruit/manager/company/form/edit?id=' + id, "编辑招聘公司信息", 'height=600, width=800, top=30%,left=30%, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
    }

}

function view() {
    let id = getIdSelections();
    if (id.toString().length > 32) {
        alert("只能选择一条数据")
    } else if (id.toString().length < 32) {
        alert("请至少选择一条数据")
    } else if (id.toString().length = 32) {
        window.open('/recruit/manager/company/form/view?id=' + id, "查看招聘公司信息", 'height=600, width=800, top=30%,left=30%, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
    }
}

function del() {
    let ids = getIdSelections();
    if (ids == null || ids == '') {
        alert("请至少选择一条数据")
    } else {
        $.ajax({
            url: "/recruit/manager/company/delete?ids=" + ids,    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true,//请求是否异步，默认为异步，这也是ajax重要特性
            data: "",    //参数值
            type: "POST",   //请求方式
            success: function (result) {
                //请求成功时处理
                alert(result.msg);
                //重新刷新页面
                //window.location.reload();
                refresh();
            }
        });
    }
}

function showSearchButton() {
    //$("#operatorSearchForm").attr();---也可以给标签设置属性值
    let attr = $("#companySearchForm").data("collapse");
    if(attr){
        //1.搜索表里有指定的属性值，此时搜索表为展开状态
        //2.判断属性值有否,需要移除data属性值，并移除”in“类
        $("#companySearchForm").removeData("collapse");
        $("#companySearchForm").removeClass("in");
    }else {
        //1.搜索表里没有指定的属性值，此时搜索表为隐藏状态
        //2.需要修改属性值，并且添加打开类”in“
        $("#companySearchForm").data("collapse","in");
        $("#companySearchForm").addClass("in");
    }
}

function delivery() {
    alert("投递简历")
}

function detail() {
    alert("查看招聘详情")
}

function recoveryData(id) {
    rc.post("/recruit/manager/company/recovery",{"id":id})
}

function deleteData(id) {
    rc.post("/recruit/manager/company/deleteByPhysics?ids="+id)
}