<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
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
		<li class="active"><a href="${ctx}/sys/teacher/">单表列表</a></li>
		<shiro:hasPermission name="sys:teacher:edit"><li><a href="${ctx}/sys/teacher/form">单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="teacher" action="${ctx}/sys/teacher/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学生ID：</label>
				<sys:treeselect id="user" name="user.id" value="${teacher.user.id}" labelName="user.name" labelValue="${teacher.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学生ID</th>
				<th>擅长技能</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:teacher:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="teacher">
			<tr>
				<td><a href="${ctx}/sys/teacher/form?id=${teacher.id}">
					${teacher.user.name}
				</a></td>
				<td>
					${teacher.skill}
				</td>
				<td>
					${teacher.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${teacher.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${teacher.updateBy.id}
				</td>
				<td>
					<fmt:formatDate value="${teacher.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${teacher.remarks}
				</td>
				<shiro:hasPermission name="sys:teacher:edit"><td>
    				<a href="${ctx}/sys/teacher/form?id=${teacher.id}">修改</a>
					<a href="${ctx}/sys/teacher/delete?id=${teacher.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>