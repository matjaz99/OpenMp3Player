<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:loadBundle basename="si.matjazcerkvenik.openmp3player.backend.om3p"
	var="bundle" />

<h:panelGrid columns="4">

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
		<h:selectOneMenu value="salsa.xml" onchange="submit()" 
			valueChangeListener="#{playerBean.playlistChanged}">
			<f:selectItems value="#{playerBean.playlists}"/>
		</h:selectOneMenu>
	</h:form>

</h:panelGrid>

