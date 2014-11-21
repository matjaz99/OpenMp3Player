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
			
			<h:form prependId="false">
				<h:commandLink action="#{playlistBean.play}">
					<h:graphicImage id="playBtn" url="img/play.png" styleClass="icon" alt="Play"
						onmouseover="onMouse('#playBtn#{mp3File.index}', 'img/play-shadow.png')" 
						onmouseout="onMouse('#playBtn#{mp3File.index}', 'img/play.png')" 
						onmousedown="onMouse('#playBtn#{mp3File.index}', 'img/play-pressed.png')" 
						onmouseup="onMouse('#playBtn#{mp3File.index}', 'img/play-shadow.png')" />
				</h:commandLink>
			</h:form>
			
		</h:column>
		
		<h:column>
			
			<o:tags value="#{mp3File.tags}"/>
			
			<h:form>
			
				<h:commandLink action="#{playlistBean.showSongDetails}">
					
					<h:outputText value="#{mp3File.title}" styleClass="blackText" />
				</h:commandLink>
			
			</h:form>
			<h:outputText value="#{mp3File.artist}" styleClass="smalltext" />
			
		</h:column>
		
		
		<h:column>
			
			<h:form prependId="false" rendered="#{!playlistBean.queue}">
				<h:commandLink action="#{playlistBean.putToQueue}">
					<h:graphicImage id="qBtn" url="img/toqueue.png" styleClass="icon" alt="Put to queue"
						onmouseover="onMouse('#qBtn#{mp3File.index}', 'img/toqueue-shadow.png')" 
						onmouseout="onMouse('#qBtn#{mp3File.index}', 'img/toqueue.png')" 
						onmousedown="onMouse('#qBtn#{mp3File.index}', 'img/toqueue-pressed.png')" 
						onmouseup="onMouse('#qBtn#{mp3File.index}', 'img/toqueue-shadow.png')" />
				</h:commandLink>
			</h:form>
			
		</h:column>
		
		<h:column>
			<h:form prependId="false">
				<h:commandLink action="#{playlistBean.removeMp3FromTheList}">
					<h:graphicImage id="queueBtn" url="img/remove.png" styleClass="icon" alt="Remove"
						onmouseover="onMouse('#queueBtn', 'img/remove-shadow.png')" 
						onmouseout="onMouse('#queueBtn', 'img/remove.png')" 
						onmousedown="onMouse('#queueBtn', 'img/remove-pressed.png')" 
						onmouseup="onMouse('#queueBtn', 'img/remove-shadow.png')" />
				</h:commandLink>
			</h:form>
		</h:column>
		
	</h:dataTable>
	
</h:panelGrid>