<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>实验数据分析</title>
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
                            <li class="active"><a data-toggle="tab" href="#c0">各专业实验总数</a></li>
                            <li class="pull-left header"><span>各专业实验总数</span></li>
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
                            <li class="active"><a data-toggle="tab" href="#c1">年级实验总数</a></li>
                            <li class="pull-left header"><span>年级实验总数</span></li>
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
                            <li class="active"><a data-toggle="tab" href="#c2">年级各实验完成次数</a></li>
                            <li class="pull-left header"><span>年级各实验完成次数</span></li>
                        </ul>
						<div class="row">  
						    <div class='col-sm-3'>  
						        <div class="form-group">  
						            <label>选择专业：</label>  
						            <select id="c2MajorId" onchange="c2ChangeGrade(this)">
							           <c:forEach items="${majors}" var="major" >
							             <option value="${major.majorId}"> ${major.majorName}</option>
							           </c:forEach>
						            </select>
						        </div>  
						    </div>  
						    <div class='col-sm-3'>  
						        <div class="form-group">  
						            <label>选择年级：</label>  
						            <select id="c2GradeId">
						            	
						            </select>
						        </div>  
						    </div>  
						    <div class='col-sm-3'>  
						    	<input type="button" name="" value="查询" onclick="queryC2Data()"/>
						    </div>
						</div>
                        <div id="c2" class="box-body">
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
                            <li class="active"><a data-toggle="tab" href="#c3">班级各实验完成次数</a></li>
                            <li class="pull-left header"><span>班级各实验完成次数</span></li>
                        </ul>
						<div class="row">  
						    <div class='col-sm-3'>  
						        <div class="form-group">  
						            <label>选择专业：</label> 
						            <select  id="c3MajorId" onchange="c3ChangeGrade(this)">
							           <c:forEach items="${majors}" var="major" >
							             <option value="${major.majorId}"> ${major.majorName}</option>
							           </c:forEach>
						            </select>
						        </div>  
						    </div>  
						    <div class='col-sm-3'>  
						        <div class="form-group">  
						            <label>选择年级：</label>  
						            <select id="c3GradeId">
						            	
						            </select>
						        </div>  
						    </div>  
						    <div class='col-sm-3'>  
						    	<input type="button" name="" value="查询" onclick="queryC3Data()"/>
						    </div>
						</div>
                        <div id="c3" class="box-body">
                            <div class="table-responsive mailbox-messages chart-responsive">
                                <div class="c-container" style="height:500px;width:100%"></div>
                            </div>
                        </div>
                    </div>
                </div>
                  
        </section>
    </div>
<!-- </div>./wrapper -->
<script src="${ctxStatic}/jquery/jQuery-2.1.4.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/AdminLTE/dist/js/app.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctxStatic}/bootstrap/datepicker/locales/bootstrap-datepicker.fr.js"></script>
<script src='${ctxStatic}/echarts/js/echarts.min.js' type="text/javascript"></script>
<script src="${ctxStatic}/experiment/js/experimentDataAnalysis.js" type="text/javascript"></script>
<script type="text/javascript">

	function first() {
		var word = $("#c2MajorId option:selected").val();
		var search = "";
		$.post("${ctx}/report/experimentDataAnalysis/ajax", {
			"majorId" : word
		}, function(data) {
			if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
// 					if (i == 0) {
// 						search += "<option selected=\"selected\" value=" + data[i].gradeId + ">" + data[i].gradeName + "</option>";
// 					} else {
						search += "<option value=" + data[i].gradeId + ">" + data[i].gradeName + "</option>";
// 					}

				}
				$("#c2GradeId").html(search);
				$("#c2GradeId").css("display", "block");
				$("#c3GradeId").html(search);
				$("#c3GradeId").css("display", "block");
			}
		}, "json");
	}

	
	function c2ChangeGrade(obj) {
		var word = obj.value;
		var search = "";
		$.post("${ctx}/report/experimentDataAnalysis/ajax", {
			"majorId" : word
		}, function(data) {
			if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					search += "<option value=" + data[i].gradeId + ">" + data[i].gradeName + "</option>";

				}
				$("#c2GradeId").html(search);
				$("#c2GradeId").css("display", "block");
			}
		}, "json");

	}
	
	function c3ChangeGrade(obj) {
		var word = obj.value;
		var search = "";
		$.post("${ctx}/report/experimentDataAnalysis/ajax", {
			"majorId" : word
		}, function(data) {
			if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					search += "<option value=" + data[i].gradeId + ">" + data[i].gradeName + "</option>";

				}
				$("#c3GradeId").html(search);
				$("#c3GradeId").css("display", "block");
			}
		}, "json");

	}
	
	function queryC2Data() {
		render_c2("c2");
	}
	
	function render_c2(id) {
    	var gradeId = $("#c2GradeId option:selected").val();
    	if (typeof(gradeId) == "undefined") {
    		gradeId = '${defaultGradeId}';
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
        	            name: '次数',
        	        },
        	        {
        	            type: 'value',
        	            name: '得分',
        	        }
        	    ],
        	    series: []
        	};
    		                    
        getC2Data("experimentDataAnalysis/experimentGradeSingleData?gradeId="+gradeId,chart,option);
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
	
	function queryC3Data() {
		render_c3("c3");
	}
	
	function render_c3(id) {
    	var gradeId = $("#c3GradeId option:selected").val();
    	if (typeof(gradeId) == "undefined") {
    		gradeId = '${defaultGradeId}';
    	}
    	var chart = echarts.init($("#" + id + " .c-container").get(0));
        chart.showLoading();
      
       var option = {
    		    tooltip : {
    		        trigger: 'axis',
    		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    		        }
    		    },
    		    legend: {
    		        data:[]
    		    },
    		    grid: {
    		        left: '3%',
    		        right: '4%',
    		        bottom: '3%',
    		        containLabel: true
    		    },
    		    xAxis : [
    		        {
    		            type : 'category',
    		            axisLabel: {  
        	            	textStyle:{
        	                    fontSize:8 // 让字体变大
        	                },
        	                // 设置倾斜
        	            	interval:0, 
        	            	rotate:40  
        	            },
        	            data: []
    		        }
    		    ],
    		    yAxis : [
    		        {
    		            type : 'value'
    		        }
    		    ],
    		    series : []
    		};

       getC3Data("experimentDataAnalysis/experimentClassSingleData?gradeId="+gradeId,chart,option);
    }
    
    // ajax获取所需数据
	function getC3Data(url, chart, option) {

		$.ajax({
			type : 'get',
			url : url,
			dataType : 'json',
			success : function(result) {
				fillC3Data(chart, option, result);
			},
			error : function(errMsg) {
				console.error("加载数据失败")
			}
		});
	}

	function fillC3Data(chart, option, result) {
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
</body>
</html>