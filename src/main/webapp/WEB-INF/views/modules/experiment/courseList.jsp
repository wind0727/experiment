<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
    <%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/experiment/course/">单表列表</a></li>
		<shiro:hasPermission name="experiment:course:edit"><li><a href="${ctx}/experiment/course/form">单表添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="choiceCourse" action="${ctx}/experiment/choiceCourse/" method="post" class="breadcrumb form-search">
	
		<table id="treeTable" class="table table-striped table-bordered table-condensed hide" style="display: table;">
			<thead><tr><th>课程名称</th><th>课程介绍</th><th>教师名称</th><th>关联的实验</th><th>备注信息</th><th>操作</th></tr></thead>	
				<tbody><c:forEach items="${courseList}" var="course">
					<tr id="${course.id}"  pId="${course.teacherId}">
						<td>${course.courseName}</td>
						<td>${course.introduce}</td>
						<td>${course.teacherName}</td>
						<td>${course.experiments}</td>
						<td>${course.remarks}</td>
						<shiro:hasPermission name="experiment:course:edit">
							<td width="150px">
				    			<a href="${ctx}/experiment/course/form?id=${course.id}">修改</a>
								<a href="${ctx}/experiment/course/delete?id=${course.id}" onclick="return confirmx('确认要删除该课程吗？', this.href)">删除</a>
								<c:if test="${course.teacherId != '0'}">
									<a id="choiseExperiment" href="javascript:void(0)" onclick="choiseExperiment('${course.id}')">关联实验</a>
								</c:if>
							</td>
						</shiro:hasPermission>
					</tr>
				</c:forEach></tbody>
		</table>
	</form:form>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 4}).show();
			
		});
		
        function choiseExperiment(id){
        	top.$.jBox.open("iframe:${ctx}/experiment/course/choiseExperiment?courseId="+id, "实验选择",1000,600,{
				buttons:{"关闭":true},submit:function(v, h, f){
				} 
			});
        }
    </script>
</body>
</html>