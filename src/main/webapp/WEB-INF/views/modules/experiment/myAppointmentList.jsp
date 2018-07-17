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
	<form:form id="searchForm" modelAttribute="experimentArrange" action="${ctx}/experiment/myAppointment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>实验名称：</label>
				<form:select id="experimentInfoId" path="experimentInfoId" class="input-medium">
					<form:options items="${experimentNames}" itemLabel="experimentName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>实验时间：</label>
				<input id="experimentStarttime" name="experimentStarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentArrange.experimentStarttime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input id="experimentEndtime" name="experimentEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${experimentArrange.experimentEndtime}" pattern="yyyy-MM-dd"/>"
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
				<th>实验室地址</th>
				<th>实验开始时间</th>
				<th>值班老师</th>
				<th>审批状态</th>
				<shiro:hasPermission name="experiment:experimentAppointment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="experimentAppointment">
			<tr>
				<td>${experimentAppointment.experimentName}</td>
				<td>${experimentAppointment.experimentContent}</td>
				<td>${experimentAppointment.laboratoryName}</td>
				<td><fmt:formatDate value="${experimentAppointment.experimentStarttime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<td>${experimentAppointment.teacherName}</td>
				<td>${experimentAppointment.approveStatusList}</td>
				<shiro:hasPermission name="experiment:experimentAppointment:edit">
					<td width="60">
						<c:if test="${ experimentAppointment.approveStatus==1}">
		    				<a href="javascript:void(0);" onclick="hrefExperiment('${ctx}/experiment/myAppointment/appointment?id=${experimentAppointment.id}','${experimentAppointment.approveStatus}')">取消预约</a>
		    			</c:if>	
		    			
		    			<c:if test="${ experimentAppointment.approveStatus==3}">
		    				<a href="javascript:void(0);" onclick="hrefExperiment('${ctx}/experiment/myAppointment/updateStatus?id=${experimentAppointment.id}','${experimentAppointment.approveStatus}')">重新申请</a> 
		    			</c:if>	
		    			
		    			<c:if test="${ experimentAppointment.approveStatus==2}">
		    			    <a id="choiseExperiment" href="javascript:void(0)" onclick="choiseExperiment('${loginName}','${password}')" >进入实验</a> 
		               </c:if>
	               </td>
               </shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
         function choiseExperiment(name, password) {
        	top.$.jBox.open("iframe:"+'${zeppelinUrl}'+"?userName="+name+"&password="+password, "",$(top.document).width()-80,$(top.document).height()+80,{
        		loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
					$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
					$("body", h.find("iframe").contents()).css("marginTop","10px");
				}
			});
        }
       function hrefExperiment(url,status) {
    	   var b;
    	   if (status == 1) {
               b=confirm('确定要取消预约？');
    	   }else {
    	       b=confirm('确定要重新申请？');   
    	   }
  		   if(b){
  		   var experimentStarttime=$('#experimentStarttime').val();
  		   var experimentEndtime=$('#experimentEndtime').val();
  		   var experimentInfoId=$('#experimentInfoId').val();
  		   url = url + "&experimentStarttime="+experimentStarttime+"&experimentEndtime="+experimentEndtime+"&experimentInfoId="+experimentInfoId;
  		   location.href  = url;
           }
       } 
    </script>
	<div class="pagination">${page}</div>
</body>
</html>