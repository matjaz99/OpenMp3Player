<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="o" uri="/WEB-INF/tld/omp3p-tags.tld"%>

<f:loadBundle basename="si.matjazcerkvenik.openmp3player.backend.om3p" var="bundle"/>

<f:subview id="playlists">

	<h:panelGrid columns="1" styleClass="background" width="100%" >
		
		<h:dataTable value="#{playlistBean.mp3List}" binding="#{playlistBean.dataTable}" var="mp3File"
			rowClasses="table-odd-row,table-even-row" cellpadding="0" cellspacing="0" width="100%" >
			
			<h:column>
				
				<h:form>
					<h:commandLink action="#{playlistBean.play}">
						<o:icon img="play"/>
					</h:commandLink>
				</h:form>
				
			</h:column>
			
			<h:column>
				
				<o:tags value="#{mp3File.tags}"/>
				
				<h:form>
				
					<h:commandLink action="#{playlistBean.showSongDetails}">
						<h:outputText value="#{mp3File.title}" styleClass="largeText" />
					</h:commandLink>
				
				</h:form>
				<h:outputText value="#{mp3File.artist}" />
				
			</h:column>
			
			
			<h:column>
				
				<h:form rendered="#{!playlistBean.queue}">
					<h:commandLink action="#{playlistBean.putToQueue}" title="Put to queue">
						<o:icon img="toqueue"/>
					</h:commandLink>
				</h:form>
				
			</h:column>
			
			<h:column>
				<h:form>
					<h:commandLink action="#{playlistBean.removeMp3FromTheList}" title="Remove">
						<o:icon img="remove"/>
					</h:commandLink>
				</h:form>
			</h:column>
			
		</h:dataTable>
		
	</h:panelGrid>

</f:subview>

