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
<!-- 	<ul class="nav nav-tabs"> -->
<%-- 		<li class="active"><a href="${ctx}/experiment/experimentAppointment/">单表列表</a></li> --%>
<%-- 		<shiro:hasPermission name="experiment:experimentAppointment:edit"><li><a href="${ctx}/experiment/experimentAppointment/form">单表添加</a></li></shiro:hasPermission> --%>
<!-- 	</ul> -->
	<form:form id="searchForm" modelAttribute="experimentAppointment" action="${ctx}/experiment/experimentAppointment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>实验名称：</label>
				<form:select id="experimentInfoId" path="experimentInfoId" class="input-medium">
					<form:options items="${experimentNameList}" itemLabel="experimentName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>实验时间：</label>
				<input id="experimentStarttime" name="experimentStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentAppointment.experimentStarttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input id="experimentEndtime" name="experimentEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentAppointment.experimentEndtime}" pattern="yyyy-MM-dd"/>"
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
				<th>实验内容</th>
				<th>实验室名称</th>
				<th>实验室地址</th>
				<th>实验开始时间</th>
				<!-- <th>实验剩余人数</th> -->
				<!-- <th></th> -->
				<shiro:hasPermission name="experiment:experimentAppointment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="experimentAppointment">
			<tr>
				<td>${experimentAppointment.experimentName}</td>
				<td>${experimentAppointment.experimentContent}</td>
				<td>${experimentAppointment.laboratoryName}</td>
				<td>${experimentAppointment.laboratoryAddress}</td>
				<td width="150px"><fmt:formatDate value="${experimentAppointment.experimentStarttime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<%-- <td>${experimentAppointment.surplusPeopleNum}</td> --%>
				<shiro:hasPermission name="experiment:experimentAppointment:edit"><td>
    				<a  href="javascript:void(0);" onclick="hrefExperiment('${experimentAppointment.experimentArrangeId}')">预约</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		function hrefExperiment(experimentArrangeId){
			
			var experimentStarttime = $('#experimentStarttime').val();
			var experimentEndtime = $('#experimentEndtime').val();
			var experimentInfoId = $('#experimentInfoId').val();
		
			location.href = "${ctx}/experiment/experimentAppointment/appointment?experimentInfoId="+experimentInfoId+
				"&experimentArrangeId="+experimentArrangeId+
				"&experimentStarttime="+experimentStarttime+
				"&experimentEndtime="+experimentEndtime;
			}
	</script>
	<div class="pagination">${page}</div>
</body>
</html>