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
		
		
		<h:panelGrid columns="1" styleClass="background" cellpadding="0" cellspacing="0" width="100%">
			
			<f:facet name="header">
				<h:form>
					<h:commandLink action="/">
						<h:outputText value="OpenMp3Player Settings" styleClass="title" />
					</h:commandLink>
				</h:form>
			</f:facet>
			
			<hr/>
			
			<h:form prependId="false">
				<h:commandLink action="home">
					<h:graphicImage id="backBtn" url="img/back.png" styleClass="icon" alt="Back"
						onmouseover="onMouse('#backBtn', 'img/back-shadow.png')" 
						onmouseout="onMouse('#backBtn', 'img/back.png')" 
						onmousedown="onMouse('#backBtn', 'img/back-pressed.png')" 
						onmouseup="onMouse('#backBtn', 'img/back-shadow.png')" />
					<h:outputLabel value="Back" styleClass="text-white largeText"></h:outputLabel>
				</h:commandLink>
			</h:form>
			
			<hr/>
			
			<h:outputText value="Connect to OpenMp3Player from remote device:" styleClass="text-white" />
			<h:outputLink value="http://#{settingsBean.localIp}:8080/OpenMp3Player/" >
				<h:outputText value="http://#{settingsBean.localIp}:8080/OpenMp3Player/" styleClass="text-white" />
			</h:outputLink>
			
			<hr/>
			
			<h:outputText value="Volume level: #{settingsBean.currentSoundLevel}" styleClass="text-white" />
			
			<hr/>
			
			<h:outputText value="CLI: #{settingsBean.telnetEnabled}" styleClass="text-white" />
			<h:outputText value="$ telnet #{settingsBean.localIp} #{settingsBean.telnetPort}" styleClass="text-white" />
			<h:outputText value="> help" styleClass="text-white" />
			
			<hr/>
			
			<jsp:include page="/includes/version.jsp"></jsp:include>
			
			<hr/>
			
		</h:panelGrid>
		
	</f:view>
	
</body>
</html>
