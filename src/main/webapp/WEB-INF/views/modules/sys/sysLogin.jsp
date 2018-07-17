<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta name="decorator" content="blank"/>
	<link  rel="stylesheet" href="${ctxStatic}/common/home.css" type="text/css" />
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
		
		function correctPNG() 
		   {
		   for(var i=0; i<document.images.length; i++)
		      {
		   var img = document.images[i]
		   var imgName = img.src.toUpperCase()
		   if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
		      {
		   var imgID = (img.id) ? "id='" + img.id + "' " : ""
		   var imgClass = (img.className) ? "class='" + img.className + "' " : ""
		   var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' "
		   var imgStyle = "display:inline-block;" + img.style.cssText 
		   if (img.align == "left") imgStyle = "float:left;" + imgStyle
		   if (img.align == "right") imgStyle = "float:right;" + imgStyle
		   if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle  
		   var strNewHTML = "<span " + imgID + imgClass + imgTitle
		   + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
		      + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
		   + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
		   img.outerHTML = strNewHTML
		   i = i-1
		      }
		      }
		   }
		window.attachEvent("onload", correctPNG);
		
	</script>
</head>
<body>
	<form id="loginForm" class="form-signin" action="${ctx}/login"
		method="post">
		<div id="box">
			<div id="logo" align="center">
				<img src="${ctxStatic}/images/logo.png" width="450" height="39" />
			</div>
			<div id="block" style="margin-bottom:10px;">
				<div id="userLogin">
					<img src="${ctxStatic}/images/user-login.png" width="194" height="36" />
				</div>
				<div id="login">
					<div id="left">
						<img src="${ctxStatic}/images/yaoshi.png" width="115" height="128" />
					</div>
					<div id="right">
						<div class="field ph-hide">
					 <div class="form-group">
					    <label for="firstname" class="col-sm-2 control-label">用户名:</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" id="username" name="username" class="login-text J_UserName" maxlength="32" tabindex="1" placeholder="请输入名字" value="${username}">
					    </div>
					    </div>
					  </div>  
						<div class="field">
							<label id="password-label">密 码：</label> 
							<input type="password" id="password" name="password"
								class="login-text J_UserName" value="" maxlength="32"
								tabindex="1" placeholder="请输入密码"/>
						</div>
					</div>
				</div>
				<div id="bottom">
					<button type="submit" class="J_Submit" tabindex="5">登&nbsp;录</button>
				</div>
				<div class="footer" style="margin-top:10px;">Copyright &copy;
				2012-${fns:getConfig('copyrightYear')} 江西云澳科技有限公司</div>
		</div>
			</div>
			
	</form>

</body>
</html>