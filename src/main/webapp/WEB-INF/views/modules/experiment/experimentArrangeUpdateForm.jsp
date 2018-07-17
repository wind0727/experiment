<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
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
		<li><a href="${ctx}/experiment/experimentArrange/">单表列表</a></li>
		<li class="active"><a href="${ctx}/experiment/experimentArrange/form?id=${experimentArrange.id}">单表<shiro:hasPermission name="experiment:experimentArrange:edit">${not empty experimentArrange.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="experiment:experimentArrange:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="experimentArrange" action="${ctx}/experiment/experimentArrange/update" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="experimentName" value="${experimentArrange.experimentName} "/>
		<input type="hidden" name="beginExperimentStarttime" value="${experimentArrange.beginExperimentStarttime}"/>
		<input type="hidden" name="endExperimentStarttime" value="${experimentArrange.endExperimentStarttime}"/>
		<input type="hidden" name="experimentInfoId" value="${experimentArrange.experimentInfoId}"/>
		<sys:message content="${message}"/>	
		<c:if test="${experimentArrange.id != null}">
		   <div class="control-group">
			<label class="control-label">实验名称：</label>
			<div class="controls">
			   ${experimentArrange.experimentName} 
			</div>
		</div> 
		</c:if>	
		<c:if test="${experimentArrange.id == null}">
		 <div class="control-group">
			<label class="control-label">实验名称：</label>
			<div class="controls">
				<form:select path="experimentInfoId" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${experiments}" itemLabel="experimentName" itemValue="experimentInfoId" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> 
	   </c:if>	
		<div class="control-group">
			<label class="control-label">实验室名称 ：</label>
			<div class="controls">
				<form:input path="laboratoryName" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>  
		<div class="control-group">
			<label class="control-label">实验室地址：</label>
			<div class="controls">
				<form:textarea path="laboratoryAddress" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验开始时间：</label>
			<div class="controls">
				<input name="experimentStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${experimentArrange.experimentStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验结束时间：</label>
			<div class="controls">
				<input name="experimentEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${experimentArrange.experimentEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		 <%-- <div class="control-group">
			<label class="control-label">值班老师：</label>
			<div class="controls">
				<form:select path="teacherId" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${teacher}" itemLabel="teacherName" itemValue="teacherId" htmlEscape="false"  />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>  --%>
		<div class="control-group">
			<label class="control-label">参加人数：</label>
			<div class="controls">
				<form:input path="count" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="experiment:experimentArrange:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>