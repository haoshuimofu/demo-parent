<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/common.jsp"%>
<link type="text/css" rel="stylesheet" href="${staticPath }/resources/plugins/Validform/css/Validform.css" />
<script type="text/javascript" src="${staticPath }/resources/plugins/Validform/js/Validform_v5.3.2.js" />
<script type="text/javascript">
	$(function(){
		//添加或修改表单校验
		$("#resourceForm").Validform({
			btnSubmit:"#confirmResourceBtn", 
			btnReset:"#cancelResourceBtn",
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
					$("#resourceDialog").dialog("close");
					$('#resources_treegrid').treegrid('reload');
				}else {
					$.messager.alert('提示',data.message,'error');
				}
			}
		});
	});
	//添加操作列
	function formatOperate(val,row,index){  
	    return '<a href="#" onclick="dialogEditResource('+row.id+')">编辑</a>&nbsp;&nbsp;'+
	    '<a href="#" onclick="dialogAddResource('+row.id+')">添加下级</a>';  
	}
	// 弹出编辑资源
	function dialogEditResource(id){
		$.ajax({
			url:"${basePath}/resource/loadResource.json",
			data:{"id":id},
			dataType:"json",
			success:function(resource){
				if(typeof resource.id == "undefined"){
					$.messager.alert('提示',"未找到相应的资源！",'error');
				}else{
					var $resourceForm = $("#resourceForm");
					$resourceForm.attr("action", "${basePath}/resource/editResource.json");
					$resourceForm.find("input[name='parentId']").val("");
					$resourceForm.find("input[name='id']").val(resource.id);
					$resourceForm.find("input[name='name']").val(resource.name);
					$resourceForm.find("select[name='type']").val(resource.type);
					$resourceForm.find("input[name='path']").val(resource.path);
					$resourceForm.find("textarea[name='description']").val(resource.description);
					var $resourceDialog = $('#resourceDialog');
					$resourceDialog.show();
					$resourceDialog.dialog({
					    title: '编辑资源',
					    width: 500,
					    height: 400,
					    closed: false,
					    modal: true
					});
				}
			},
			error:function(){
				$.messager.alert('提示',data.message,'error');
			}
		});
	}
	//弹出添加下级资源框
	function dialogAddResource(id){
		var $resourceForm = $("#resourceForm");
		$resourceForm.attr("action", "${basePath}/resource/addResource.json");
		$resourceForm.find("input[name='parentId']").val(id);
		$resourceForm.find("input[name='id']").val("");
		$resourceForm.find("input[name='name']").val("");
		$resourceForm.find("select[name='type']").val("");
		$resourceForm.find("input[name='path']").val("");
		$resourceForm.find("textarea[name='description']").val("");
		var $resourceDialog = $('#resourceDialog');
		$resourceDialog.show();
		$resourceDialog.dialog({
		    title: '添加资源',
		    width: 500,
		    height: 400,
		    closed: false,
		    modal: true
		});
	}
	//刷新资源treegird控件
	function reloadResources(){
		$('#resources_treegrid').treegrid('reload');
	}
</script>
<div>
	<a href="javascript:dialogAddResource('');" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增一级资源</a>
	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>
	<a href="javascript:reloadResources();" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
</div>
<table id="resources_treegrid" class="easyui-treegrid" data-options="url:'${basePath}/resource/loadAllResources.json',idField:'id',treeField:'name'">
    <thead>
        <tr>
            <th data-options="field:'name',width:180">资源名称</th>
            <th data-options="field:'type',width:60,align:'right'">资源类型</th>
            <th data-options="field:'path',width:200">资源请求路径</th>
            <th data-options="field:'description',width:150">资源描述</th>
            <th data-options="field:'_operate',align:'center',formatter:formatOperate">详细信息</th>  
        </tr>
    </thead>
</table>
<!-- dialog -->
<div id="resourceDialog" style="display:none;">
	<form action="" method="post" id="resourceForm">
		<input type="hidden" name="parentId" value="" />
		<input type="hidden" name="id" value="" />
	    <p>
	    	<label for="name">资源名称：</label><input type="text" name="name" datatype="*" nullmsg="请输入资源名称！"/>
	    	<span class="Validform_checktip"></span>
	    </p>
	    <p>
	    	<label for="mark">自己类型：</label>
	    	<select name="type">
	    		<option value="">---请选择---</option>
	    		<option value="menu">菜单</option>
	    		<option value="function">功能</option>
	    	</select>
	    	<span class="Validform_checktip"></span>
	    </p>
	     <p>
	    	<label for="path">资源路径：</label><input type="text" name="path" />
	    </p>
	    <p>
	    	<label for="description">资源表述：</label><textarea name="description"></textarea>
	    </p>
	    <p>
	    	<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-ok" id="confirmResourceBtn">提交</a>
	    	<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" id="cancelResourceBtn">取消</a>
	    </p>
	</form>
</div>