<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:loadBundle basename="si.matjazcerkvenik.openmp3player.backend.om3p"
	var="bundle" />

<h:panelGrid columns="6">

	<h:form prependId="false">
		<h:commandLink action="#{playerBean.prev}">
			<h:graphicImage id="rewBtn" url="img/rewind.png" styleClass="icon"
				alt="#{bundle.buttonLabelPrevious}" 
				onmouseover="onMouse('#rewBtn', 'img/rewind-shadow.png')" 
				onmouseout="onMouse('#rewBtn', 'img/rewind.png')" 
				onmousedown="onMouse('#rewBtn', 'img/rewind-pressed.png')" 
				onmouseup="onMouse('#rewBtn', 'img/rewind-shadow.png')"/>
		</h:commandLink>
	</h:form>

	<h:form rendered="#{!playerBean.playing}" prependId="false">
		<h:commandLink action="#{playerBean.play}">
			<h:graphicImage id="playBtn" url="img/play.png" styleClass="icon" alt="Play" 
				onmouseover="onMouse('#playBtn', 'img/play-shadow.png')" 
				onmouseout="onMouse('#playBtn', 'img/play.png')" 
				onmousedown="onMouse('#playBtn', 'img/play-pressed.png')" 
				onmouseup="onMouse('#playBtn', 'img/play-shadow.png')"/>
		</h:commandLink>
	</h:form>
	
	<h:form rendered="#{playerBean.playing}" prependId="false">
		<h:commandLink action="#{playerBean.stop}">
			<h:graphicImage id="stopBtn" url="img/stop.png" styleClass="icon" alt="Stop" 
				onmouseover="onMouse('#stopBtn', 'img/stop-shadow.png')" 
				onmouseout="onMouse('#stopBtn', 'img/stop.png')" 
				onmousedown="onMouse('#stopBtn', 'img/stop-pressed.png')" 
				onmouseup="onMouse('#stopBtn', 'img/stop-shadow.png')"/>
		</h:commandLink>
	</h:form>

	<h:form prependId="false">
		<h:commandLink action="#{playerBean.next}">
			<h:graphicImage id="fwdBtn" url="img/fast_forward.png" styleClass="icon" alt="Next" 
				onmouseover="onMouse('#fwdBtn', 'img/fast_forward-shadow.png')" 
				onmouseout="onMouse('#fwdBtn', 'img/fast_forward.png')" 
				onmousedown="onMouse('#fwdBtn', 'img/fast_forward-pressed.png')" 
				onmouseup="onMouse('#fwdBtn', 'img/fast_forward-shadow.png')"/>
		</h:commandLink>
	</h:form>
	
	<h:form>
		<h:selectOneMenu onchange="submit()" value="#{playerBean.selectedPlaylist}"
			valueChangeListener="#{playerBean.playlistChanged}">
			<f:selectItems value="#{playerBean.playlists}"/>
		</h:selectOneMenu>
	</h:form>
		
	<h:panelGrid id="ddButton">
		<h:graphicImage id="ddPanelBtn" url="img/showMenu.png" styleClass="icon"
			onmouseover="onMouse('#ddPanelBtn', 'img/showMenu-shadow.png')" 
			onmouseout="onMouse('#ddPanelBtn', 'img/showMenu.png')" 
			onmousedown="onMouse('#ddPanelBtn', 'img/showMenu-pressed.png')" 
			onmouseup="onMouse('#ddPanelBtn', 'img/showMenu-shadow.png')" />
	</h:panelGrid>

</h:panelGrid>


