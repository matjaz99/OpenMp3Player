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
<title>OpenMp3Player</title>
</head>
<body>
	
	<f:view>
		
		<h:panelGrid columns="1" styleClass="background">
			
			<h:form>
			<h:commandLink action="/">
				<h:outputLabel value="Queue" styleClass="title"></h:outputLabel>
			</h:commandLink>
			</h:form>
			
			
			<hr/>
			
			<%@ include file="/includes/player-gui.jsp" %>
			
			<hr/>
			
			<h:form prependId="false" rendered="#{playlistBean.queue}">
				<h:panelGrid columns="3">
					<h:outputLabel value="Name: "></h:outputLabel>
					<h:inputText value="#{queueBean.newQueueName}">
						<f:validator validatorId="playlistValidator"/>
					</h:inputText>
					<h:commandButton action="#{queueBean.saveQueue}" value="Save"></h:commandButton>
				</h:panelGrid>
				<h:messages layout="table" showDetail="true" showSummary="false" styleClass="error"></h:messages>
				<hr/>
			</h:form>
			
			
			<h:form rendered="#{playlistBean.queue}">
				<h:commandLink action="#{queueBean.emptyQueue}">
					<h:outputLabel value="Empty queue"></h:outputLabel>
					<h:graphicImage id="emptyBtn" url="img/empty.png" styleClass="icon" alt="Empty"
						onmouseover="onMouse('#emptyBtn', 'img/empty-shadow.png')" 
						onmouseout="onMouse('#emptyBtn', 'img/empty.png')" 
						onmousedown="onMouse('#emptyBtn', 'img/empty-pressed.png')" 
						onmouseup="onMouse('#emptyBtn', 'img/empty-shadow.png')" />
				</h:commandLink>
				<hr/>
			</h:form>
			
			
			
			
			<h:panelGrid columns="2">
				<h:form>
					<h:commandLink action="#">
						<h:graphicImage url="img/refresh.png" styleClass="icon" />
					</h:commandLink>
				</h:form>
				<h:outputLabel value="Playing: #{playerBean.currentlyPlaying}"></h:outputLabel>
			</h:panelGrid>
			
			<hr/>
			
			<%@ include file="/includes/playlist.jsp" %>
			
			<hr/>
			
			<h:outputLabel value="Size: #{playlistBean.playlistSize}"></h:outputLabel>
			
			<hr/>
			
			<h:outputLabel value="Version: #{settingsBean.version}"></h:outputLabel>
			
			<hr/>
			
			
		</h:panelGrid>
	
		
	
	</f:view>
	
</body>
</html>
