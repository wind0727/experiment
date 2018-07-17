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
	<form:form id="searchForm" modelAttribute="studentInfoVO" action="${ctx}/experiment/teacherApprove/" method="post" class="breadcrumb form-search">
<%-- 		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/> --%>
<%-- 		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/> --%>
		<ul class="ul-form">
			<li><label>实验名称：</label>
				<form:select id="experimentInfoId" path="experimentInfoId" class="input-medium">
					<form:options items="${studentName}" itemLabel="experimentName" itemValue="experimentInfoId" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>所在班级：</label>
				<form:select path="experimentArrangeId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${studentInfos}" itemLabel="officeName" itemValue="experimentArrangeId" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li><label>预约时间：</label>
				<input id="experimentStarttime" name="experimentStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${studentInfoVO.experimentStarttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input id="experimentEndtime" name="experimentEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${studentInfoVO.experimentEndtime}" pattern="yyyy-MM-dd"/>"
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
				<th>学生姓名</th>
				<!-- <th>所属年级</th>
				<th>所属班级</th> -->
				<th>实验名称</th>
				<th>实验室地址</th>
				<th>实验开始时间</th>
				<th>实验剩余人数</th>
				<shiro:hasPermission name="experiment:teacherApprove:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="studentInfo">
			<tr>
			    <td>${studentInfo.studentName}</td> 
				<%-- <td>${studentInfo.parentId}</td> 
				<td>${studentInfo.officeName}</td> --%>
				<td>${studentInfo.experimentName}</td>
				<td>${studentInfo.laboratoryAddress}</td>
				<td><fmt:formatDate value="${studentInfo.experimentStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${studentInfo.count}</td>
				<shiro:hasPermission name="experiment:teacherApprove:edit"><td>
    				<a href="javascript:void(0);" onclick="hrefExperiment('${ctx}/experiment/teacherApprove/approve?status=2&experimentAppointmentId=${studentInfo.experimentAppointmentId}')">通过</a>
    				<a href="javascript:void(0);" onclick="reason('${studentInfo.experimentAppointmentId}')" >拒绝</a>
				</td></shiro:hasPermission>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div> 
	<script type="text/javascript">
	function reason(reason){
	var result = window.prompt("请输入拒绝理由", "");
     if(result != null){
    	 hrefExperiment("${ctx}/experiment/teacherApprove/approve?status=3&result="+result+"&experimentAppointmentId="+reason);
     }
     
	}
	
	function hrefExperiment(url){
		 var experimentStarttime = $('#experimentStarttime').val();
		 var experimentEndtime = $('#experimentEndtime').val();
		 var experimentInfoId = $('#experimentInfoId').val();
		 url = url + "&experimentStarttime="+experimentStarttime+"&experimentEndtime="+experimentEndtime+"&experimentInfoId="+experimentInfoId;
		 location.href  = url;
		 }
	</script>
</body>

</html>