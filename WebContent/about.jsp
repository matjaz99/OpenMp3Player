<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/mstyles.css"/>
<title>About</title>
</head>
<body>

	<f:view>
		
		<h:panelGrid columns="1" styleClass="background">
			
			
			<h:outputText value="About OpenMp3Player" styleClass="title" />
			
			<hr/>
			
			<h:outputText value="Version: #{playerBean.version}"></h:outputText>
			
			<hr/>
			
			<%@ include file="/includes/about-data.jsp" %>
			
		</h:panelGrid>

	</f:view>
	
</body>
</html>