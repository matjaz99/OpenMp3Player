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
				<h:outputLabel value="Queue" styleClass="title"></h:outputLabel>
			</h:panelGrid>
			
			<hr/>
			
			<%@ include file="/includes/player-gui.jsp" %>
			
			<hr/>
			
			<h:form prependId="false" rendered="#{playlistBean.queue}">
				<h:panelGrid columns="3">
					<h:outputLabel value="Name: " styleClass="text-white"></h:outputLabel>
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
					<h:outputLabel value="Empty queue" styleClass="text-white"></h:outputLabel>
					<h:graphicImage id="emptyBtn" url="img/empty.png" styleClass="icon" alt="Empty"
						onmouseover="onMouse(this, 'img/empty-shadow.png')" 
						onmouseout="onMouse(this, 'img/empty.png')" 
						onmousedown="onMouse(this, 'img/empty-pressed.png')" 
						onmouseup="onMouse(this, 'img/empty-shadow.png')" />
				</h:commandLink>
				<hr/>
			</h:form>
			
			
			
			
			<hr/>
			
			<jsp:include page="/includes/currently-playing.jsp"></jsp:include>
			
			<hr/>
			
			<%@ include file="/includes/playlist.jsp" %>
			
			<hr/>
			
			<h:outputLabel value="Size: #{playlistBean.playlistSize}" styleClass="text-white"></h:outputLabel>
			
			<hr/>
			
			<jsp:include page="/includes/version.jsp"></jsp:include>
			
			<hr/>
			
			
		</h:panelGrid>
	
		
	
	</f:view>
	
</body>
</html>
