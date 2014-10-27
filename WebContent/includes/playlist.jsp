<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:loadBundle basename="si.matjazcerkvenik.openmp3player.backend.om3p" var="bundle"/>


<h:panelGrid columns="1" >
	
	<h:dataTable value="#{playlistBean.mp3List}" binding="#{playlistBean.dataTable}" var="mp3File">
		
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
			
			<h:form>
				<h:commandLink action="song">
					<f:param name="id" value="#{mp3File.index}"></f:param>
					<h:outputText value="#{mp3File.title}" />
				</h:commandLink>
			</h:form>
			
			
		</h:column>
		
		<h:column>
			
			<h:form prependId="false">
				<h:commandLink action="#{playlistBean.putToQueue}">
					<h:graphicImage id="qBtn" url="img/toqueue.png" styleClass="icon" alt="Put to queue"
						onmouseover="onMouse('#qBtn#{mp3File.index}', 'img/toqueue-shadow.png')" 
						onmouseout="onMouse('#qBtn#{mp3File.index}', 'img/toqueue.png')" 
						onmousedown="onMouse('#qBtn#{mp3File.index}', 'img/toqueue-pressed.png')" 
						onmouseup="onMouse('#qBtn#{mp3File.index}', 'img/toqueue-shadow.png')" />
				</h:commandLink>
			</h:form>
			
		</h:column>
		
	</h:dataTable>
	
</h:panelGrid>