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
			
			
			
				<h:panelGrid columns="2" width="100%">
					
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
						<h:form>
							<h:commandLink action="/" value="OpenMp3Player" styleClass="title" />
						</h:form>
					</h:panelGrid>
					
					
					<h:panelGroup style="text-align: right;">
						<h:form>
							<h:commandLink action="playlists">
								<h:graphicImage url="img/playlists.png" styleClass="icon"
									onmouseover="onMouse(this, 'img/playlists-shadow.png')" 
									onmouseout="onMouse(this, 'img/playlists.png')" 
									onmousedown="onMouse(this, 'img/playlists-pressed.png')" 
									onmouseup="onMouse(this, 'img/playlists-shadow.png')" />
							</h:commandLink>
							<h:commandLink action="#{playerBean.gotoQueue}">
								<h:graphicImage url="img/queue.png" styleClass="icon"
									onmouseover="onMouse(this, 'img/queue-shadow.png')" 
									onmouseout="onMouse(this, 'img/queue.png')" 
									onmousedown="onMouse(this, 'img/queue-pressed.png')" 
									onmouseup="onMouse(this, 'img/queue-shadow.png')" />
							</h:commandLink>
							<h:graphicImage url="img/separator.png" styleClass="icon" />
							<h:commandLink action="#{playerBean.volumeDown}">
								<h:graphicImage url="img/volumeDn.png" styleClass="icon"
									onmouseover="onMouse(this, 'img/volumeDn-shadow.png')" 
									onmouseout="onMouse(this, 'img/volumeDn.png')" 
									onmousedown="onMouse(this, 'img/volumeDn-pressed.png')" 
									onmouseup="onMouse(this, 'img/volumeDn-shadow.png')" />
							</h:commandLink>
							<h:commandLink action="#{playerBean.volumeUp}">
								<h:graphicImage url="img/volumeUp.png" styleClass="icon"
									onmouseover="onMouse(this, 'img/volumeUp-shadow.png')" 
									onmouseout="onMouse(this, 'img/volumeUp.png')" 
									onmousedown="onMouse(this, 'img/volumeUp-pressed.png')" 
									onmouseup="onMouse(this, 'img/volumeUp-shadow.png')" />
							</h:commandLink>
							<h:graphicImage url="img/separator.png" styleClass="icon" />
							<h:commandLink action="settings">
								<h:graphicImage url="img/settings.png" styleClass="icon"
									onmouseover="onMouse(this, 'img/settings-shadow.png')" 
									onmouseout="onMouse(this, 'img/settings.png')" 
									onmousedown="onMouse(this, 'img/settings-pressed.png')" 
									onmouseup="onMouse(this, 'img/settings-shadow.png')" />
							</h:commandLink>
							<h:commandLink action="about">
								<h:graphicImage url="img/help.png" styleClass="icon"
									onmouseover="onMouse(this, 'img/help-shadow.png')" 
									onmouseout="onMouse(this, 'img/help.png')" 
									onmousedown="onMouse(this, 'img/help-pressed.png')" 
									onmouseup="onMouse(this, 'img/help-shadow.png')" />
							</h:commandLink>
						</h:form>
					</h:panelGroup>
				
				</h:panelGrid>
			
			
			
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
