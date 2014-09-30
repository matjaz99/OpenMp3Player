<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:loadBundle basename="si.matjazcerkvenik.openmp3player.backend.om3p" var="bundle"/>


<h:panelGrid columns="1" >
	
	<h:dataTable value="#{plistMng.activePlaylist.mp3Files}" var="mp3File">
		
		<h:column>
			
			<h:form>
				<h:commandLink id="playBtn" action="#{playerBean.play}">
					<h:graphicImage url="img/play.png" styleClass="icon" alt="Play" />
				</h:commandLink>
			</h:form>
			
		</h:column>
		
		<h:column>
			
			<h:outputText value="#{mp3File.title}" />
			
		</h:column>
		
		<h:column>
			
			<h:form>
				<h:commandLink id="queueBtn" action="#{playerBean.play}">
					<h:graphicImage url="img/toqueue.png" styleClass="icon" alt="Put to queue" />
				</h:commandLink>
			</h:form>
			
		</h:column>
		
	</h:dataTable>
	
</h:panelGrid>