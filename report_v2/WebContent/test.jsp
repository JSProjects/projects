<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1','packages':['timeline']}]}"></script>
<script type="text/javascript">

google.setOnLoadCallback(drawChart);

function drawChart() {
  var container = document.getElementById('timeline');
  var chart = new google.visualization.Timeline(container);
  var dataTable = new google.visualization.DataTable();

  dataTable.addColumn({ type: 'string', id: 'Entitlement Key' });
  dataTable.addColumn({ type: 'date', id: 'Start' });
  dataTable.addColumn({ type: 'date', id: 'End' });
  dataTable.addRows([
                     [ 'IUfEO-0mOlj-BJO0U-laufG-oJMpF-OezH8', new Date(2014, 11, 17), new Date(2016, 0, 15) ],
                     [ '0Py42-Vpqfz-rU3fN-QqvDX-7W9K2-AFbzo',      new Date(2014, 3, 16),  new Date(2015, 4, 15) ],
                     [ 'MNX02-QWMhT-UjafY-LsuYA-tMVHN-ZCPkx	',  new Date(2014, 0, 24),  new Date(2015, 0, 23) ]]);

  var options = {
		     colors: ['#cbb69d'], 
		  };
  chart.draw(dataTable,options);
}
</script>
       <div id="timeline" style="width: 100%; height: 180px;"></div>
</html>