<!-- ddPanel is below the guiPanel (and ddButton) -->
<h:panelGrid id="ddPanel" columns="8">

	<h:form prependId="false">
		<h:commandLink action="playlists">
			<h:graphicImage id="showPlaylistsBtn" url="img/playlists.png" styleClass="icon"
				onmouseover="onMouse('#showPlaylistsBtn', 'img/playlists-shadow.png')" 
				onmouseout="onMouse('#showPlaylistsBtn', 'img/playlists.png')" 
				onmousedown="onMouse('#showPlaylistsBtn', 'img/playlists-pressed.png')" 
				onmouseup="onMouse('#showPlaylistsBtn', 'img/playlists-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	<h:form prependId="false">
		<h:commandLink action="#{playerBean.volumeDown}">
			<h:graphicImage id="volumeDnBtn" url="img/volumeDn.png" styleClass="icon"
				onmouseover="onMouse('#volumeDnBtn', 'img/volumeDn-shadow.png')" 
				onmouseout="onMouse('#volumeDnBtn', 'img/volumeDn.png')" 
				onmousedown="onMouse('#volumeDnBtn', 'img/volumeDn-pressed.png')" 
				onmouseup="onMouse('#volumeDnBtn', 'img/volumeDn-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	<h:form prependId="false">
		<h:commandLink action="#{playerBean.volumeUp}">
			<h:graphicImage id="volumeUpBtn" url="img/volumeUp.png" styleClass="icon"
				onmouseover="onMouse('#volumeUpBtn', 'img/volumeUp-shadow.png')" 
				onmouseout="onMouse('#volumeUpBtn', 'img/volumeUp.png')" 
				onmousedown="onMouse('#volumeUpBtn', 'img/volumeUp-pressed.png')" 
				onmouseup="onMouse('#volumeUpBtn', 'img/volumeUp-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	<h:form rendered="#{playerBean.repeatOn}" prependId="false">
		<h:commandLink action="#{playerBean.turnRepeatOff}">
			<h:graphicImage id="repeatBtnOn" url="img/repeatOn.png" styleClass="icon"
				onmouseover="onMouse('#repeatBtnOn', 'img/repeatOn-shadow.png')" 
				onmouseout="onMouse('#repeatBtnOn', 'img/repeatOn.png')" 
				onmousedown="onMouse('#repeatBtnOn', 'img/repeatOn-pressed.png')" 
				onmouseup="onMouse('#repeatBtnOn', 'img/repeatOn-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	<h:form rendered="#{!playerBean.repeatOn}" prependId="false">
		<h:commandLink action="#{playerBean.turnRepeatOn}">
			<h:graphicImage id="repeatBtnOff" url="img/repeatOff.png" styleClass="icon"
				onmouseover="onMouse('#repeatBtnOff', 'img/repeatOff-shadow.png')" 
				onmouseout="onMouse('#repeatBtnOff', 'img/repeatOff.png')" 
				onmousedown="onMouse('#repeatBtnOff', 'img/repeatOff-pressed.png')" 
				onmouseup="onMouse('#repeatBtnOff', 'img/repeatOff-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	<h:form prependId="false">
		<h:commandLink action="#{playerBean.gotoQueue}">
			<h:graphicImage id="queueBtn0" url="img/toqueue.png" styleClass="icon"
				onmouseover="onMouse('#queueBtn0', 'img/toqueue-shadow.png')" 
				onmouseout="onMouse('#queueBtn0', 'img/toqueue.png')" 
				onmousedown="onMouse('#queueBtn0', 'img/toqueue-pressed.png')" 
				onmouseup="onMouse('#queueBtn0', 'img/toqueue-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	<h:form prependId="false">
		<h:inputText value="#{playerBean.newQueueName}" />
		<h:commandLink action="#{playerBean.saveQueue}">
			<h:graphicImage id="saveBtn" url="img/save.png" styleClass="icon"
				onmouseover="onMouse('#saveBtn', 'img/save-shadow.png')" 
				onmouseout="onMouse('#saveBtn', 'img/save.png')" 
				onmousedown="onMouse('#saveBtn', 'img/save-pressed.png')" 
				onmouseup="onMouse('#saveBtn', 'img/save-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	<h:form prependId="false">
		<h:commandLink action="settings">
			<h:graphicImage id="settingsBtn" url="img/settings.png" styleClass="icon"
				onmouseover="onMouse('#settingsBtn', 'img/settings-shadow.png')" 
				onmouseout="onMouse('#settingsBtn', 'img/settings.png')" 
				onmousedown="onMouse('#settingsBtn', 'img/settings-pressed.png')" 
				onmouseup="onMouse('#settingsBtn', 'img/settings-shadow.png')" />
		</h:commandLink>
	</h:form>
	
	<h:form prependId="false">
		<h:commandLink action="about">
			<h:graphicImage id="helpBtn" url="img/help.png" styleClass="icon"
				onmouseover="onMouse('#helpBtn', 'img/help-shadow.png')" 
				onmouseout="onMouse('#helpBtn', 'img/help.png')" 
				onmousedown="onMouse('#helpBtn', 'img/help-pressed.png')" 
				onmouseup="onMouse('#helpBtn', 'img/help-shadow.png')" />
		</h:commandLink>
	</h:form>
	
</h:panelGrid>
