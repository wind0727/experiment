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
	 <%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/experiment/adminChoiceCourse/">单表列表</a></li>
	    <shiro:hasPermission name="experiment:adminChoiceCourse:edit"><li><a href="${ctx}/experiment/adminChoiceCourse/form">单表添加</a></li></shiro:hasPermission> 
	</ul>  --%>
	<form:form id="searchForm" modelAttribute="choiceCourse" action="${ctx}/experiment/choiceCourse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<c:forEach items="${officeList}" var="office">
		    <c:if test="${type == '8' && office.type == '7'}">
				<ul class="ul-form">
				 	<li class="btns"><input id="btnSubmit" type="button" class="btn btn-primary" value="选择课程" onclick="assignButton('${office.id}')" width="40px"/></li>
					<li class="clearfix"></li>
				</ul> 
			</c:if>
        </c:forEach>
	<sys:message content="${message}"/>
	
	<table id="treeTable" class="table table-striped table-bordered table-condensed hide" style="display: table;">
		<thead><tr><th>名称</th>
			<c:if test="${type == '8'}">
				<th>课程介绍</th><th>教师名称</th>
			</c:if>
		</tr></thead>	
			<tbody><c:forEach items="${officeList}" var="office">
				<tr id="${office.id}"  pId="${office.parentIds}">
					<c:if test="${type != '8' || office.type != '7'}">	
						<td>${office.name}</td>
					</c:if>
					<c:if test="${type == '8' && office.type != '7'}">	
						<td>${office.introduce}</td>
						<td>${office.teacherName}</td>
					</c:if>
					<c:if test="${office.type == '7' && type != '8'}">	
						<td><a href="" onclick="assignButton('${office.id}')">安排课程</a></td>
					</c:if>
					<c:if test="${office.type != '7' && type != '8'}">
						<td><a href=""></a></td>
					</c:if>
				</tr>
			</c:forEach></tbody>
	</table>
	</form:form>
    <script type="text/javascript">
	$(document).ready(function() {
		$("#treeTable").treeTable({expandLevel : 4}).show();
	});
	
	 function  assignButton(officeId){
		top.$.jBox.open("iframe:${ctx}/experiment/course/userToRole?officeId="+officeId, "选择课程",810,$(top.document).height()-240,{
			buttons:{"确定选择":"ok", "清除已选":"clear", "关闭":true},submit:function(v, h, f){
				if (v == "ok"){
					var ids = h.find("iframe")[0].contentWindow.getAllSelect();
					var arrangeIds = h.find("iframe")[0].contentWindow.getGradeArrangeId();
			        $(window).attr('location','${ctx}/experiment/choiceCourse/insertGordCourse?ids='+ids+'&officeId='+officeId+'&arrangeIds='+arrangeIds+'&type=${type}'); 
					return true;
				} else if (v == "clear"){
					var type = h.find("iframe")[0].contentWindow.type;
					
					if(type == '8'){
						window.confirm("你没有清除科目权限");
						return false;
					}else{
						var r = window.confirm("确定清除已选科目");
					}
					
					if(r && type != '8'){
						$(window).attr('location','${ctx}/experiment/choiceCourse/cleanGordCourse?officeId='+officeId);
					}
					return true;
                }
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
	 }
</script>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>