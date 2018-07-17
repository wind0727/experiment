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
		<li><a href="${ctx}/experiment/course/">单表列表</a></li>
		<li class="active"><a href="${ctx}/experiment/course/form?id=${course.id}">单表<shiro:hasPermission name="experiment:course:edit">${not empty course.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="experiment:course:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="course" action="${ctx}/experiment/course/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">课程名称：</label>
			<div class="controls">
				<form:input path="courseName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">课程父ID：</label>
			<div class="controls">
			   <li><sys:treeselect  id="company"  name="parent.id" value="${course.parent.id}" labelName="parent.id" labelValue="${course.parent.courseName}" 
				title="科目"  url="/experiment/course/treeData?type=1"  cssClass="input-small" allowClear="true" /></li> 
			</div>
		</div>
		<!-- <input id="assignButton" class="btn btn-primary" type="submit" value="课程安排"/> -->
		<div class="control-group">
			<label class="control-label">课程介绍：</label>
			<div class="controls">
				<form:input path="introduce" htmlEscape="false" maxlength="1000" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教师名称：</label>
			<div class="controls">
				<form:select path="teacherId" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${teacherList}" itemLabel="teacherName" itemValue="teacherId" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="experiment:course:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script type="text/javascript">
		/* $(function(){ 
			var id = $('#company').val('${course.parent.id}');
			alert(id)
		});	 */
	</script>
</body>
</html>