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
		<li class="active"><a href="${ctx}/experiment/experimentArrange/">单表列表</a></li>
		<shiro:hasPermission name="experiment:experimentArrange:edit"><li><a href="${ctx}/experiment/experimentArrange/form">单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="experimentArrange" action="${ctx}/experiment/experimentArrange/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>实验名称：</label>
				<form:select id="experimentInfoId" path="experimentInfoId" class="input-medium">
					<form:options items="${experiments}" itemValue="experimentInfoId" itemLabel="experimentName"  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>实验时间：</label>
				<input id="experimentStarttime" name="experimentStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentArrange.experimentStarttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input id="experimentEndtime" name="experimentEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentArrange.experimentEndtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" width="100px"/>
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
				<!-- <th>所属课程</th> -->
				<th>实验室名称</th>
				<th>实验室地址</th>
				<th>实验开始时间</th>
				<th>实验结束时间</th>
				<th>值班老师</th>
				<th>参加人数</th>
				<shiro:hasPermission name="experiment:experimentArrange:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="experimentArrange">
			<tr>
				 <td><%-- <a href="${ctx}/experiment/experimentArrange/form?id=${experimentArrange.id}"> --%>
					${experimentArrange.experimentName}
				</a></td> 
				<%-- <td>
					${experimentArrange.courseName}
				</td> --%>
				<td>
					${experimentArrange.laboratoryName}
				</td>
				<td>
					${experimentArrange.laboratoryAddress}
				</td>
				<td>
					<fmt:formatDate value="${experimentArrange.experimentStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${experimentArrange.experimentEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${experimentArrange.teacherName}
				</td>
				<td>
					${experimentArrange.count}
				</td>
				<shiro:hasPermission name="experiment:experimentArrange:edit"><td>
    				<a href="javascript:void(0);" onclick="returnId('${ctx}/experiment/experimentArrange/editage?id=${experimentArrange.id}')">修改</a>
					<a href="javascript:void(0);" onclick="hrefExperiment('${ctx}/experiment/experimentArrange/delete?id=${experimentArrange.id}')">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
		<script type="text/javascript">
			function returnId(url){
				 var experimentStarttime = $('#experimentStarttime').val();
				 var experimentEndtime = $('#experimentEndtime').val();
				 var experimentInfoId = $('#experimentInfoId').val();
				 url = url + "&experimentStarttime="+experimentStarttime+"&experimentEndtime="+experimentEndtime+"&experimentInfoId="+experimentInfoId;
				 location.href  = url;
			 }
		
			function hrefExperiment(url){
				 var b = confirm('确认要删除该单表吗？');
				 if(b){
					 var experimentStarttime = $('#experimentStarttime').val();
					 var experimentEndtime = $('#experimentEndtime').val();
					 var experimentInfoId = $('#experimentInfoId').val();
					 url = url + "&experimentStarttime="+experimentStarttime+"&experimentEndtime="+experimentEndtime+"&experimentInfoId="+experimentInfoId;
					 location.href  = url;
				 }
			 }
		</script>
</body>
</html>