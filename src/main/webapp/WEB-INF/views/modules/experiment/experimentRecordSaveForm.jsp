<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script src="${ctxStatic}/bootstrap/datepicker/locales/jquery.validate.min.js"></script>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/experiment/experimentRecord/">单表列表</a></li>
		<li class="active"><a href="${ctx}/experiment/experimentRecord/form?id=${experimentRecord.id}">单表<shiro:hasPermission name="experiment:experimentRecord:edit">${not empty experimentRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="experiment:experimentRecord:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="experimentRecord" action="${ctx}/experiment/experimentRecord/save" method="post" class="form-horizontal">
		<%-- <form:hidden path="id" /> --%>
		<sys:message content="${message}"/>		
		 <div class="control-group">
			<label class="control-label">实验名称：</label>
			<div class="controls">
				<form:select path="experimentAppointmentId" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${appointments}" itemLabel="experimentName" itemValue="experimentAppointmentId" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验完成情况：</label>
			<div class="controls">
 				<form:textarea path="experimentCompletion" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验问题及解决方案：</label>
			<div class="controls">
				<form:textarea path="experimentProblem" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
 		<div class="control-group"> 
 			<label class="control-label">实验结果地址：</label> 
 			<div class="controls"> 
 				<%-- <form:input id="url" path="experimentResultUrl" htmlEscape="false" class="input-xlarge "/>  --%>
 				<input type="url" name="experimentResultUrl" id="url" style="width: 460px"/>
 			</div> 
 		</div> 
 		 <!-- <div class="control-group"> --> 
<!-- 			<label class="control-label">实验评语：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:textarea path="experimentComment" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="form-actions">
			<shiro:hasPermission name="experiment:experimentRecord:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"  onclick="return postComment()" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
</body>
</html>