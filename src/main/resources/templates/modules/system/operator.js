layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    table.render({
        elem: '#operatorTable',
        url: '/recruit/system/operator/data',
        method: 'GET',
        page: true, //开启分页
        request: {
            pageName: 'pageNo', // page
            limitName: 'pageSize' // limit
        },//重命名参数名称
        limit: 20,
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
                title: '用户名',
                field: 'username'
            },
            {
                title: '性别',
                field: 'sex',
                sort: true
            },
            {
                title: '年龄',
                field: 'age'
            },
            {
                title: '住址',
                field: 'address'
            },
            {
                title: '电话',
                field: 'phone',
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
        limit: 15,
        page: true,
        skin: 'line'
    });

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var result = JSON.stringify(data.field);
        layer.alert(result, {
            title: '最终的搜索信息'
        });

        //执行搜索重载
        table.reload('operatorTable', {
            page: {
                curr: 1
            }
            , where: {
                searchParams: result
            }
        }, 'data');

        return false;
    });

    /**
     * toolbar监听事件
     */
    table.on('toolbar(operatorTableFilter)', function (obj) {
        if (obj.event === 'add') {  // 监听添加操作
            var index = layer.open({
                title: '添加用户',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: "/recruit/system/operator/form/add",
            });
            $(window).on("resize", function () {
                layer.full(index);
            });
        } else if (obj.event === 'delete') {  // 监听删除操作
            var checkStatus = table.checkStatus('operatorTable')
                , data = checkStatus.data;
            layer.alert(JSON.stringify(data));
        }
    });

    //监听表格复选框选择
    table.on('checkbox(operatorTableFilter)', function (obj) {
        console.log(obj)
    });

    table.on('tool(operatorTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {

            var index = layer.open({
                title: '编辑用户',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: '/backstage/layui/page/table/edit.html',
            });
            $(window).on("resize", function () {
                layer.full(index);
            });
            return false;
        } else if (obj.event === 'delete') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                layer.close(index);
            });
        }
    });

});