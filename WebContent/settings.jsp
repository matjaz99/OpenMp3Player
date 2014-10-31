<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/omp3p.js"></script>
<link rel="stylesheet" type="text/css" href="css/mstyles.css"/>
<title>OpenMp3Player settings</title>
</head>
<body>
	
	<f:view>
		
		
		<h:panelGrid columns="1" styleClass="background">
			
			<h:outputText value="OpenMp3Player Settings" styleClass="title" />
			
			<h:form>
				<h:commandLink value="Back" action="home" />
			</h:form>
			
			<hr/>
			
			<h:outputText value="Connect to OpenMp3Player from remote device:" />
			<h:outputLink value="http://#{settingsBean.localIp}:8080/OpenMp3Player/" >
				<h:outputText value="http://#{settingsBean.localIp}:8080/OpenMp3Player/" />
			</h:outputLink>
			
			<hr/>
			
			<h:outputText value="Volume level: #{settingsBean.currentSoundLevel}" />
			
			<hr/>
			
			<h:outputText value="CLI: #{settingsBean.telnetEnabled}" />
			<h:outputText value="$ telnet #{settingsBean.localIp} #{settingsBean.telnetPort}" />
			<h:outputText value="> help" />
			
			<hr/>
			
			<h:outputLabel value="Version: #{settingsBean.version}"></h:outputLabel>
			
			<hr/>
			
		</h:panelGrid>
		
	</f:view>
	
</body>
</html>
