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
		<li class="active"><a href="${ctx}/experiment/experimentRecord/">单表列表</a></li>
		<shiro:hasPermission name="experiment:experimentRecord:edit"><li><a href="${ctx}/experiment/experimentRecord/form">单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="experimentAppointmentVO" action="${ctx}/experiment/experimentRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>实验名称：</label>
				<form:select id="experimentInfoId" path="experimentInfoId" class="input-medium">
					<form:options items="${studentExperiment}" itemLabel="experimentName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>实验时间：</label>
				<input id="experimentStarttime" name="experimentStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentAppointmentVO.experimentStarttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input id="experimentEndtime" name="experimentEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentAppointmentVO.experimentEndtime}" pattern="yyyy-MM-dd"/>"
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
				<th>实验问题</th>
				<!-- <th>实验结果地址</th> -->
				<!-- <th>实验得分</th>
				<th>实验评语</th> --> 
				<shiro:hasPermission name="experiment:experimentRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="experimentRecord">
			<tr>
				 <td><a href="${ctx}/experiment/experimentRecord/form?id=${experimentRecord.id}">
					${experimentRecord.experimentName}
				</a></td>
				<td>
					${experimentRecord.teacherName}
				</td> 
				<td>
					${experimentRecord.experimentCompletion}
				</td>
				<td>
					${experimentRecord.experimentProblem}
				</td>
				<%-- <td>
					${experimentRecord.experimentResultUrl}
				</td> --%>
				<%-- <td>
					${experimentRecord.experimentScore}
				</td>
				<td>
					${experimentRecord.experimentComment}
				</td> --%>
				<shiro:hasPermission name="experiment:experimentRecord:edit"><td width="60px">
    				<a href="javascript:void(0);" onclick="hrefExperiment('${ctx}/experiment/experimentRecord/form?id=${experimentRecord.id}')">修改</a>
					<a href="javascript:void(0);" onclick="deleteExperiment('${ctx}/experiment/experimentRecord/delete?id=${experimentRecord.id}')">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		var experimentStarttime = $('#experimentStarttime').val();
		var experimentEndtime = $('#experimentEndtime').val();
	    var experimentInfoId = $('#experimentInfoId').val();
	    
		function hrefExperiment(url){
			 url = url + "&experimentStarttime="+experimentStarttime+"&experimentEndtime="+experimentEndtime+"&experimentInfoId="+experimentInfoId;
			 location.href  = url;
		}
		
		function deleteExperiment(url){
			 var b = confirm('确认要删除该单表吗？');
			 if(b){
				 url = url + "&experimentStarttime="+experimentStarttime+"&experimentEndtime="+experimentEndtime+"&experimentInfoId="+experimentInfoId;
				 location.href  = url;
			 }
		}
	</script>
	<div class="pagination">${page}</div>
	
</body>
</html>