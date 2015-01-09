<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="o" uri="/WEB-INF/tld/omp3p-tags.tld"%>

<f:subview id="currently-playing">

	<h:panelGrid columns="2" width="100%">
		
		<h:panelGrid columns="2">
			<h:form>
				<h:commandLink action="#">
					<o:icon img="refresh"/>
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
					<o:icon img="repeatOn"/>
				</h:commandLink>
			</h:form>
			
			<h:form rendered="#{!playerBean.repeatOn}">
				<h:commandLink action="#{playerBean.turnRepeatOn}">
					<o:icon img="repeatOff"/>
				</h:commandLink>
			</h:form>
		</h:panelGroup>
		
	</h:panelGrid>
	
</f:subview>