$(document).ready(function () {
    layui.use(['table', 'treetable'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var treetable = layui.treetable;

        // 渲染表格
        layer.load(2);
        treetable.render({
            treeColIndex: 1,
            treeSpid: -1,
            treeIdName: 'id',
            treePidName: 'parentId',
            elem: '#menuTable',
            url: '/recruit/system/menu/data',
            treeDefaultClose: true,	//是否默认折叠
            treeLinkage: false,		//父级展开时是否自动展开所有子级
            //toolbar: '#toolbarDemo',
            page: false,
            cols: [
                [
                    {type: 'numbers'},
                    {
                        field: 'title',
                        align: 'left',
                        title: '菜单名称'
                    },
                    {
                        field: 'href',
                        align: 'left',
                        title: '菜单url'
                    },
                    {
                        field: 'icon',
                        align: 'left',
                        title: '菜单图标',
                        templet: function (d) {
                            let strings = d.icon.toString().split(" ");
                            let html = '<i class="' + d.icon + '"></i> ';
                            return html;
                        }
                    },
                    {
                        field: 'sort',
                        align: 'left',
                        title: '排序'
                    },
                    {
                        field: 'isShow',
                        align: 'left',
                        title: '是否展示',
                        templet: function (d) {
                            if (1 == d.isShow) {
                                return '是';
                            } else {
                                return '否';
                            }
                        }
                    },
                    {
                        field: 'type',
                        align: 'left',
                        title: '类型'
                    },
                    {
                        field: 'permission',
                        align: 'left',
                        title: '权限标识'
                    },
                    {
                        field: 'hasChildren',
                        align: 'left',
                        title: '是否有子菜单',
                        templet: function (d) {
                            if (d.hasChildren) {
                                return '是';
                            } else {
                                return '否';
                            }
                        }
                    },
                    {
                        field: 'permission',
                        align: 'left',
                        title: '权限标识',
                        templet: '#auth-state', width: 120, align: 'center', title: '操作'
                    },
                ]
            ],
            done: function () {
                layer.closeAll('loading');
            }
        });

        $('#btn-expand').click(function () {
            treetable.expandAll('#munu-table');
        });

        $('#btn-fold').click(function () {
            treetable.foldAll('#munu-table');
        });
    });
})

function getIdSelections() {
    let ids;
    layui.use(['table', 'treetable'], function () {
        var table = layui.table;

        //监听工具条
        table.on('tool(menuTable)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;

            if (layEvent === 'addChildren') {
                layer.msg('addChildren' + data.id);
                ids = data.id;
            } else if (layEvent === 'edit') {
                layer.msg('修改' + data.id);
            }
        });
    })
    alert(ids)
}

function add() {
    rc.openSaveDialog("/recruit/system/menu/form/add", "添加一级菜单")
}

function edit() {
    let id = getIdSelections().toString();
    let split = id.toString().split(",");
    if (split[1]) {
        rc.alert("只能选择一条数据")
    } else if (id.length <= 0) {
        rc.alert("请至少选择一条数据")
    } else if (split[0]) {
        rc.openSaveDialog("/recruit/system/menu/form/edit?id=" + id, "编辑菜单信息")
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
        rc.openSaveDialog("/recruit/system/menu/form/view?id=" + id, "查看菜单信息")
    }
}

function del() {
    let ids = getIdSelections();
    if (ids == null || ids == '') {
        rc.alert("请至少选择一条数据")
    } else {
        rc.post("/recruit/system/menu/delete?ids=" + ids)
    }
}

function addChildren() {
    let ids = getIdSelections();
    //rc.openSaveDialog("/recruit/system/menu/form/addChildren?parent.id=" + ids, "添加下级菜单")
}
