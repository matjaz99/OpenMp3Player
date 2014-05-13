<%@page import="si.matjazcerkvenik.openmp3player.backend.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/mstyles.css"/>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/omp3p.js"></script>
<title>OpenMp3Player::Settings</title>
</head>
<body>


<section>




<div>
<h2>OpenMp3Player Settings</h2>
</div>

<hr/>

<button onclick="openPlayer()">Back to player</button>
<hr/>

<article>
<p>Connect to OpenMp3Player from remote device:</p>
<% 
String url = "http://" + Utils.getLocalIp() + ":8080/OpenMp3Player/";
%>
<a href="<%=url %>" style="font-size: 28px; color: #88EEEE"><%=url %></a>
</article>


<hr/>


<article>
<p>Volume level: <%=Utils.CURRENT_VOLUME_LEVEL %></p>

</article>


<hr/>


<article>
<p>CLI: <%=Utils.TELNET_ENABLED %></p>
<p style="font-size: 28px; color: #88EEEE; font-family: monospace;">$ telnet <%=Utils.getLocalIp() + " " + Utils.TELNET_PORT%></p>
<p style="font-size: 28px; color: #88EEEE; font-family: monospace;">&gt; help</p>
</article>


<hr/>


<article>
<p></p>

</article>




	
</section>


</body>
</html>