<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:loadBundle basename="si.matjazcerkvenik.openmp3player.backend.om3p"
	var="bundle" />

<f:subview id="header">

	<h:panelGrid columns="5">
	
		<h:form>
			<h:commandLink action="#{playerBean.prev}">
				<h:graphicImage url="img/rewind.png" styleClass="icon"
					alt="#{bundle.buttonLabelPrevious}" 
					onmouseover="onMouse(this, 'img/rewind-shadow.png')" 
					onmouseout="onMouse(this, 'img/rewind.png')" 
					onmousedown="onMouse(this, 'img/rewind-pressed.png')" 
					onmouseup="onMouse(this, 'img/rewind-shadow.png')"/>
			</h:commandLink>
		</h:form>
	
		<h:form rendered="#{!playerBean.playing}">
			<h:commandLink action="#{playerBean.play}">
				<h:graphicImage url="img/play.png" styleClass="icon" alt="Play" 
					onmouseover="onMouse(this, 'img/play-shadow.png')" 
					onmouseout="onMouse(this, 'img/play.png')" 
					onmousedown="onMouse(this, 'img/play-pressed.png')" 
					onmouseup="onMouse(this, 'img/play-shadow.png')"/>
			</h:commandLink>
		</h:form>
		
		<h:form rendered="#{playerBean.playing}">
			<h:commandLink action="#{playerBean.stop}">
				<h:graphicImage url="img/stop.png" styleClass="icon" alt="Stop" 
					onmouseover="onMouse(this, 'img/stop-shadow.png')" 
					onmouseout="onMouse(this, 'img/stop.png')" 
					onmousedown="onMouse(this, 'img/stop-pressed.png')" 
					onmouseup="onMouse(this, 'img/stop-shadow.png')"/>
			</h:commandLink>
		</h:form>
	
		<h:form>
			<h:commandLink action="#{playerBean.next}">
				<h:graphicImage url="img/fast_forward.png" styleClass="icon" alt="Next" 
					onmouseover="onMouse(this, 'img/fast_forward-shadow.png')" 
					onmouseout="onMouse(this, 'img/fast_forward.png')" 
					onmousedown="onMouse(this, 'img/fast_forward-pressed.png')" 
					onmouseup="onMouse(this, 'img/fast_forward-shadow.png')"/>
			</h:commandLink>
		</h:form>
		
		<h:form>
			<h:selectOneMenu onchange="submit()" value="#{playerBean.selectedPlaylist}"
				valueChangeListener="#{playerBean.playlistChanged}">
				<f:selectItems value="#{playerBean.playlists}"/>
			</h:selectOneMenu>
		</h:form>
		
	
	</h:panelGrid>


</f:subview>
