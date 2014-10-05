<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:loadBundle basename="si.matjazcerkvenik.openmp3player.backend.om3p"
	var="bundle" />

<h:panelGrid columns="5">

	<h:form>
		<h:commandLink id="rewBtn" action="#{playerBean.prev}">
			<h:graphicImage url="img/rewind.png" styleClass="icon"
				alt="#{bundle.buttonLabelPrevious}" />
		</h:commandLink>
	</h:form>

	<h:form>
		<h:commandLink id="playBtn" action="#{playerBean.play}">
			<h:graphicImage url="img/play.png" styleClass="icon" alt="Play" />
		</h:commandLink>
	</h:form>

	<h:form>
		<h:commandLink id="fwdBtn" action="#{playerBean.next}">
			<h:graphicImage url="img/fast_forward.png" styleClass="icon"
				alt="Next" />
		</h:commandLink>
	</h:form>
	
	<h:form>
		<h:selectOneMenu onchange="submit()" 
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
<h:panelGrid id="ddPanel" columns="6">

	<h:form>
		<h:commandLink action="#">
			<h:graphicImage url="img/playlists.png" styleClass="icon" />
		</h:commandLink>
	</h:form>
	
	<h:form>
		<h:commandLink action="#">
			<h:graphicImage url="img/volumeDn.png" styleClass="icon" />
		</h:commandLink>
	</h:form>
	
	<h:form>
		<h:commandLink action="#">
			<h:graphicImage url="img/volumeUp.png" styleClass="icon" />
		</h:commandLink>
	</h:form>
	
	<h:form>
		<h:commandLink action="#">
			<h:graphicImage url="img/repeatOff.png" styleClass="icon" />
		</h:commandLink>
	</h:form>
	
	<h:form>
		<h:commandLink action="#">
			<h:graphicImage url="img/settings.png" styleClass="icon" />
		</h:commandLink>
	</h:form>
	
	<h:form>
		<h:commandLink action="#">
			<h:graphicImage url="img/help.png" styleClass="icon" />
		</h:commandLink>
	</h:form>
	
</h:panelGrid>
