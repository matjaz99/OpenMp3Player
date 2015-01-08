<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="o" uri="/WEB-INF/tld/omp3p-tags.tld"%>

<f:loadBundle basename="si.matjazcerkvenik.openmp3player.backend.om3p" var="bundle"/>


<h:panelGrid columns="1" width="100%" >
	
	<h:dataTable value="#{playlistBean.mp3List}" binding="#{playlistBean.dataTable}" var="mp3File"
		rowClasses="table-odd-row,table-even-row" cellpadding="0" cellspacing="0" width="100%" >
		
		<h:column>
			
			<h:form>
				<h:commandLink action="#{playlistBean.play}">
					<h:graphicImage url="img/play.png" styleClass="icon" alt="Play"
						onmouseover="onMouse(this, 'img/play-shadow.png')" 
						onmouseout="onMouse(this, 'img/play.png')" 
						onmousedown="onMouse(this, 'img/play-pressed.png')" 
						onmouseup="onMouse(this, 'img/play-shadow.png')" />
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
			
			<h:form prependId="false" rendered="#{!playlistBean.queue}">
				<h:commandLink action="#{playlistBean.putToQueue}">
					<h:graphicImage id="qBtn" url="img/toqueue.png" styleClass="icon" alt="Put to queue"
						onmouseover="onMouse(this, 'img/toqueue-shadow.png')" 
						onmouseout="onMouse(this, 'img/toqueue.png')" 
						onmousedown="onMouse(this, 'img/toqueue-pressed.png')" 
						onmouseup="onMouse(this, 'img/toqueue-shadow.png')" />
				</h:commandLink>
			</h:form>
			
		</h:column>
		
		<h:column>
			<h:form prependId="false">
				<h:commandLink action="#{playlistBean.removeMp3FromTheList}">
					<h:graphicImage id="queueBtn" url="img/remove.png" styleClass="icon" alt="Remove"
						onmouseover="onMouse(this, 'img/remove-shadow.png')" 
						onmouseout="onMouse(this, 'img/remove.png')" 
						onmousedown="onMouse(this, 'img/remove-pressed.png')" 
						onmouseup="onMouse(this, 'img/remove-shadow.png')" />
				</h:commandLink>
			</h:form>
		</h:column>
		
	</h:dataTable>
	
</h:panelGrid>