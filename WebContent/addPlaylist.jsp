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
<title>OpenMp3Player Add Playlist</title>
</head>
<body>
	
	<f:view>
		
		<h:panelGrid columns="1" styleClass="background" cellpadding="0" cellspacing="0" width="100%">
			
			<h:outputLabel value="Add new playlist" styleClass="title"></h:outputLabel>
			
			<hr/>
			
			<h:form prependId="false">
				<h:commandLink action="playlists">
					<h:graphicImage id="backBtn" url="img/back.png" styleClass="icon" alt="Back"
						onmouseover="onMouse('#backBtn', 'img/back-shadow.png')" 
						onmouseout="onMouse('#backBtn', 'img/back.png')" 
						onmousedown="onMouse('#backBtn', 'img/back-pressed.png')" 
						onmouseup="onMouse('#backBtn', 'img/back-shadow.png')" />
					<h:outputLabel value="Back" styleClass="text-white largeText"></h:outputLabel>
				</h:commandLink>
			</h:form>
			
			<hr/>
				
				<h:form>
					<h:panelGrid columns="2">
						<h:outputLabel value="Name: " styleClass="text-white"></h:outputLabel>
						<h:inputText value="#{addPlaylistBean.name}">
							<f:validator validatorId="playlistValidator"/>
						</h:inputText>
						<h:outputLabel value="Directory: " styleClass="text-white"></h:outputLabel>
						<h:inputText value="#{addPlaylistBean.source}" />
						<h:commandButton action="#{addPlaylistBean.addPlaylist}" value="Add"></h:commandButton>
					</h:panelGrid>
					
				</h:form>
			<h:messages layout="table" showDetail="true" showSummary="false" styleClass="error"></h:messages>
			<hr/>
			
			<jsp:include page="/includes/version.jsp"></jsp:include>
			
			<hr/>
			
			
		</h:panelGrid>
	
		
	
	</f:view>
	
</body>
</html>
