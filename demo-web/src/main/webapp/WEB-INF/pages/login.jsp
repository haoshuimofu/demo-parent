<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录${num }</title>
		<%--<link href="${staticPath }/resources/css/login.css" rel="stylesheet" type="text/css" />--%>
		<link href="${staticPath }/resources/plugins/Validform/css/Validform.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${staticPath }/resources/js/jquery-1.11.3.min.js"></script>
		<%--<script type="text/javascript" src="${staticPath }/resources/plugins/Validform/js/Validform_v5.3.2.js"></script>--%>
		<%--<script type="text/javascript" src="${staticPath }/resources/js/login.js"></script>--%>
		<script type="text/javascript">
            $(function(){
                $("#loginBtn").bind("click",function () {
                    $.ajaxSetup({
                        contentType : 'application/json'
                    });
                    var password = $("#password").val();
                    var params = {username:"wude",password:password};
                    $.ajax({
                        type:"post",
                        url:"${basePath }/login",
                        processData:false,
//                        data:"username=admin&password=123456",
                        data:JSON.stringify(params),
                        dataType:"json",
                        success:function (data) {
                            console.log(data);
                            window.location.href = "${basePath}/channel/list"
                        }
                    });

                });


                $("#authcBtn").bind("click",function () {
                    $.ajaxSetup({
                        contentType : 'application/json'
                    });
                    $.ajax({
                        type:"post",
                        url:"${basePath }/index/init",
                        processData:false,
//                        data:JSON.stringify(params),
                        dataType:"json",
                        success:function (data) {
                            console.log(data);
                            window.location.href = "${basePath}/channel/list"
                        }
                    });

                });
            });
		</script>
	</head>
	<body>
		<div class="login-header">
			<img alt="" src="${staticPath15 }/resources/images/top034.gif">
		</div>
		<div class="login-main">
			<%--<div class="login-logo">
				<img width="107" height="97" src="${staticPath1 }/resources/images/ico13.gif">
			</div>
			<div class="login-divide"><img width="5" height="292" src="${staticPath1 }/resources/images/line01.gif"></div>--%>
			<div class="login-form">
				<%--<h1>用户登录</h1>--%>
	            <form action="${basePath }/login/submit" method="post" id="loginForm">
	                <p>
	                	<label for="" class="form-label">用户名：</label>
	                    <input type="text" name="username" datatype="*" nullmsg="请输入用户名！" value="wude"/>
						<span class="Validform_checktip"></span>
	                </p>
	                <p>
	                	<label for="" class="form-label">密码：</label>
	                    <input type="password" name="password" id="password" datatype="*" nullmsg="请输入登录密码！" value=""/>
						<span class="Validform_checktip"></span>
	                </p>
	                <%--<p>
	                	<label for="" class="form-label">验证码图片：</label>
	                	<img  src="${staticPath }/resources/images/pic05.gif">
	                	<a class="login-text03" href="#">看不清楚，换张图片</a>
	                </p>--%>
	               	<p>
	               		<label class="form-label">验证码：</label>
	               		<input type="text" />
	               	</p>
	                <p>
	                    <a href="javascript:void(0);" id="loginBtn">登录</a>

						<a href="javascript:void(0);" id="authcBtn">异步请求authc资源</a>
	                </p>
	            </form>
			</div>
		</div>
	</body>
</html>