<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>总体数据分析</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <link href="${ctxStatic}/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />    
        <link href="${ctxStatic}/AdminLTE/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${ctxStatic}/AdminLTE/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <link href="${ctxStatic}/AdminLTE/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
        <link href="${ctxStatic}/AdminLTE/dist/css/skins/skin-yellow.min.css" rel="stylesheet" type="text/css" />
        <link href="${ctxStatic}/AdminLTE/css/dataReport.css" rel="stylesheet" type="text/css" />
    </head>
   
<body class="skin-yellow sidebar-mini" onload="first()">
   
    <div>     
        <!-- Main content -->
        <section class="content">
            <div class="row">
				 <div class="col-md-6">
                    <div class="nav-tabs-custom tab-chart dchart-2">
                        <ul class="nav nav-tabs pull-right ui-sortable-handle">
                            <li class="active"><a data-toggle="tab" href="#c0">学生总人数</a></li>
                            <li class="pull-left header"><span> 用户数据分析</span></li>
                        </ul>

                        <div id="c0" class="box-body">
                            <div class="table-responsive mailbox-messages chart-responsive">
                                <div class="c-container" style="height:500px;width:100%"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="nav-tabs-custom tab-chart dchart-2">
                        <ul class="nav nav-tabs pull-right ui-sortable-handle">
                            <li class="active"><a data-toggle="tab" href="#c3">学生年龄分布</a></li>
                            <li class="pull-left header"><span> 学生年龄分布</span></li>
                        </ul>

                        <div id="c3" class="box-body">
                            <div class="table-responsive mailbox-messages chart-responsive">
                                <div class="c-container" style="height:500px;width:100%"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
				 <div class="col-md-6">
                    <div class="nav-tabs-custom tab-chart dchart-2">
                        <ul class="nav nav-tabs pull-right ui-sortable-handle">
                            <li class="active"><a data-toggle="tab" href="#c1">年级人数</a></li>
                            <li class="pull-left header"><span> 年级人数</span></li>
                        </ul>

                        <div id="c1" class="box-body">
                            <div class="table-responsive mailbox-messages chart-responsive">
                                <div class="c-container" style="height:500px;width:100%"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
				 <div class="col-md-6">
                    <div class="nav-tabs-custom tab-chart dchart-2">
                        <ul class="nav nav-tabs pull-right ui-sortable-handle">
                            <li class="active"><a data-toggle="tab" href="#c2">班级人数</a></li>
                            <li class="pull-left header"><span> 班级人数</span></li>
                        </ul>
						<div class="row">  
						    <div class='col-sm-3'>  
						        <div class="form-group">  
						            <label>选择专业：</label>  
						            <select  id="select" onchange="dian(this)">
						           <c:forEach items="${ majors}" var="majorVo" >
						             <option value="${majorVo.majorId}"> ${majorVo.majorName}</option>
						           </c:forEach>
						               </select>
						        </div>  
						    </div>  
						    <div class='col-sm-3'>  
						        <div class="form-group">  
						            <label>选择年级：</label>  
						            <select id="showOption">
						            	<option />
						            </select>
						        </div>  
						    </div>  
						    <div class='col-sm-3'>  
						    	<input type="button" name="" value="查询" onclick="queryNewUser()"/>
						    </div>
						</div>
                        <div id="c2" class="box-body">
                            <div class="table-responsive mailbox-messages chart-responsive">
                                <div class="c-container" style="height:500px;width:100%"></div>
                            </div>
                        </div>
                    </div>
                </div>
                  
        </section>
    </div>
<!-- </div>./wrapper -->

<script type="text/javascript">
 function first(){
	var word=$("#select option:selected").val();
	  /*   var word=ful.value; 
	  var word = obj.value; */
    var gradeId="";	  
	var search="";
	$.post(          "${ctx}/report/experimentDataAnalysis/ajax",
			{"majorId":word},
			function(data){
				if(data.length>0){
		    		 for(var i=0;i<data.length;i++){
					    	search+="<option value="+data[i].gradeId+">"+data[i].gradeName+"</option>";
					    	
					    }
					    $("#showOption").html(search);
					    $("#showOption").css("display","block");
					    
					    
				}
				gradeId=$("#showOption option:selected").val();
				queryNewUser(gradeId);
			},
			"json"
			);
	        
	       
	}


function dian(obj){
	var word = obj.value;
	var search="";
	$.post(          "${ctx}/report/experimentDataAnalysis/ajax",
					{"majorId":word},
					function(data){
						if(data.length>0){
				    		 for(var i=0;i<data.length;i++){
							    	search+="<option value="+data[i].gradeId+">"+data[i].gradeName+"</option>";
							    	
							    }
							    $("#showOption").html(search);
							    $("#showOption").css("display","block");
							   
						}
					},
					"json"
					);

 } 
 
function queryNewUser(gradeId) {
	render_c2("c2",gradeId);
}

function render_c2(id,grade) {
	var gradeId = $("#showOption option:selected").val();
	if (typeof(gradeId) == "undefined") {
		gradeId = grade;
	}
	var chart = echarts.init($("#" + id + " .c-container").get(0));
    chart.showLoading();
  
    var option = {
    	    tooltip: {
    	        trigger: 'axis',
    	        axisPointer: {
    	            type: 'cross',
    	            crossStyle: {
    	                color: '#999'
    	            }
    	        }
    	    },
    	    toolbox: {
    	        feature: {
    	            dataView: {show: false, readOnly: false},
    	            magicType: {show: false, type: ['line', 'bar']},
    	            restore: {show: false},
    	            saveAsImage: {show: false}
    	        }
    	    },
    	    legend: {
    	        data:[]
    	    },
    	    xAxis: [
    	        {
    	            type: 'category',
    	            axisLabel: {  
    	            	textStyle:{
    	                    fontSize:8 // 让字体变大
    	                },
    	                // 设置倾斜
    	            	interval:0, 
    	            	rotate:40  
    	            },  
    	            data: [],
    	            axisPointer: {
    	                type: 'shadow'
    	            }
    	        }
    	    ],
    	    yAxis: [
    	        {
    	            type: 'value',
    	            name: '人数',
    	        },
    	        {
    	            type: 'value',
    	            name: '年龄',
    	        }
    	    ],
    	    series: []
    	};
		                    
    getC2Data("userDataAnalysis/userClassData?gradeId="+gradeId,chart,option);
}   

// ajax获取所需数据
function getC2Data(url, chart, option) {

	$.ajax({
		type : 'get',
		url : url,
		dataType : 'json',
		success : function(result) {
			fillC2Data(chart, option, result);
		},
		error : function(errMsg) {
			console.error("加载数据失败")
		}
	});
}

function fillC2Data(chart, option, result) {
	if (result) {
		// 将返回的category和series对象赋值给options对象内的category和series
		option.legend.data = result.legend;
		option.xAxis[0].data = result.axis;
		var series_arr = result.series;
		for (var i = 0; i < series_arr.length; i++) {
			option.series[i] = result.series[i];
		}
		chart.hideLoading();
		chart.setOption(option);
	}
}

</script>

<script src="${ctxStatic}/jquery/jQuery-2.1.4.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/AdminLTE/dist/js/app.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctxStatic}/bootstrap/datepicker/locales/bootstrap-datepicker.fr.js"></script>
<script src='${ctxStatic}/echarts/js/echarts.min.js' type="text/javascript"></script>
<script src="${ctxStatic}/experiment/js/userDataAnalysis.js" type="text/javascript"></script>
</body>
</html>