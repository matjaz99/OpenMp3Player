<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:subview id="currently-playing">

	<h:panelGrid columns="2" width="100%">
		
		<h:panelGrid columns="2">
			<h:form>
				<h:commandLink action="#">
					<h:graphicImage url="img/refresh.png" styleClass="icon" alt="Refresh" 
						onmouseover="onMouse(this, 'img/refresh-shadow.png')" 
						onmouseout="onMouse(this, 'img/refresh.png')" 
						onmousedown="onMouse(this, 'img/refresh-pressed.png')" 
						onmouseup="onMouse(this, 'img/refresh-shadow.png')"/>
				</h:commandLink>
			</h:form>
			
			<h:panelGrid columns="1">
				<h:outputLabel value="Active playlist: #{playlistBean.activePlaylistName}" styleClass="text-white" />
				<h:outputLabel value="Playing: #{playerBean.currentlyPlaying}" styleClass="text-white largeText" />
			</h:panelGrid>
		</h:panelGrid>
		
		
		<h:panelGroup style="text-align: right;">
			<h:form rendered="#{playerBean.repeatOn}">
				<h:commandLink action="#{playerBean.turnRepeatOff}">
					<h:graphicImage url="img/repeatOn.png" styleClass="icon"
						onmouseover="onMouse(this, 'img/repeatOn-shadow.png')" 
						onmouseout="onMouse(this, 'img/repeatOn.png')" 
						onmousedown="onMouse(this, 'img/repeatOn-pressed.png')" 
						onmouseup="onMouse(this, 'img/repeatOn-shadow.png')" />
				</h:commandLink>
			</h:form>
			
			<h:form rendered="#{!playerBean.repeatOn}">
				<h:commandLink action="#{playerBean.turnRepeatOn}">
					<h:graphicImage url="img/repeatOff.png" styleClass="icon"
						onmouseover="onMouse(this, 'img/repeatOff-shadow.png')" 
						onmouseout="onMouse(this, 'img/repeatOff.png')" 
						onmousedown="onMouse(this, 'img/repeatOff-pressed.png')" 
						onmouseup="onMouse(this, 'img/repeatOff-shadow.png')" />
				</h:commandLink>
			</h:form>
		</h:panelGroup>
		
	</h:panelGrid>
	
</f:subview>