$(function(){
	$("#loginForm").Validform({
		btnSubmit:"#loginBtn", 
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
				var objtip=o.obj.siblings(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
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
			console.log(data);

			// window.location.href = "/index/view"
		}

	})
});