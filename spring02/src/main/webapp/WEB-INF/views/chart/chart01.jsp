<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="test/javascript" src="http://www.google.com/jsapi"></script>
<script>
	google.load('visualization', '1',{
		'packages' : ['corechart']
	});
	//로딩이 완료되면 wrawChart 함수 호출
	google.setOnLoadCallback(drawChart); 
	function drawChart() { 
		var jsonData = $.ajax({
			url : "${path}/json/sampleData2.json", 
			dataType : "json",
			async : false
		}).responseTest;
		console.log(jsonData);
		//테이터 테이블 생성
		
		var data = new google.visualization.DataTable(jsonData); 
		//차트를 출력할 div
		//LineChart, ColumnChart, PieChart
		var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
		
		//var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
		
		//var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
		
		//차트객체.draw(데이터테이블, 옵션)
		//curveType : "function" => 곡선 처리 
		
		
		chart.draw(data, {
			title : "차트예제",
			//curveType : "function",
			width : 600,
			height : 440
		});
	}
</script>
</head>
<body>
	<%@ include file="../include/admin_menu.jsp" %>
	<div id="chart_div"></div>
	<button id="btn" type="button" onclick="drawChart()">refresh</button>
</body>
</html>