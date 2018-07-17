$(function () {
    "use strict";
    
    function render_c0(id) {
    	var chart = echarts.init($("#" + id + " .c-container").get(0));
        chart.showLoading();
    	var option = {
    		    tooltip: {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b}: {c}个"
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        x: 'left',
    		        data:[]
    		    },
    		    series: [
    		        {
    		            name:'',
    		            type:'pie',
    		            radius: ['50%', '70%'],
    		            avoidLabelOverlap: false,
    		            label: {
    		                normal: {
    		                    show: false,
    		                    position: 'center'
    		                },
    		                emphasis: {
    		                    show: true,
    		                    textStyle: {
    		                        fontSize: '30',
    		                        fontWeight: 'bold'
    		                    }
    		                }
    		            },
    		            labelLine: {
    		                normal: {
    		                    show: false
    		                }
    		            },
    		            data:[]
    		        }
    		    ]
    		};

    	getC0Data("experimentDataAnalysis/experimentData",chart,option);   
    }
    
    //ajax获取所需数据
    function getC0Data(url,chart,option){
    	
    	$.ajax({
            type:'get',
            url:url,
            dataType:'json',
            success:function(result){
            	fillC0Data(chart,option,result);
            },
            error:function(errMsg){
                console.error("加载数据失败")
            }
        });
    }
    
    function fillC0Data(chart,option,result){
        if(result){
            //将返回的category和series对象赋值给options对象内的category和series
            option.legend.data = result.legend;
            var series_arr=result.series;
            for(var i=0;i<series_arr.length;i++){
                option.series[i].data = result.series[i].data;
            }
            chart.hideLoading();
            chart.setOption(option);
        }
    }
    
    function render_c1(id) {
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
                data: []
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis:  {
                type: 'value'
            },
            yAxis: {
                type: 'category',
                data: []
            },
            series: []
        };
    		                    
        getC1Data("experimentDataAnalysis/experimentGradeData",chart,option);
    }
    
    // ajax获取所需数据
	function getC1Data(url, chart, option) {

		$.ajax({
			type : 'get',
			url : url,
			dataType : 'json',
			success : function(result) {
				fillC1Data(chart, option, result);
			},
			error : function(errMsg) {
				console.error("加载数据失败")
			}
		});
	}

	function fillC1Data(chart, option, result) {
		if (result) {
			// 将返回的category和series对象赋值给options对象内的category和series
			option.legend.data = result.legend;
			option.yAxis.data = result.axis;
			var series_arr = result.series;
			for (var i = 0; i < series_arr.length; i++) {
				option.series[i] = result.series[i];
			}
			chart.hideLoading();
			chart.setOption(option);
		}
	}
	
//	function queryC2Data() {
//		render_c2("c2");
//	}
    
//    function render_c2(id) {
//    	var gradeId = $("#c2GradeId").val();
//    	alert("gradeId  == " + gradeId);
//    	var chart = echarts.init($("#" + id + " .c-container").get(0));
//        chart.showLoading();
//      
//        var option = {
//        	    tooltip: {
//        	        trigger: 'axis',
//        	        axisPointer: {
//        	            type: 'cross',
//        	            crossStyle: {
//        	                color: '#999'
//        	            }
//        	        }
//        	    },
//        	    toolbox: {
//        	        feature: {
//        	            dataView: {show: false, readOnly: false},
//        	            magicType: {show: false, type: ['line', 'bar']},
//        	            restore: {show: false},
//        	            saveAsImage: {show: false}
//        	        }
//        	    },
//        	    legend: {
//        	        data:[]
//        	    },
//        	    xAxis: [
//        	        {
//        	            type: 'category',
//        	            axisLabel: {  
//        	            	textStyle:{
//        	                    fontSize:8 // 让字体变大
//        	                },
//        	                // 设置倾斜
//        	            	interval:0, 
//        	            	rotate:40  
//        	            },  
//        	            data: [],
//        	            axisPointer: {
//        	                type: 'shadow'
//        	            }
//        	        }
//        	    ],
//        	    yAxis: [
//        	        {
//        	            type: 'value',
//        	            name: '次数',
//        	        },
//        	        {
//        	            type: 'value',
//        	            name: '得分',
//        	        }
//        	    ],
//        	    series: []
//        	};
//    		                    
//        getC2Data("experimentDataAnalysis/experimentGradeSingleData?gradeId="+gradeId,chart,option);
//    }
//    
//    // ajax获取所需数据
//	function getC2Data(url, chart, option) {
//
//		$.ajax({
//			type : 'get',
//			url : url,
//			dataType : 'json',
//			success : function(result) {
//				fillC2Data(chart, option, result);
//			},
//			error : function(errMsg) {
//				console.error("加载数据失败")
//			}
//		});
//	}
//
//	function fillC2Data(chart, option, result) {
//		if (result) {
//			// 将返回的category和series对象赋值给options对象内的category和series
//			option.legend.data = result.legend;
//			option.xAxis[0].data = result.axis;
//			var series_arr = result.series;
//			for (var i = 0; i < series_arr.length; i++) {
//				option.series[i] = result.series[i];
//			}
//			chart.hideLoading();
//			chart.setOption(option);
//		}
//	}
	
