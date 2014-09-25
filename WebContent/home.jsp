<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/mstyles.css"/>
<title>OpenMp3Player</title>
</head>
<body>
	
	<f:view>
		
		<h:panelGrid columns="1" styleClass="background">
			
			<h:outputLabel value="OpenMp3Player" styleClass="title"></h:outputLabel>
			
			<hr/>
			
			<%@ include file="/includes/player-gui.jsp" %>
			
			<hr/>
			
			<%@ include file="/includes/playlist.jsp" %>
			
			<hr/>
			
			<h:outputLabel value="Version:"></h:outputLabel>
			
			<hr/>
			
		</h:panelGrid>
	
		
	
	</f:view>
	
</body>
</html>