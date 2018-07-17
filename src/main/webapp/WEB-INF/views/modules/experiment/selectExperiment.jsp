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
	<form:form id="searchForm" modelAttribute="course" action="${ctx}/experiment/course/setCourseAndExperiment" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="id" name="id" type="hidden" value="${id}"/>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th></th>
				<th>实验名称</th>
				<th>实验目的</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${experimentName}" var="experimentInfo">
			<tr>
			   <td>
			   <input type="checkbox" value="${experimentInfo.id}" id="${experimentInfo.id}" name="ids"/>
			   </td>
				<td><a href="${ctx}/experiment/course/form?id=${experimentInfo.id}">
					${experimentInfo.experimentName}
				</a></td>
				<td>
					${experimentInfo.experimentObjective}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript" >
	window.onload = function() { 

		$.post(         
				"${ctx}/experiment/course/ajaxCourseExperiment",
				{"courseId":'${id}'},
				function(data){
					for(var j=0;j < data.length;j++){
			        	 document.getElementById(data[j]).checked = true; 
		                }
				},
				"json"
				);
		
	           }
	function keep(){
		document.getElementById('searchForm').submit();
	    close();
	 }
	function close(){
		 window.parent.window.jBox.close();
	}

   </script>
	<div class="form-actions">
			<input  id="btnSubmit"  class="btn btn-primary" type="button"  onclick="keep()" value="保 存"/>
		</div>
	<div class="pagination">${page}</div>
	</form:form>
</body>

</html>