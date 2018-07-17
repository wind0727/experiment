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
		<li class="active"><a href="${ctx}/experiment/experimentHistory/">单表列表</a></li>
		<shiro:hasPermission name="experiment:experimentHistory:edit"><li><a href="${ctx}/experiment/experimentHistory/form">单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="experimentRecord" action="${ctx}/experiment/experimentHistory/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%-- <ul class="ul-form">
			<li><label>实验ID：</label>
				<form:select path="experimentInfoId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${experimentname}" itemLabel="experimentName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>实验时间：</label>
				<input id="beginExperimentStarttime" name="beginExperimentStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentArrange.beginExperimentStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input id="endExperimentStarttime"  name="endExperimentStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentArrange.endExperimentStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> --%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>实验名称</th>
				<th>值班老师</th>
				<th>实验完成情况</th>
			   <!--  <th>实验完成次数</th> -->
			    <th>实验开始时间</th>
				<th>实验问题</th>
				<th>实验得分</th>
				<th>实验评语</th>
				<shiro:hasPermission name="experiment:experimentHistory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${studentInfos}" var="experimentRecord">
			<tr>
				<td>
					${experimentRecord.experimentName}
				</td>
				<td>
					${experimentRecord.teacherName}
				</td>
				<td>
					${experimentRecord.experimentCompletion}
				</td>
				<%-- <td>
					${experimentRecord.count}
				</td> --%>
				<td>
					<fmt:formatDate value="${experimentRecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${experimentRecord.experimentProblem}
				</td>
				<td>
					${experimentRecord.experimentScore}
				</td>
				<td>
					${experimentRecord.experimentComment}
				</td>
				<shiro:hasPermission name="experiment:experimentHistory:edit"><td>
    				<a href="${ctx}/experiment/experimentHistory/form?id=${experimentRecord.id}">修改</a>
					<a href="${ctx}/experiment/experimentHistory/delete?id=${experimentRecord.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>