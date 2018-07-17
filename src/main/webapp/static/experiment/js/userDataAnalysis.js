$(function () {
    "use strict";
    
    function render_c0(id) {
    	var chart = echarts.init($("#" + id + " .c-container").get(0));
        chart.showLoading();
    	var option = {
    		    title : {
    		        text: '',
    		        subtext: '',
    		        x:'center'
    		    },
    		    tooltip : {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b} : {c} ({d}%)"
    		    },
    		    legend: {
    		        orient : 'vertical',
    		        x : 'left',
    		        data:[]
    		    },
    		    toolbox: {
    		        show : true,
    		        feature : {
    		            mark : {show: false},
    		            dataView : {show: false, readOnly: false},
    		            magicType : {
    		                show: false, 
    		                type: ['pie', 'funnel'],
    		                option: {
    		                    funnel: {
    		                        x: '25%',
    		                        width: '50%',
    		                        funnelAlign: 'left',
    		                        max: 1548
    		                    }
    		                }
    		            },
    		            restore : {show: false},
    		            saveAsImage : {show: false}
    		        }
    		    },
    		    calculable : true,
    		    series : []
    		};
        
    	 getC0Data("userDataAnalysis/userData",chart,option);                    
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
    
  //填充图表柱状图，曲线图数据
    function fillC0Data(chart,option,result){
        if(result){
            //将返回的category和series对象赋值给options对象内的category和series
            option.legend.data = result.legend;
            var series_arr=result.series;
            for(var i=0;i<series_arr.length;i++){
                option.series[i] = result.series[i];
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
    		                    
        getC1Data("userDataAnalysis/userGradeData",chart,option); 
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
    
    function render_c2(id) {
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
    		                    
       getC2Data("userDataAnalysis/userClassData",chart,option); 
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
			option.yAxis.data = result.axis;
			var series_arr = result.series;
			for (var i = 0; i < series_arr.length; i++) {
				option.series[i] = result.series[i];
			}
			chart.hideLoading();
			chart.setOption(option);
		}
	}
    
    //////////////////////////////////////////////
    
    function render_c3(id) {
    	var chart = echarts.init($("#" + id + " .c-container").get(0));
        chart.showLoading();
      
       var option = {
        	    title : {
        	        text: '',
        	        subtext: '',
        	        x:'center'
        	    },
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
        	    },
        	    legend: {
        	        x : 'center',
        	        y : 'bottom',
        	        data:[]
        	    },
        	    toolbox: {
        	        show : true,
        	        feature : {
        	            mark : {show: false},
        	            dataView : {show: false, readOnly: false},
        	            magicType : {
        	                show: true,
        	                type: ['pie', 'funnel']
        	            },
        	            restore : {show: false},
        	            saveAsImage : {show: false}
        	        }
        	    },
        	    calculable : true,
        	    series : [
        	        {
        	            name:'面积模式',
        	            type:'pie',
        	            radius : [30, 110],
        	            //center : ['75%', '50%'],
        	            roseType : 'area',
        	            data:[]
        	        }
        	    ]
        	};

    		                    
       getC3Data("userDataAnalysis/userAgeData",chart,option); 
    }
    
    // ajax获取所需数据
	function getC3Data(url, chart, option) {

		$.ajax({
			type : 'get',
			url : url,
			dataType : 'json',
			success : function(result) {
				fillC3Data(chart, option, result);
//				alert(result);
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
//			alert(result.legend);
//			option.yAxis.data = result.axis;
			var series_arr = result.series;
//			alert(result.series);
			for (var i = 0; i < series_arr.length; i++) {
				option.series[i].data = result.series[i].data;
			}
			chart.hideLoading();
			chart.setOption(option);
		}
	}
	
	function render_c4(id) {
    	var chart = echarts.init($("#" + id + " .c-container").get(0));
        chart.showLoading();
    	var option = {
    		    title: {
    		        text: '学生城市分布',
    		        subtext: '',
    		        left: 'center'
    		    },
    		    tooltip: {
    		        trigger: 'item'
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        left: 'left',
    		        data:['学生城市分布']
    		    },
    		    visualMap: {
    		        min: 0,
    		        max: 2500,
    		        left: 'left',
    		        top: 'bottom',
    		        text: ['高','低'],           // 文本，默认为数值文本
    		        calculable: true
    		    },
    		    toolbox: {
    		        show: false,
    		        orient: 'vertical',
    		        left: 'right',
    		        top: 'center',
    		        feature: {
    		            dataView: {readOnly: false},
    		            restore: {},
    		            saveAsImage: {}
    		        }
    		    },
    		    series: [
    		        {
    		            name: 'iphone3',
    		            type: 'map',
    		            mapType: 'china',
    		            roam: false,
    		            label: {
    		                normal: {
    		                    show: true
    		                },
    		                emphasis: {
    		                    show: true
    		                }
    		            },
    		            data:[
    		                {name: '北京',value: 20 },
    		                {name: '天津',value: 30 },
    		                {name: '上海',value: 50 },
    		                {name: '重庆',value: 10 },
    		                {name: '河北',value: 10 },
    		                {name: '河南',value: 15 },
    		                {name: '云南',value: 46 },
    		                {name: '辽宁',value: 70 },
    		                {name: '黑龙江',value: 100 },
    		                {name: '湖南',value: 120 },
    		                {name: '安徽',value: 23 },
    		                {name: '山东',value: 45 },
    		                {name: '新疆',value: 64 },
    		                {name: '江苏',value: 78 },
    		                {name: '浙江',value: 85 },
    		                {name: '江西',value: 90 },
    		                {name: '湖北',value: 48 },
    		                {name: '广西',value: 20 },
    		                {name: '甘肃',value: 77 },
    		                {name: '山西',value: 88 },
    		                {name: '内蒙古',value: 66 },
    		                {name: '陕西',value: 55 },
    		                {name: '吉林',value: 47 }
    		            ]
    		        }
    		    ]
    		};
    	chart.hideLoading();
		chart.setOption(option);
        
//    	 getC0Data("userDataAnalysis/userData",chart,option);                    
    }
	
    
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