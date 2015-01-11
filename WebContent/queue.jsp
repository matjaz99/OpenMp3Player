<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="o" uri="/WEB-INF/tld/omp3p-tags.tld"%>
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
			
			<h:panelGrid columns="2" width="100%">
					
					<h:panelGrid columns="2" styleClass="valign-middle">
						<h:form>
							<h:commandLink action="home">
								<h:graphicImage url="img/DMD.png" styleClass="icon"/>
							</h:commandLink>
						</h:form>
						<h:outputText value="Queue" styleClass="title" />
					</h:panelGrid>
					
					<jsp:include page="/includes/toolbar.jsp"></jsp:include>
				
			</h:panelGrid>
			
			<hr/>
			
			<jsp:include page="/includes/player-gui.jsp"></jsp:include>
			
			<hr/>
			
			
			
			
			
			<h:panelGrid columns="2" width="100%" rendered="#{playlistBean.queue}">
		
<%-- 				<h:panelGrid columns="2"> --%>
					
					<h:form>
						<h:panelGrid columns="3">
							<h:outputLabel value="Save queue as: " styleClass="text-white largeText"></h:outputLabel>
							<h:inputText value="#{queueBean.newQueueName}">
								<f:validator validatorId="playlistValidator"/>
							</h:inputText>
							<h:commandButton action="#{queueBean.saveQueue}" value="Save"></h:commandButton>
						</h:panelGrid>
						<h:messages layout="table" showDetail="true" showSummary="false" styleClass="error"></h:messages>
					</h:form>
					
<%-- 				</h:panelGrid> --%>
		
		
				<h:panelGroup styleClass="align-right valign-middle">
					<h:form>
						<h:commandLink action="#{queueBean.emptyQueue}">
							<h:outputLabel value="Empty queue " styleClass="text-white largeText"></h:outputLabel>
							<h:commandButton action="#{queueBean.emptyQueue}" value="Empty"></h:commandButton>
						</h:commandLink>
					</h:form>
				</h:panelGroup>
		
			</h:panelGrid>
			
			
			
			<hr/>
			
			<jsp:include page="/includes/currently-playing.jsp"></jsp:include>
			
			<hr/>
			
			<jsp:include page="/includes/playlist.jsp"></jsp:include>
			
			<hr/>
			
			<h:outputLabel value="Size: #{playlistBean.playlistSize}" styleClass="text-white"></h:outputLabel>
			
			<hr/>
			
			<jsp:include page="/includes/version.jsp"></jsp:include>
			
			<hr/>
			
			
		</h:panelGrid>
	
		
	
	</f:view>
	
</body>
</html>
