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
		<li class="active"><a href="${ctx}/experiment/teacherComment/">单表列表</a></li>
		<%-- <shiro:hasPermission name="experiment:teacherComment:edit"><li><a href="${ctx}/experiment/teacherComment/form">单表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="experimentRecord" action="${ctx}/experiment/teacherComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>实验ID：</label>
				<form:select id="experimentInfoId" path="experimentInfoId" class="input-medium">
					<form:options items="${experiments}" itemLabel="experimentName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>实验时间：</label>
				<input id="experimentStarttime"  name="experimentStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentRecord.experimentStarttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input id="experimentEndtime"  name="experimentEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentRecord.experimentEndtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>实验名称</th>
				<th>值班老师</th>
				<th>实验完成情况</th>
				<th>实验得分</th>
				<th>实验评语</th>
				<shiro:hasPermission name="experiment:teacherComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="experimentRecord">
			<tr>
				<td><a href="${ctx}/experiment/teacherComment/form?id=${experimentRecord.id}">
					${experimentRecord.experimentName}
				</a></td>
				<td>
					${experimentRecord.teacherName}
				</td>
				<td>
					${experimentRecord.experimentCompletion}
				</td>
				<td>
					${experimentRecord.experimentScore}
				</td>
				<td>
					${experimentRecord.experimentComment}
				</td>
				<shiro:hasPermission name="experiment:teacherComment:edit"><td style="width: 100px">
    				<a href="javascript:void(0);" onclick="returnId('${ctx}/experiment/teacherComment/form?id=${experimentRecord.id}')">评分</a>
					<a href="javascript:void(0);" onclick="returnId('${ctx}/experiment/teacherComment/finshSave?id=${experimentRecord.id}')">完成评测</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		function returnId(url){
			 var experimentStarttime = $('#experimentStarttime').val();
			 var experimentEndtime = $('#experimentEndtime').val();
			 var experimentInfoId = $('#experimentInfoId').val();
			 url = url + "&experimentStarttime="+experimentStarttime+"&experimentEndtime="+experimentEndtime+"&experimentInfoId="+experimentInfoId;
			 location.href  = url;
		}
	</script>
	<div class="pagination">${page}</div>
</body>
</html>