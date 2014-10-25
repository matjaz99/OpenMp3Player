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
<title>OpenMp3Player Playlists</title>
</head>
<body>
	
	<f:view>
		
		<h:panelGrid columns="1" styleClass="background">
			
			<h:outputLabel value="Playlists" styleClass="title"></h:outputLabel>
			
			<h:form>
				<h:commandLink value="Back" action="home" />
			</h:form>
			<h:form>
				<h:commandLink value="Add new" action="addPlaylist" />
			</h:form>
			
			<hr/>
			
			<h:dataTable value="#{playlistsBean.playlistsList}" binding="#{playlistsBean.dataTable}" var="plist">
				
				<h:column>
					<h:form prependId="false">
						<h:commandLink action="#{playlistsBean.gotoPlaylist}">
							<h:graphicImage id="playBtn" url="img/folder.png" styleClass="icon" alt="Goto"
								onmouseover="onMouse('#playBtn', 'img/folder-shadow.png')" 
								onmouseout="onMouse('#playBtn', 'img/folder.png')" 
								onmousedown="onMouse('#playBtn', 'img/folder-pressed.png')" 
								onmouseup="onMouse('#playBtn', 'img/folder-shadow.png')" />
						</h:commandLink>
					</h:form>
				</h:column>
				
				<h:column>
					<h:outputText value="#{plist.name} - " />
					<h:outputText value="#{plist.source}" />
				</h:column>
				
				<h:column>
					<h:form prependId="false" rendered="#{!plist.queue}">
						<h:commandLink action="#{playlistsBean.deletePlaylist}">
							<h:graphicImage id="queueBtn" url="img/remove.png" styleClass="icon" alt="Remove"
								onmouseover="onMouse('#queueBtn', 'img/remove-shadow.png')" 
								onmouseout="onMouse('#queueBtn', 'img/remove.png')" 
								onmousedown="onMouse('#queueBtn', 'img/remove-pressed.png')" 
								onmouseup="onMouse('#queueBtn', 'img/remove-shadow.png')" />
						</h:commandLink>
					</h:form>
					<h:form prependId="false" rendered="#{plist.queue}">
						<h:commandLink action="#{playlistsBean.deletePlaylist}">
							<h:graphicImage id="emptyBtn" url="img/empty.png" styleClass="icon" alt="Empty"
								onmouseover="onMouse('#emptyBtn', 'img/empty-shadow.png')" 
								onmouseout="onMouse('#emptyBtn', 'img/empty.png')" 
								onmousedown="onMouse('#emptyBtn', 'img/empty-pressed.png')" 
								onmouseup="onMouse('#emptyBtn', 'img/empty-shadow.png')" />
						</h:commandLink>
					</h:form>
				</h:column>
				
			</h:dataTable>
			
			<hr/>
			
			<h:outputLabel value="Version: #{playerBean.version}"></h:outputLabel>
			
			<hr/>
			
			
		</h:panelGrid>
	
		
	
	</f:view>
	
</body>
</html>
