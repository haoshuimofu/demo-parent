<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
	    <link rel="stylesheet" type="text/css" href="${staticPath }/resources/css/base.css">
		<link rel="stylesheet" type="text/css" href="${staticPath }/resources/framework/jquery-easyui-1.4.4/themes/metro/easyui.css">
	    <link rel="stylesheet" type="text/css" href="${staticPath }/resources/framework/jquery-easyui-1.4.4/themes/icon.css">
	    <script type="text/javascript" src="${staticPath }/resources/framework/jquery-easyui-1.4.4/jquery.min.js"></script>
	    <script type="text/javascript" src="${staticPath }/resources/framework/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	    <link rel="stylesheet" href="${staticPath }/resources/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="${staticPath }/resources/plugins/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
	    <link rel="stylesheet" href="${staticPath }/resources/plugins/Validform/css/Validform.css" type="text/css">
		<script type="text/javascript" src="${staticPath }/resources/plugins/Validform/js/Validform_v5.3.2.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#addUserBtn").click(function(){
					var $addUserDialog = $('#addUserDialog');
					$addUserDialog.show();
					$addUserDialog.dialog({
					    title: '添加用户',
					    width: 700,
					    height: 500,
					    closed: false,
					    modal: true
					});
				});
				//添加用户表单校验
				$("#addUserForm").Validform({
					btnSubmit:"#confirmAddUserBtn", 
					//btnReset:"#reset",
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
						if(data.state == 1){
							$("#addUserDialog").dialog("close");
							$("#users-datagrid").datagrid("reload");
						}else {
							$.messager.alert('提示',data.message,'error');
						}
					}
				});
			});
			//用户详细信息
			function formatUserDetail(val,row,index){  
			    return '<a href="#" onclick="loadUserDetail('+row.id+')">查看</a>';  
			}  
		</script>
	</head>
	<body>
		<div style="padding:0px 10px 0px 10px;">
			<span style="float:left;margin-top:5px;">用户列表</span>
			<span style="float:right;">
				<a id="addUserBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增用户</a>
				<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑用户</a>
				<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">封锁账号</a>
			</span>
		</div>
		<table id="users-datagrid" class="easyui-datagrid" style="width:100%;" data-options="url:'${basePath}/user/loadUsers.json',fitColumns:true,singleSelect:false">
		    <thead>
		        <tr>
		        	<th data-options="field:'ck',checkbox:true"></th>
		        	<th data-options="field:'id',width:40">ID</th>
		            <th data-options="field:'username',width:100">用户名</th>
		            <th data-options="field:'nickname',width:100">昵称</th>
		            <th data-options="field:'realname',width:100">真实姓名</th>
		            <th data-options="field:'gender',width:100">性别</th>
		            <th data-options="field:'birthDate',width:100">出生日期</th>
		            <th data-options="field:'cellphone',width:100">手机号码</th>
		            <th data-options="field:'email',width:100">邮箱</th>
		            <th data-options="field:'createTime',width:100">创建时间</th>
		            <th data-options="field:'status',width:100">状态</th>
		            <th data-options="field:'_operate',align:'center',formatter:formatUserDetail">详细信息</th>  
		        </tr>
		    </thead>
		</table>
		<!-- dialog -->
		<div id="addUserDialog" style="display:none;">
			<form action="${basePath }/user/addUser.json" method="post" id="addUserForm">
			    <p>
			    	<label for="username" class="form-label"><i class="required">*</i>用户名：</label>
			    	<input type="text" name="username" datatype="*" nullmsg="请输入用户名！"/>
			    	<span class="Validform_checktip"></span>
			    </p>
			    <p>
			    	<label for="password" class="form-label"><i class="required">*</i>密码：</label>
			    	<input type="password" name="password" datatype="*6-16" nullmsg="请输入密码！"/>
			    	<span class="Validform_checktip"></span>
			    </p>
			    <p>
			    	<label for="repassword" class="form-label"><i class="required">*</i>确认密码：</label>
			    	<input type="password" name="repassword" datatype="*6-16" recheck="password" nullmsg="请输入确认密码！" errormsg="两次密码不一致，请重新输入！"/>
			    	<span class="Validform_checktip"></span>
			    </p>
			    <p>
			    	<label for="nickname" class="form-label">昵称：</label>
			    	<input type="text" name="nickname" />
			    </p>
			    <p>
			    	<label for="realname" class="form-label">真实姓名：</label>
			    	<input type="text" name="realname" />
			    </p>
			    <p>
			    	<label for="gender" class="form-label">性别：</label>
			    	<input type="radio" name="gender" value="M" />男
			    	<input type="radio" name="gender" value="F" />女
			    </p>
			    <p>
			    	<label for="cellphone" class="form-label">手机号码：</label>
			    	<input type="text" name="cellphone" ignore="ignore" datatype="m" errormsg="手机号码格式错误！"/>
			    	<span class="Validform_checktip"></span>
			    </p>
			    <p>
			    	<label for="email" class="form-label">邮箱：</label>
			    	<input type="text" name="email" ignore="ignore" datatype="e" errormsg="邮箱格式错误！"/>
			    	<span class="Validform_checktip"></span>
			    </p>
			    <p>
			    	<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-ok" id="confirmAddUserBtn">提交</a>
			    	<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-cancel">取消</a>
			    </p>
			</form>
		</div>
	</body>
</html>