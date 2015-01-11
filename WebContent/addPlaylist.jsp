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
<title>OpenMp3Player Add Playlist</title>
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
						<h:outputText value="Add new playlist" styleClass="title" />
					</h:panelGrid>
					
					<jsp:include page="/includes/toolbar.jsp"></jsp:include>
				
			</h:panelGrid>
			
			<hr/>
			
			<h:panelGrid columns="2" styleClass="valign-middle">
				<h:form>
					<h:commandLink action="playlists">
						<o:icon img="back"/>
					</h:commandLink>
				</h:form>
				<h:form>
					<h:commandLink action="playlists">
						<h:outputLabel value="Back" styleClass="text-white largeText"></h:outputLabel>
					</h:commandLink>
				</h:form>
			</h:panelGrid>
			
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