//	function queryC3Data() {
//		render_c3("c3");
//	}
    
//    function render_c3(id) {
//    	var gradeId = $("#c3GradeId").val();
//    	var chart = echarts.init($("#" + id + " .c-container").get(0));
//        chart.showLoading();
//      
//       var option = {
//    		    tooltip : {
//    		        trigger: 'axis',
//    		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
//    		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
//    		        }
//    		    },
//    		    legend: {
//    		        data:[]
//    		    },
//    		    grid: {
//    		        left: '3%',
//    		        right: '4%',
//    		        bottom: '3%',
//    		        containLabel: true
//    		    },
//    		    xAxis : [
//    		        {
//    		            type : 'category',
//    		            axisLabel: {  
//        	            	textStyle:{
//        	                    fontSize:8 // 让字体变大
//        	                },
//        	                // 设置倾斜
//        	            	interval:0, 
//        	            	rotate:40  
//        	            },
//        	            data: []
//    		        }
//    		    ],
//    		    yAxis : [
//    		        {
//    		            type : 'value'
//    		        }
//    		    ],
//    		    series : []
//    		};
//
//       getC3Data("experimentDataAnalysis/experimentClassSingleData?gradeId="+gradeId,chart,option);
//    }
//    
//    // ajax获取所需数据
//	function getC3Data(url, chart, option) {
//
//		$.ajax({
//			type : 'get',
//			url : url,
//			dataType : 'json',
//			success : function(result) {
//				fillC3Data(chart, option, result);
//			},
//			error : function(errMsg) {
//				console.error("加载数据失败")
//			}
//		});
//	}
//
//	function fillC3Data(chart, option, result) {
//		if (result) {
//			// 将返回的category和series对象赋值给options对象内的category和series
//			option.legend.data = result.legend;
//			option.xAxis[0].data = result.axis;
//			var series_arr = result.series;
//			for (var i = 0; i < series_arr.length; i++) {
//				option.series[i] = result.series[i];
//			}
//			chart.hideLoading();
//			chart.setOption(option);
//		}
//	}
    
    $(".dchart-1 button").click(function() {
        if (!$(this).hasClass("active")) {
            $(this).addClass("active").siblings().removeClass("active");
            var container = $($(this).closest(".box-body").siblings("ul").find("li.active a").attr("href"));
            eval("render_" + container.attr("id") + "('" + container.attr("id") + "', '" + $(this).attr("data-type") + "')");
        }
    });

    $(".dchart-1 .nav-tabs li a").click(function() {
        var container = $($(this).attr("href"));
        if (!$(this).parent().hasClass("active")) {
            $(this).parent().addClass("active").siblings().removeClass("active");
        }
        container.show().siblings(".box-body").hide();
        if ($(".c-container", container).html() == "") {
            $("button", container).first().click();
        }
    });

    $(".dchart-1").each(function() {
        $(".nav-tabs li a", this).last().click();
    });


    $(".dchart-2 .nav-tabs li a").click(function() {
        var container = $($(this).attr("href"));
        if (!$(this).parent().hasClass("active")) {
            $(this).parent().addClass("active").siblings().removeClass("active");
        }
        container.show().siblings(".box-body").hide();
        if ($(".c-container", container).html() == "") {
            eval("render_" + container.attr("id") + "('" + container.attr("id") + "')");
        }
    });

    $(".dchart-2").each(function() {
        $(".nav-tabs li a", this).last().click();
    });
});