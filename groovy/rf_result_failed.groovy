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
        color: #f5f5dc;
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
						<td class="title" colspan="4"><div><h2><span>${project.name}</span><span>  自动化测试报告</span></h2> <img src="data:image/png;base64,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" /></td>
					</tr>
					<tr>
						<td class="case"><b>详细报告</b></td>
						<td colspan="3" class="case"><a href="${rooturl}${build.url}robot/report/report.html">点击查看报告详情</a></td>
					</tr>
                                        <TR><TD class="case"><b>执行时间</b></TD><TD colspan="3" class="case">${it.timestampString}</TD></TR>
                                        <TR><TD class="case"><b>测试时长</b></TD><TD colspan="3" class="case">${build.durationString}</TD></TR>
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
						<td colspan="2" class="head"><b>失败用例</b></td> 
						<td class="head"><b>状态</b></td>
						<td class="head"><b>耗时</b></td> 
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
					<!--<tr>
						<td colspan="4" class="suite"><b><%= suiteName %></b></td>
					</tr> -->
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
<%              if(!tc.isPassed()){
%>

<%               if(${robotTestResultAction.getFailCount()} != 0) {
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
<% } %>
<%				}%>

<%                            }%> 
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
