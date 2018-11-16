<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/pages/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>backstage</title>
    <link rel="stylesheet" type="text/css"
          href="${staticPath }/resources/framework/jquery-easyui-1.4.4/themes/metro/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${staticPath }/resources/framework/jquery-easyui-1.4.4/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${staticPath }/resources/css/base.css">
    <script type="text/javascript" src="${staticPath }/resources/framework/jquery-easyui-1.4.4/jquery.min.js"></script>
    <script type="text/javascript"
            src="${staticPath }/resources/framework/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="${staticPath }/resources/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <script type="text/javascript" src="${staticPath }/resources/plugins/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
    <style type="text/css">
        .ztree li a.level0 {
            width: 100%;
            height: 20px;
            text-align: center;
            display: block;
            background-color: #0B61A4;
            border: 1px silver solid;
        }

        .ztree li a.level0.cur {
            background-color: #66A3D2;
        }

        .ztree li a.level0 span {
            display: block;
            color: white;
            padding-top: 3px;
            font-size: 12px;
            font-weight: bold;
            word-spacing: 2px;
        }

        .ztree li a.level0 span.button {
            float: right;
            margin-left: 10px;
            visibility: visible;
            display: none;
        }

        .ztree li span.button.switch.level0 {
            display: none;
        }
    </style>
    <script type="text/javascript">
        var curMenu = null, zTree_Menu = null;
        var setting = {
            view: {
                showLine: true,
                selectedMulti: false,
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: false //标准数据
                }
            },
            callback: {
                onNodeCreated: this.onNodeCreated,
                beforeClick: this.beforeClick,
                onClick: this.onClick
            }
        };
        var zNodes = null;

        function beforeClick(treeId, node) {
            if (node.isParent) {
                if (node.level === 0) {
                    var pNode = curMenu;
                    while (pNode && pNode.level !== 0) {
                        pNode = pNode.getParentNode();
                    }
                    if (pNode !== node) {
                        var a = $("#" + pNode.tId + "_a");
                        a.removeClass("cur");
                        zTree_Menu.expandNode(pNode, false);
                    }
                    a = $("#" + node.tId + "_a");
                    a.addClass("cur");

                    var isOpen = false;
                    for (var i = 0, l = node.children.length; i < l; i++) {
                        if (node.children[i].open) {
                            isOpen = true;
                            break;
                        }
                    }
                    if (isOpen) {
                        zTree_Menu.expandNode(node, true);
                        curMenu = node;
                    } else {
                        zTree_Menu.expandNode(node.children[0].isParent ? node.children[0] : node, true);
                        curMenu = node.children[0];
                    }
                } else {
                    zTree_Menu.expandNode(node);
                }
            }
            return !node.isParent;
        }

        function onClick(e, treeId, node) {
            if (typeof node.url != "undefined" && $.trim(node.url) != "") {
                var title = node.name;
                var $tabs = $("#tabs");
                if ($tabs.tabs("exists", title)) {
                    $tabs.tabs("select", title);
                } else {
                    var content = '<iframe scrolling="auto" frameborder="0"  src="${basePath}' + node.url + '?version=' + new Date().getTime() + '" style="width:100%;height:100%;"></iframe>';
                    $tabs.tabs('add', {
                        title: title,
                        content: content,
                        href: "${basePath}" + node.path,
                        closable: true,
                        tools: [{
                            iconCls: 'icon-mini-refresh',
                            handler: function () {
                                var tab = $tabs.tabs('getSelected');  // get selected panel
                                $tabs.tabs('update', {
                                    tab: tab,
                                    options: {
                                        content: content
                                    }
                                });
                            }
                        }]
                    });
                }
            }
        }

        $(document).ready(function () {
            //加载菜单树
            $.ajax({
                url: "${basePath}/authc/initMenuTree",
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    zNodes = data;
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                    zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
                    //curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
                    curMenu = zTree_Menu.getNodes()[0].children[0];
                    zTree_Menu.selectNode(curMenu);
                    var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
                    a.addClass("cur");
                }
            });
        });
    </script>
</head>
<body>
<div class="easyui-layout" style="height:700px;">
    <div data-options="region:'north'" style="height:50px">
        <h4 style="float:left;">SpringMVC+Mybatis+Jquery-easyui</h4>
        <div style="float:right;">
            <span>${user.username }</span>
            <a href="${basePath}/logout">注销</a>
        </div>
    </div>
    <div data-options="region:'south',split:true" style="height:50px;">
    </div>
    <!--
    <div data-options="region:'east',split:true" title="East" style="width:100px;"></div>
     -->
    <div data-options="region:'west',split:true" title="菜单" style="width:200px;">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
    <div data-options="region:'center'" style="padding:5px;">
        <div class="easyui-tabs" style="height:100%;" id="tabs">
            <div title="首页" style="padding:10px">
                欢迎！！！
            </div>
        </div>
    </div>
</div>
</body>
</html>