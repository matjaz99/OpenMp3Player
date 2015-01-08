<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:loadBundle basename="si.matjazcerkvenik.openmp3player.backend.om3p"
	var="bundle" />

<f:subview id="header">

<h:panelGrid columns="6">

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
	
	
	<!-- dd -->
	

</h:panelGrid>


<!-- ddPanel is below the guiPanel (and ddButton) -->
<h:panelGrid id="ddPanel" columns="8">

	
	<h:form>
		<h:commandLink action="playlists">
			<h:graphicImage url="img/playlists.png" styleClass="icon"
				onmouseover="onMouse(this, 'img/playlists-shadow.png')" 
				onmouseout="onMouse(this, 'img/playlists.png')" 
				onmousedown="onMouse(this, 'img/playlists-pressed.png')" 
				onmouseup="onMouse(this, 'img/playlists-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	<h:form>
		<h:commandLink action="#{playerBean.volumeDown}">
			<h:graphicImage url="img/volumeDn.png" styleClass="icon"
				onmouseover="onMouse(this, 'img/volumeDn-shadow.png')" 
				onmouseout="onMouse(this, 'img/volumeDn.png')" 
				onmousedown="onMouse(this, 'img/volumeDn-pressed.png')" 
				onmouseup="onMouse(this, 'img/volumeDn-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	<h:form>
		<h:commandLink action="#{playerBean.volumeUp}">
			<h:graphicImage url="img/volumeUp.png" styleClass="icon"
				onmouseover="onMouse(this, 'img/volumeUp-shadow.png')" 
				onmouseout="onMouse(this, 'img/volumeUp.png')" 
				onmousedown="onMouse(this, 'img/volumeUp-pressed.png')" 
				onmouseup="onMouse(this, 'img/volumeUp-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	
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
	
	<h:form>
		<h:commandLink action="#{playerBean.gotoQueue}">
			<h:graphicImage url="img/queue.png" styleClass="icon"
				onmouseover="onMouse(this, 'img/queue-shadow.png')" 
				onmouseout="onMouse(this, 'img/queue.png')" 
				onmousedown="onMouse(this, 'img/queue-pressed.png')" 
				onmouseup="onMouse(this, 'img/queue-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	
</h:panelGrid>

</f:subview>
