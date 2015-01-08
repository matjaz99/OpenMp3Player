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
			
			<h:panelGrid columns="2" styleClass="valign-middle">
				<h:form>
					<h:commandLink action="/">
						<h:graphicImage url="img/DMD.png" styleClass="icon"
							onmouseover="onMouse(this, 'img/DMD.png')" 
							onmouseout="onMouse(this, 'img/DMD.png')" 
							onmousedown="onMouse(this, 'img/DMD.png')" 
							onmouseup="onMouse(this, 'img/DMD.png')" />
					</h:commandLink>
				</h:form>
				<h:outputLabel value="OpenMp3Player Settings" styleClass="title"></h:outputLabel>
			</h:panelGrid>
			
			<hr/>
			
			<h:panelGrid columns="2" styleClass="valign-middle">
				<h:form>
					<h:commandLink action="home">
						<h:graphicImage url="img/back.png" styleClass="icon" alt="Back"
							onmouseover="onMouse(this, 'img/back-shadow.png')" 
							onmouseout="onMouse(this, 'img/back.png')" 
							onmousedown="onMouse(this, 'img/back-pressed.png')" 
							onmouseup="onMouse(this, 'img/back-shadow.png')" />
					</h:commandLink>
				</h:form>
				<h:form>
					<h:commandLink action="home">
						<h:outputLabel value="Back" styleClass="text-white largeText"></h:outputLabel>
					</h:commandLink>
				</h:form>
			</h:panelGrid>
			
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
