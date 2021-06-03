layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    table.render({
        elem: '#twoColorBallTable',
        url: '/recruit/forecast/twoColorBall/data',
        method: 'POST',
        request: {
            pageName: 'pageNo', // page
            limitName: 'pageSize' // limit
        },//重命名参数名称
        done: function (res) {
            //做checkbox回显
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
        },
        toolbar: '#toolBar',
        defaultToolbar: [
            'filter',
            'exports',
            'print',
            {
                title: '提示',
                layEvent: 'test',
                icon: 'layui-icon-tips'
            }
        ],
        cols: [
            [
                {
                    type: "checkbox"
                },
                {
                    title: '药名',
                    field: 'issueNumber',
                    sort: true,
                    sortName: 'issueNumber'
                },
                {
                    title: '功能',
                    field: 'redOne',
                    sort: true
                },
                {
                    title: '原价',
                    field: 'redTwo'
                },
                {
                    title: '折后价',
                    field: 'redThree'
                },
                {
                    title: '图片',
                    field: 'redFour',
                    sort: true
                },
                {
                    title: '折扣',
                    field: 'redFive',
                    sort: true
                },
                {
                    title: '标签',
                    field: 'redSix',
                    sort: true
                },
                {
                    title: '处方类型',
                    field: 'blue',
                    sort: true
                },
                {
                    title: '基本单位',
                    field: 'happySunday',
                    sort: true
                },
                {
                    title: '品牌',
                    field: 'bonus',
                    sort: true
                },
                {
                    title: '批准文号',
                    field: 'numberOfFirstPrize',
                    sort: true
                },
                {
                    title: '产地',
                    field: 'bonusOfFirstPrize',
                    sort: true
                },
                {
                    title: '成分',
                    field: 'numberOfSecondAward',
                    sort: true
                },
                {
                    title: '成分',
                    field: 'bonusOfSecondAward',
                    sort: true
                },
                {
                    title: '成分',
                    field: 'totalBets',
                    sort: true
                },
                {
                    title: '成分',
                    field: 'drawDate',
                    sort: true
                },
                {
                    title: '操作',
                    toolbar: '#operation',
                    align: "center"
                }
            ]
        ],
        limits: [10, 15, 20, 25, 50, 100],
        limit: 10,
        page: true,
        skin: 'line',
        where: {
            username: $("#username").val(),
            sex: $("#sex").val(),
            age: $("#age").val(),
            address: $("#address").val(),
            phone: $("#phone").val()
        }, //如果无需传递额外参数，可不加该参数
        sort: true
    });

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        //执行搜索重载
        table.reload('twoColorBallTable', {
            where: {
                username: $("#username").val(),
                sex: $("#sex").val(),
                age: $("#age").val(),
                address: $("#address").val(),
                phone: $("#phone").val()
            }
        });
        return false;
    });

    /**
     * toolbar监听事件
     */
    table.on('toolbar(twoColorBallTableFilter)', function (obj) {
        if (obj.event === 'add') {  // 监听添加操作
            var index = rc.openSaveDialog("/recruit/forecast/twoColorBall/form/add", "新建用户信息")
            $(window).on("resize", function () {
                layer.full(index);
            });
        } else if (obj.event === 'edit') {  // 监听修改操作
            let ids = getIdSelections(table) + "";
            let idArr = ids.toString().split(",");
            if (idArr[1]) {
                rc.alert("只能选择一条数据")
            } else if (ids.length <= 0) {
                rc.alert("请至少选择一条数据")
            } else if (idArr[0]) {
                ids = idArr[0];
                rc.openSaveDialog('/recruit/forecast/twoColorBall/form/edit?id=' + ids, "编辑用户信息");
            }
            $(window).on("resize", function () {
                layer.full(index);
            });
        } else if (obj.event === 'view') {  // 监听查看操作
            let ids = getIdSelections(table);
            let idArr = ids.toString().split(",");
            if (idArr[1]) {
                rc.alert("只能选择一条数据")
            } else if (ids.length <= 0) {
                rc.alert("请至少选择一条数据")
            } else if (idArr[0]) {
                ids = idArr[0];
                rc.openViewDialog('/recruit/forecast/twoColorBall/form/view?id=' + ids, "查看用户信息");
            }
            $(window).on("resize", function () {
                layer.full(index);
            });
        } else if (obj.event === 'delete') {  // 监听删除操作
            let ids = getIdSelections(table);
            if (ids == null || ids == '') {
                rc.alert("请至少选择一条数据")
            } else {
                rc.post("/recruit/forecast/twoColorBall/deleteByPhysics?ids=" + ids, '',function (data) {
                    if(data.code == 200){
                        //执行搜索重载
                        refresh();
                        rc.alert(data.msg);
                    }else{
                        rc.alert(data.msg);
                    }
                });
            }
        } else if (obj.event === 'import') {  // 监听删除操作
            rc.openImportDialog("/recruit/forecast/twoColorBall/importTemplate", "/recruit/forecast/twoColorBall/importFile")
        } else if (obj.event === 'export') {  // 监听删除操作
            rc.downloadFile("/recruit/forecast/twoColorBall/exportFile?" + $("#searchForm").serialize());
        }
    });

    table.on('tool(twoColorBallTableFilter)', function (obj) {
        var id = obj.data.id;
        var index = rc.openSelectionDialog("/recruit/forecast/twoColorBall/addRolePage?id=" + id, "设置角色")
        $(window).on("resize", function () {
            layer.full(index);
        });
        return false;
    });
});

/**
 * 获取layui table 复选框的id
 * @param table -- table = layui.table;
 * @param tableId -- layui table 的id
 * @returns {string}
 */
function getIdSelections(table) {
    var checkStatus = table.checkStatus('twoColorBallTable'),
        data = checkStatus.data;
    let ids = "";
    for (let i = 0; i < data.length; i++) {
        ids = ids + data[i].id + ",";
    }
    ;
    return ids;
}

function refresh() {
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        //执行搜索重载
        table.reload('twoColorBallTable', {
            page: {
                curr: 1
            }
            , where: {

            }
        }, 'data');
    })
}