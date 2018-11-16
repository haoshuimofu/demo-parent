<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>角色管理</title>
		<link rel="stylesheet" type="text/css" href="${staticPath }/resources/css/base.css">
		<link rel="stylesheet" type="text/css" href="${staticPath }/resources/framework/jquery-easyui-1.4.4/themes/metro/easyui.css">
	    <link rel="stylesheet" type="text/css" href="${staticPath }/resources/framework/jquery-easyui-1.4.4/themes/icon.css">
	    <script type="text/javascript" src="${staticPath }/resources/framework/jquery-easyui-1.4.4/jquery.min.js"></script>
	    <script type="text/javascript" src="${staticPath }/resources/framework/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	    <link rel="stylesheet" href="${staticPath }/resources/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="${staticPath }/resources/plugins/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="${staticPath }/resources/plugins/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
	    <link rel="stylesheet" href="${staticPath }/resources/plugins/Validform/css/Validform.css" type="text/css">
		<script type="text/javascript" src="${staticPath }/resources/plugins/Validform/js/Validform_v5.3.2.js"></script>
		<script type="text/javascript">
			$(function(){
				//弹出新增角色框
				$("#addRoleBtn").click(function(){
					//清空输入框内信息
					var $roleForm = $("#roleForm");
					$roleForm.find("input[name='id']").val("");
					$roleForm.find("input[name='name']").val("");
					$roleForm.find("input[name='mark']").val("");
					$roleForm.find("textarea[name='description']").val("");
					$roleForm.attr("action", "${basePath}/role/addRole.json");
					var $roleDialog = $('#roleDialog');
					$roleDialog.show();
					$roleDialog.dialog({
					    title: '添加角色',
					    width: 500,
					    height: 400,
					    closed: false,
					    modal: true
					});
				});
				// 刷新角色列表
				$("#reloadRolesBtn").click(function(){
					$("#roles-datagrid").datagrid("reload");
				});
				$("#roleForm").Validform({
					btnSubmit:"#confirmRoleBtn", 
					btnReset:"#cancelRoleBtn",
					tiptype:1, 
					ignoreHidden:false,
					dragonfly:false,
					tipSweep:true,
					label:".label",
					showAllError:true,
					postonce:true,
					ajaxPost:true,
					tiptype:function(msg,o,cssctl){
						if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
							if(o.obj.is(":visible")){
								var objtip=o.obj.siblings(".Validform_checktip");
								cssctl(objtip,o.type);
								objtip.text(msg);
							}
						}else{
							var objtip=o.obj.find("#formmsg");
							cssctl(objtip,o.type);
							objtip.text(msg);
						}
					},
					beforeCheck:function(curform){
					},
					beforeSubmit:function(curform){
					},
					callback:function(data){
						if(data.result == 1){
							$("#roleDialog").dialog("close");
							$("#roles-datagrid").datagrid("reload");
						}else {
							$.messager.alert('提示',data.message,'error');
						}
					}
				});
			});
			//添加操作列
			function formatOper(val,row,index){  
			    return '<a href="#" onclick="editRole('+row.id+')">编辑</a><a href="#" onclick="assignResources('+row.id+')">分配资源</a>';  
			}
			//弹出编辑角色对话框
			function editRole(id){
				$.ajax({
					url:"${basePath}/role/loadById.json",
					data:{"id":id},
					dataType:"json",
					success:function(role){
						if(typeof role.id == "undefined"){
							$.messager.alert('提示','找不到相应的角色信息!','info');
							return;
						}
						var $roleDialog = $('#roleDialog');
						$roleDialog.show();
						$roleDialog.dialog({
						    title: '编辑角色',
						    width: 500,
						    height: 400,
						    closed: false,
						    modal: true
						});
						//填充角色信息
						var $roleForm = $("#roleForm");
						$roleForm.find("input[name='id']").val(role.id);
						$roleForm.find("input[name='name']").val(role.name);
						$roleForm.find("input[name='mark']").val(role.mark);
						$roleForm.find("textarea[name='description']").val(role.description);
						$roleForm.attr("action", "${basePath}/role/editRole.json");
					},
					error:function(){
						$.messager.alert('服务器错误，请稍后重试!','error');
					}
				});
			}
			// 弹出资源树，编辑角色
			function assignResources(id){
				var $assignResourceDiglog = $('#assignResourceDiglog');
				$assignResourceDiglog.find("input[name='roleId']").val(id);
				$assignResourceDiglog.show();
				$assignResourceDiglog.dialog({
				    title: '分配资源',
				    width: 500,
				    height: 400,
				    closed: false,
				    modal: true
				});
				$.ajax({
					url:"${basePath}/resource/findAllLeftJoinRole.json",
					data:{"roleId":id},
					dataType:"json",
					type:"get",
					success:function(data){
						$.fn.zTree.init($("#roleResourceTree"), setting, data);
					}
				});
			}
			// tree
			var setting = {
				check: {
					enable: true,
					chkStyle: "checkbox",
					chkboxType: { "Y": "p", "N": "s" }
				},
				data: {
					simpleData: {
						enable: true // 简单格式
					}
				}
			};
			//确定角色资源分配
			function confirmAssignResources(obj){
				var treeObj = $.fn.zTree.getZTreeObj("roleResourceTree");
				var nodes = treeObj.getCheckedNodes(true);
				var resourceIds = "";
				$(nodes).each(function(i, node){
					resourceIds += node.id + ";";
				});
				$.ajax({
					url:"${basePath}/resource/assignRoleResources.json",
					data:{roleId:$(obj).prev().val(), resourceIds:resourceIds},
					dataType:"json",
					type:"post",
					success:function(data){
						var $assignResourceDiglog = $("assignResourceDiglog");
						$("assignResourceDiglog").find("input[name='roleId']").val("");
						$("assignResourceDiglog").dialog("close");
					}
				});
			}
		</script>
	</head>
	<body>
		<div>
			<a id="addRoleBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>
			<a id="reloadRolesBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
		</div>
		<table id="roles-datagrid" class="easyui-datagrid" data-options="url:'${basePath}/role/loadRoles.json',fitColumns:true,singleSelect:false">
		    <thead>
		        <tr>
		        	<th data-options="field:'ck',checkbox:true"></th>
		        	<th data-options="field:'id',width:40">ID</th>
		            <th data-options="field:'name',width:100">角色名称</th>
		            <th data-options="field:'mark',width:100">角色标识</th>
		            <th data-options="field:'description',width:100">角色描述</th>
		            <th data-options="field:'createBy',width:100">创建人</th>
		            <th data-options="field:'createTime',width:100">创建时间</th>
		            <th data-options="field:'updateBy',width:100">最近修改人</th>
		            <th data-options="field:'updateTime',width:100">最近修改时间</th>
		            <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>  
		        </tr>
		    </thead>
		</table>
		<!-- dialog -->
		<div id="roleDialog" style="display:none;">
			<form action="${basePath }/role/addRole.json" method="post" id="roleForm">
				<input type="hidden" name="id" value="" />
			    <p>
			    	<label for="name">角色名称：</label>
			    	<input type="text" name="name" datatype="*" nullmsg="请输入角色名称！"/>
			    	<span class="Validform_checktip"></span>
			    </p>
			    <p>
			    	<label for="mark">角色标识：</label><input type="text" name="mark" datatype="*" nullmsg="请输入角色标识！"/>
			    	<span class="Validform_checktip"></span>
			    </p>
			    <p>
			    	<label for="description">角色表述：</label><textarea name="description"></textarea>
			    </p>
			    <p>
			    	<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-ok" id="confirmRoleBtn">提交</a>
			    	<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" id="cancelRoleBtn">取消</a>
			    </p>
			</form>
		</div>
		<div id="assignResourceDiglog" style="display:none;">
			<ul id="roleResourceTree" class="ztree"></ul>
			<input type="hidden" name="roleId" value="">
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="confirmAssignResources(this)">确定</a>
		</div>
	</body>
</html>