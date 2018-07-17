$(function () {
    "use strict";
    
    function render_c0(id) {
    	var chart = echarts.init($("#" + id + " .c-container").get(0));
        chart.showLoading();
    	var option = {
    			tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
    		    angleAxis: {
    		        type: 'category',
    		        data: [],
    		        z: 10
    		    },
    		    radiusAxis: {
    		    },
    		    polar: {
    		    },
    		    series: [],
    		    legend: {
    		        show: true,
    		        data: []
    		    }
    		};
    		                    
    	getC0Data("scoreDataAnalysis/experimentMajorAvgScoreData",chart,option);   
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
            option.angleAxis.data = result.axis;
            var series_arr=result.series;
            for(var i=0;i<series_arr.length;i++){
                option.series[i] = result.series[i];
            }
            chart.hideLoading();
            chart.setOption(option);
        }
    }
    
//    function render_c1(id) {
//    	var chart = echarts.init($("#" + id + " .c-container").get(0));
//        chart.showLoading();
//      
//        var option = {
//            tooltip : {
//                trigger: 'axis',
//                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
//                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
//                }
//            },
//            legend: {
//                data: ['15届', '16届','17届','18届']
//            },
//            grid: {
//                left: '3%',
//                right: '4%',
//                bottom: '3%',
//                containLabel: true
//            },
//            xAxis:  {
//                type: 'value'
//            },
//            yAxis: {
//                type: 'category',
//                data: ['计算机专业','物理专业','化学专业']
//            },
//            series: [
//                {
//                    name: '总实验数',
//                    type: 'bar',
//                    stack: '总量',
//                    label: {
//                        normal: {
//                            show: true,
//                            position: 'insideRight'
//                        }
//                    },
//                    data: [320, 302, 301]
//                },
//                {
//                    name: '15届',
//                    type: 'bar',
//                    stack: '总量',
//                    label: {
//                        normal: {
//                            show: true,
//                            position: 'insideRight'
//                        }
//                    },
//                    data: [120, 132, 101]
//                },
//                {
//                    name: '16届',
//                    type: 'bar',
//                    stack: '总量',
//                    label: {
//                        normal: {
//                            show: true,
//                            position: 'insideRight'
//                        }
//                    },
//                    data: [220, 182, 191]
//                },
//                {
//                    name: '17届',
//                    type: 'bar',
//                    stack: '总量',
//                    label: {
//                        normal: {
//                            show: true,
//                            position: 'insideRight'
//                        }
//                    },
//                    data: [150, 212, 201]
//                },
//                {
//                    name: '18届',
//                    type: 'bar',
//                    stack: '总量',
//                    label: {
//                        normal: {
//                            show: true,
//                            position: 'insideRight'
//                        }
//                    },
//                    data: [820, 832, 901]
//                }
//            ]
//        };
//    		                    
//    	chart.hideLoading();
//        chart.setOption(option);
//    }
//    
//    function render_c2(id) {
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
//        	        data:['平均得分','完成次数']
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
//        	            data: ['HDFS大数据互动实验','Hive大数据互动实验','HBase大数据互动实验','Spark大数据互动实验','Sqoop大数据互动实验','Flume大数据互动实验'],
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
//        	    series: [
//        	        {
//        	            name:'平均得分',
//        	            type:'bar',
//        	            data:[60.5, 45.4, 72, 50, 82, 70]
//        	        },
//        	        {
//        	            name:'完成次数',
//        	            type:'line',
//        	            yAxisIndex: 1,
//        	            data:[60, 73, 65, 76, 80, 84]
//        	        }
//        	    ]
//        	};
//    		                    
//    	chart.hideLoading();
//        chart.setOption(option);
//    }
//    
//    function render_c3(id) {
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
//    		        data:['1501班','1502班','1503班','1504班','1505班','1506班']
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
//        	            data: ['HDFS大数据互动实验','Hive大数据互动实验','HBase大数据互动实验','Spark大数据互动实验','Sqoop大数据互动实验','Flume大数据互动实验']
//    		        }
//    		    ],
//    		    yAxis : [
//    		        {
//    		            type : 'value'
//    		        }
//    		    ],
//    		    series : [
//    		        {
//    		            name:'1501班',
//    		            type:'bar',
//    		            data:[50, 64, 72, 65, 78, 55]
//    		        },
//    		        {
//    		            name:'1502班',
//    		            type:'bar',
//    		            stack: '一般',
//    		            data:[70, 65, 50, 68, 90, 58]
//    		        },
//    		        {
//    		            name:'1503班',
//    		            type:'bar',
//    		            stack: '一般',
//    		            data:[46, 56, 53, 48, 67, 76]
//    		        },
//    		        {
//    		            name:'1504班',
//    		            type:'bar',
//    		            stack: '一般',
//    		            data:[70, 65, 60, 85, 63, 50]
//    		        },
//    		        {
//    		            name:'1505班',
//    		            type:'bar',
//    		            data:[57, 64, 68, 78, 84, 95],
//    		            markLine : {
//    		                lineStyle: {
//    		                    normal: {
//    		                        type: 'dashed'
//    		                    }
//    		                },
//    		                data : [
//    		                    [{type : 'min'}, {type : 'max'}]
//    		                ]
//    		            }
//    		        },
//    		        {
//    		            name:'1506班',
//    		            type:'bar',
//    		            barWidth : 5,
//    		            stack: '好',
//    		            data:[62, 73, 70, 73, 90, 84]
//    		        }
//    		    ]
//    		};
//
//    		                    
//    	chart.hideLoading();
//        chart.setOption(option);
//    }
    
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