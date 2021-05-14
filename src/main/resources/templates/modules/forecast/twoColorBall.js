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
            $('#twoColorBallTable').bootstrapTable({
                url: '/recruit/forecast/twoColorBall/data',         //请求后台的URL（*）
                method: 'post',                      //请求方式（*）
                //类型json
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: false,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                queryParams: function (params) {
                    var searchParam = $("#twoColorBallSearchForm").serializeJson();
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
                        field: 'issueNumber',
                        title: '期号'
                    }, {
                        field: 'redOne',
                        title: '一号红球'
                    }, {
                        field: 'redTwo',
                        title: '二号红球'
                    }, {
                        field: 'redThree',
                        title: '三号红球'
                    }, {
                        field: 'redFour',
                        title: '四号红球'
                    },  {
                        field: 'redFive',
                        title: '五号红球'
                    }, {
                        field: 'redSix',
                        title: '六号红球',
                    }, {
                        field: 'blue',
                        title: '蓝球',
                    },{
                        field: 'happySunday',
                        title: '欢乐星期天',
                    },{
                        field: 'bonus',
                        title: '奖池奖金',
                    },{
                        field: 'numberOfFirstPrize',
                        title: '一等奖数量',
                    },{
                        field: 'bonusOfFirstPrize',
                        title: '一等奖奖金',
                    },{
                        field: 'numberOfSecondAward',
                        title: '二等奖数量',
                    },{
                        field: 'bonusOfSecondAward',
                        title: '二等奖奖金',
                    },{
                        field: 'totalBets',
                        title: '总投注金额',
                    },{
                        field: 'drawDate',
                        title: '开奖日期',
                        formatter: function (value, row, index) {
                            return rc.dateFormat(row.drawDate);
                        }
                    },
                    {
                        field: 'phone',
                        title: '操作',
                        align: 'center',
                        formatter: function (value, row, index) {
                            return '<button class="btn btn-primary btn-sm" onclick="addChildren(\'' + row.id + '\')">添加下级菜单</button>';
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
        $('#twoColorBallTable').bootstrapTable('refresh');
    })

    //重置按钮
    $("#reset").click(function () {
        //先将查询form的值全部置空
        $("#twoColorBallSearchForm  input").val("");
        //只需刷新bootstraptable，bootstraptable就会去/data接口下带着form参数请求数据
        $('#twoColorBallTable').bootstrapTable('refresh');
    })
})

//获取点击的行的数据id
function getIdSelections() {
    return $.map($("#twoColorBallTable").bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

//刷新列表
function refresh() {
    $('#twoColorBallTable').bootstrapTable('refresh');
}

function add() {
    rc.openSaveDialog("/recruit/forecast/twoColorBall/form/add", "添加双色球信息")
}

function edit() {
    let id = getIdSelections().toString();
    let split = id.toString().split(",");
    if (split[1]) {
        rc.alert("只能选择一条数据")
    } else if (id.length <= 0) {
        rc.alert("请至少选择一条数据")
    } else if (split[0]) {
        rc.openSaveDialog("/recruit/forecast/twoColorBall/form/edit?id=" + id, "编辑菜单信息")
    }

}

function view() {
    let id = getIdSelections().toString();
    let split = id.toString().split(",");
    if (split[1]) {
        rc.alert("只能选择一条数据")
    } else if (id.length <= 0) {
        rc.alert("请至少选择一条数据")
    } else if (split[0]) {
        rc.openSaveDialog("/recruit/forecast/twoColorBall/form/view?id=" + id, "查看菜单信息")
    }
}

function del() {
    let ids = getIdSelections();
    if (ids == null || ids == '') {
        rc.alert("请至少选择一条数据")
    } else {
        rc.post("/recruit/forecast/twoColorBall/delete?ids=" + ids)
    }
}

function showSearchButton() {
    //$("#twoColorBallSearchForm").attr();---也可以给标签设置属性值
    let attr = $("#twoColorBallSearchForm").data("collapse");
    if (attr) {
        //1.搜索表里有指定的属性值，此时搜索表为展开状态open
        //2.判断属性值有否,需要移除data属性值，并移除”in“类
        $("#twoColorBallSearchForm").removeData("collapse");
        $("#twoColorBallSearchForm").removeClass("in");
    } else {
        //1.搜索表里没有指定的属性值，此时搜索表为隐藏状态
        //2.需要修改属性值，并且添加打开类”in“
        $("#twoColorBallSearchForm").data("collapse", "in");
        $("#twoColorBallSearchForm").addClass("in");
    }
}

function importFile() {
    rc.confirm("测试")
}

function exportFile() {
    rc.alert("导出")
}

function addChildren(parentId) {
    rc.openSaveDialog("/recruit/forecast/twoColorBall/form/addChildren?parent.id=" + parentId, "添加下级菜单")
}