<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分配角色</title>
	<meta name="decorator" content="blank"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
	
		var gradeTree;
		var selectedTree;//zTree已选择对象
		var treeObj;
		var nodes;
		var before_ids;
		var type = '${type}';
		// 初始化
		$(document).ready(function(){
			gradeTree = $.fn.zTree.init($("#gradeTree"), setting, officeNodes);
			selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
			gradeTree.addNodes(null, studentData);
		});
		// 复选框选中状态默认勾选
		/* window.onload = function(){
			 treeObj = $.fn.zTree.getZTreeObj("selectedTree");
			 <c:forEach items="${alreadyTreeData}" var="date">
				 var node = treeObj.getNodeByParam("id",'${date.id}');
				 node.checked=true;
			     getAllChilds(node);
			 </c:forEach> 
		}
		
		function getAllChilds(node){
			 for(var i=0;i<node.children.length;i++) {
				　var childNode = node.children[i];
			 　       childNode.checked=true;
			 　       while(typeof(childNode.children) != 'undefined'){
				 　	    getAllChilds(childNode); 
				 　	    break;
			 　       }
				 }
		} */
       
		var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
				data: {simpleData: {enable: true}},
				callback: {onClick: treenodeClick}};
		
		var officeNodes =[
	             <c:forEach items="${alreadyTreeData}" var="office">
	            {id:"${office.id}",
	             pId:"${not empty office.pId?office.pId:0}", 
	             name:"<font color='red' style='font-weight:bold;'>${office.name}</font>",
	             checked:true},
	            </c:forEach> 
			]; 
		
		var studentData=[
				<c:forEach items="${studentData}" var="office">
	            {id:"${office.id}",
	             pId:"${not empty office.pId?office.pId:0}", 
	             name:"<font color='purple' style='font-weight:bold;'>${office.name}</font>",
	             checked:true},
	            </c:forEach>
		]
		
		var pre_selectedNodes =[
   		        <c:forEach items="${userList}" var="user">
   		        {id:"${user.id}",
   		         pId:"0",
   		         name:"${user.name}"
   		        </c:forEach>];
		
		var selectedNodes =[
		        <c:forEach items="${treeData}" var="user">
		        {pId:"${user.pId}",
		         name:"${user.name}",
		         id:"${user.id}"},
		        </c:forEach>];
		
		var pre_ids = "${selectIds}".split(",");
		
		
		
		//点击选择项回调
		function treenodeClick(event, treeId, treeNode, clickFlag){
	    	
	    	var str = "[" ;
	    	
			     if("selectedTree" == treeId){
			    	 
			    	 if(treeNode.isParent){
				     	str = getAllChildrenNodes(treeNode,str); 
			    	 }else{
			    		 str+='{"name":"'+treeNode.name+'",'+'"pId":"'+0+'",'+'"id":"'+treeNode.id+'"},';
					} 
			    	 
				     str = str.substring(0,str.length-1);
				     str = str+"]";
			    	 str=$.parseJSON(str);
			
			         var ids = getAllSelect();
			         ids = ids.split(",");
				     for(var i = 0;i < str.length;i++){ 
				     	if($.inArray(String(str[i].id), ids)<0){
							gradeTree.addNodes(null, str[i]);
						} 
			         } 
				 }
				if("gradeTree" == treeId){
				   /*  var coursrIds = "";
					for(var i in officeNodes){
						coursrIds += officeNodes[i].id+','
					}
					coursrIds = coursrIds.split(","); */
					
					if($.inArray(String(treeNode.id), ids)<0){
						if(type == '8'){
							if($.inArray(String(treeNode.id), getGradeArrangeId()) < 0){
								gradeTree.removeNode(treeNode);
							}
						}else{
							gradeTree.removeNode(treeNode);	
						}
					}
				};
		};
		
		function getGradeArrangeId(){
			var coursrIds = "";
			for(var i in officeNodes){
				coursrIds += officeNodes[i].id+','
			}
			coursrIds = coursrIds.split(",");
			return coursrIds;
		}
		
		function getAllChildrenNodes(treeNode,result){  
			if (treeNode.isParent) {  
		        var childrenNodes = treeNode.children;  
		        if (childrenNodes) {  
		            for (var i = 0; i < childrenNodes.length; i++) {
		                if(!childrenNodes[i].isParent){
		                	result +='{"name":"'+childrenNodes[i].name+'",'+'"pId":"'+0+'",'+'"id":"'+childrenNodes[i].id+'"},';
		                }
		                result = getAllChildrenNodes(childrenNodes[i], result);  
		            }  
		    	}
		    }
		    return result;
		}
		
		function getAllSelect(){
			var ids = "";
			var treeObj = $.fn.zTree.getZTreeObj("gradeTree");
	    	var node = treeObj.getNodes();
	    	var nodes = treeObj.transformToArray(node);
	    	 
	        for(var i=0;i<nodes.length;i++){  
	        ids+=nodes[i].id + ",";  
	        }
		   return ids;
		} 
		
		/* function submit(){
			var submit = function (v, h, f) {
			    if (v == 'ok'){
			    	
			    } else if (v == 'cancel'){
			    	top.$.jBox.tip("取消清除操作！", 'info');
			    }
			    return true;
			}
		} */
		
		 function submit(){
			var submit = function (v, h, f) {
			    if (v == 'ok'){
			    	
			    } else if (v == 'clear'){
			    	
			    }
			    return true;
			};
			
		}; 
	</script>
</head>
<body>
	<div id="assignRole" class="row-fluid span12">
		<div class="span4" style="border-right: 1px solid #A8A8A8;">
			<p>待选课程：</p>
			<div id="selectedTree" class="ztree"></div>
		</div>
		<div class="span3">
			<p>已选课程：</p>
			<div id="gradeTree" class="ztree"></div>
		</div>
		<!-- <div class="span3" style="padding-left:16px;border-left: 1px solid #A8A8A8;"> -->
			<!-- <p>已选课程：</p>
			<div id="userTree" class="ztree"></div> -->
		</div>
	</div>
</body>
</html>
