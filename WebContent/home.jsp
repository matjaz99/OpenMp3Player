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
			
			
			<h:form prependId="false">
				<h:panelGrid columns="2" cellpadding="0" cellspacing="3" width="100%">
					
					
					<h:panelGrid columns="2" cellpadding="0" cellspacing="0" styleClass="valign-middle">
						<h:commandLink action="/">
							<h:graphicImage id="dmdBtn" url="img/DMD.png" styleClass="icon"
								onmouseover="onMouse(this, 'img/DMD.png')" 
								onmouseout="onMouse(this, 'img/DMD.png')" 
								onmousedown="onMouse(this, 'img/DMD.png')" 
								onmouseup="onMouse(this, 'img/DMD.png')" />
						</h:commandLink>
						<h:commandLink action="/" value="OpenMp3Player" styleClass="title" />
					</h:panelGrid>
					<h:panelGroup>
						<h:commandLink action="settings">
							<h:graphicImage id="settingsBtn" url="img/settings.png" styleClass="icon"
								onmouseover="onMouse(this, 'img/settings-shadow.png')" 
								onmouseout="onMouse(this, 'img/settings.png')" 
								onmousedown="onMouse(this, 'img/settings-pressed.png')" 
								onmouseup="onMouse(this, 'img/settings-shadow.png')" />
						</h:commandLink>
						<h:commandLink action="about">
							<h:graphicImage id="helpBtn" url="img/help.png" styleClass="icon"
								onmouseover="onMouse(this, 'img/help-shadow.png')" 
								onmouseout="onMouse(this, 'img/help.png')" 
								onmousedown="onMouse(this, 'img/help-pressed.png')" 
								onmouseup="onMouse(this, 'img/help-shadow.png')" />
						</h:commandLink>
					</h:panelGroup>
				
				</h:panelGrid>
			</h:form>
			
			
			<hr/>
			
			
			<jsp:include page="/includes/player-gui.jsp"></jsp:include>
			
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
