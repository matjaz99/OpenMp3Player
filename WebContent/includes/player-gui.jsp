<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="o" uri="/WEB-INF/tld/omp3p-tags.tld"%>

<f:subview id="header">

	<h:panelGrid columns="5">
	
		<h:form>
			<h:commandLink action="#{playerBean.prev}" title="#{bundle.buttonLabelPrevious}">
				<o:icon img="rewind"/>
			</h:commandLink>
		</h:form>
	
		<h:form rendered="#{!playerBean.playing}">
			<h:commandLink action="#{playerBean.play}" title="Play">
				<o:icon img="play"/>
			</h:commandLink>
		</h:form>
		
		<h:form rendered="#{playerBean.playing}">
			<h:commandLink action="#{playerBean.stop}" title="Stop">
				<o:icon img="stop"/>
			</h:commandLink>
		</h:form>
	
		<h:form>
			<h:commandLink action="#{playerBean.next}" title="Next">
				<o:icon img="fast_forward"/>
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
