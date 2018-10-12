<%  
 import java.text.DateFormat  
 import java.text.SimpleDateFormat  
%> 
<!-- Robot Framework Results --> 
<!DOCTYPE html>
<html>
<style type="text/css">
table {width:720px;table-layout:fixed;}
td {width:180px;}
td.title {
	background-color:#343A40;
	text-align: center;
}
td.suite{background-color:#EEE8AA;}
td.case{background-color:#dff0d8;}
td.head{background-color:#1E90FF;}
td.error {background-color:#FF6666;}
table thead tboday tr td {cellspacing:0px;border:1px;}
h2.span{color:white;}
span.pass{color:#66CC00;}
span.fail{color:#FF3333;}
</style>
<body>
<%  
 def robotResults = false  
 def actions = build.actions // List<hudson.model.Action>  
 actions.each() { action ->  
	if( action.class.simpleName.equals("RobotBuildAction") ) { // hudson.plugins.robot.RobotBuildAction  
		robotResults = true 
%>
		<div>
			<table cellpadding="4" align="left">
				<thead>
					<tr>
						<td class="title" colspan="4"><h2><span>${project.name}</span><span>  自动化测试报告</span></h2></td>
					</tr>
					<tr>
						<td class="case"><b>详细报告</b></td>
						<td colspan="3" class="case"><a href="${rooturl}${build.url}robot/report/report.html">点击查看报告详情</a></td>
					</tr>
					<tr>
						<td class="head"><b>用例总数</b></td>
						<td class="head"><b>通过</b></td>
						<td class="head"><b>不通过</b></td>
						<td class="head"><b>通过率</b></td>
					</tr>
					<tr>
						<td class="case"><%= action.result.overallTotal %></td>
						<td class="case"><b><span class="pass"><%= action.result.overallPassed %></span></b></td>
						<td class="case"><b><span class="fail"><%= action.result.overallFailed %></span></b></td>
						<td class="case"><%= action.overallPassPercentage %>%</td>
					</tr>
					<tr>
						<td colspan="2" class="head"><b>Test Name</b></td> 
						<td class="head"><b>Status</b></td>
						<td class="head"><b>Elapsed Time</b></td> 
					</tr>
				</thead>
				<tboday>
<% 
 def suites = action.result.allSuites  
 suites.each() { suite ->   
	def currSuite = suite  
    def suiteName = currSuite.displayName  
    //忽略最上层结构两个占位的元素  
    while (currSuite.parent != null && currSuite.parent.parent != null) {  
		currSuite = currSuite.parent  
		suiteName = currSuite.displayName + "." + suiteName  
    }
%> 
					<tr>
						<td colspan="4" class="suite"><b><%= suiteName %></b></td>
					</tr>
<%  
	DateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss")
    def execDateTcPairs = []
    suite.caseResults.each() { tc ->  
		Date execDate = format.parse(tc.starttime)
		execDateTcPairs << [execDate, tc]
    }
    //按执行日期、显示名称进行排序
    execDateTcPairs = execDateTcPairs.sort{ a,b -> a[1].displayName <=> b[1].displayName }
    execDateTcPairs = execDateTcPairs.sort{ a,b -> a[0] <=> b[0] }
    execDateTcPairs.each() {
		def execDate = it[0]
		def tc = it[1]  
%>
					<tr>  
						<td colspan="2" class="case"><%= tc.displayName %></td>  
						<td class="case"><b><span style="color:<%= tc.isPassed() ? "#66CC00" : "#FF3333" %>"><%= tc.isPassed() ? "PASS" : "FAIL" %></span></b></td>  
						<td class="case"><%= tc.getDuration().intdiv(60000)+"分"+(tc.getDuration()-tc.getDuration().intdiv(60000)*60000).intdiv(1000)+"秒" %></td>  
					</tr>  
		
<%
		if(tc.errorMsg != null) {
%>
					<tr>
						<td class="error"><b><span>错误描述：</span></b></td>
						<td class="error" colspan="3"><span><%= tc.errorMsg%></span></td>
					</tr>
<%				}%>
<%  
			} // tests  
		} // suites 
%>  
				</tboday>
			</table>
		</div>
<%  
	} // robot results  
}  
	if (!robotResults){ 
%> 
	<p>No Robot Framework test results found.</p>  
<%}%>
</body>
</html>
