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
		<li><a href="${ctx}/experiment/teacherComment/">单表列表</a></li>
		<li class="active"><a href="${ctx}/experiment/teacherComment/form?id=${ExperimentRecord.id}">单表<shiro:hasPermission name="experiment:teacherComment:edit">${not empty experimentRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="experiment:teacherComment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="experimentRecord" action="${ctx}/experiment/teacherComment/save" method="post" class="form-horizontal">
		 <form:hidden path="id" />  
		<input type="hidden" value="<fmt:formatDate value="${experimentRecord.experimentEndtime}" pattern="yyyy-MM-dd"/>" name="experimentEndtime"/>
		<input type="hidden" value="<fmt:formatDate value="${experimentRecord.experimentStarttime}" pattern="yyyy-MM-dd"/>" name="experimentStarttime"/>
		<input type="hidden" value="${experimentRecord.experimentInfoId}" name="experimentInfoId"/>
		<c:forEach items="${studentInfos}" var="experimentRecord">
		<input id="pageNo" name="teacherIds" type="hidden" value="${experimentRecord.teacherId}"/>
		</c:forEach>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">实验名称：</label>
			<div class="controls">
				${experimentRecord.experimentName}
		</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">学生名称：</label>
 			<div class="controls"> 
 				${experimentRecord.studentName}
<!--  				<span class="help-inline"><font color="red">*</font> </span>  -->
 			</div> 
		</div>
		<div class="control-group">
			<label class="control-label">值班老师：</label>
 			<div class="controls"> 
 				${experimentRecord.teacherName}
 			</div> 
		</div>
		<div class="control-group">
			<label class="control-label">实验完成情况：</label>
			<div class="controls">
				<form:textarea path="experimentCompletion" readonly="true" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验结果：</label>
			<div class="controls">
				<a id="resultUrl" href="javascript:void(0)" onclick="result('${experimentRecord.experimentResultUrl}')">点击展示实验结果</a>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验得分：</label>
			<div class="controls">
				<form:input path="experimentScore" htmlEscape="false" class="input-xlarge"  />
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验评语：</label>
			<div class="controls">
				<form:textarea path="experimentComment" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="experiment:teacherComment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script type="text/javascript">
	var iWidth=1300; 
    var iHeight=700;  
    var iTop = (window.screen.availHeight-30-iHeight)/2; 
    var iLeft = (window.screen.availWidth-10-iWidth)/2;
     function result(url){
    	   window.open(url,"newwindow","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft +"toolbar=0, menubar=0, scrollbars=0, resizable=0,location=0, status=0");  
     } 
     
    /*  $("#resultUrl").click(function(){
    	 alert('${experimentRecord.experimentResultUrl}');
    	 top.$.jBox.open("iframe:"+'${experimentRecord.experimentResultUrl}', "实验结果",1000,$(top.document).height()-200,{
				buttons:{"关闭":true},submit:function(v, h, f){
				} 
			});
		}); */
</script>
</body>

</html>