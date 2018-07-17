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
		<li class="active"><a href="${ctx}/experiment/experimentInfo/">单表列表</a></li>
		<shiro:hasPermission name="experiment:experimentInfo:edit"><li><a href="${ctx}/experiment/experimentInfo/form">单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="experimentInfo" action="${ctx}/experiment/experimentInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/> 
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%--  <li><label>所属专业：</label>
				<sys:treeselect id="office" name="office.id" value="${experimentInfo.office.id}" labelName="office.name" labelValue="${experimentInfo.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%> 
			<li><label>实验名称：</label>
				<form:input path="experimentName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>所属专业</th> -->
				<th>实验名称</th>
				<th>实验目的</th>
				<th>实验内容</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新者</th>
				<th>更新时间</th>
				<shiro:hasPermission name="experiment:experimentInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="experimentInfo">
			<tr>
				<%-- <td><a href="${ctx}/experiment/experimentInfo/form?id=${experimentInfo.id}">
					${experimentInfo.office.name}
				</a></td> --%>
				<td>
					${experimentInfo.experimentName}
				</td>
				<td>
					${experimentInfo.experimentObjective}
				</td>
				<td>
					${experimentInfo.experimentContent}
				</td>
				<td>
					${fns:getUserById(experimentInfo.createBy.id).name}
				</td>
				<td>
					<fmt:formatDate value="${experimentInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getUserById(experimentInfo.updateBy.id).name}
				</td>
				<td>
					<fmt:formatDate value="${experimentInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="experiment:experimentInfo:edit"><td width="60px">
    				<a href="${ctx}/experiment/experimentInfo/form?id=${experimentInfo.id}">修改</a>
					<a href="${ctx}/experiment/experimentInfo/delete?id=${experimentInfo.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>