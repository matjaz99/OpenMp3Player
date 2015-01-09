<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="o" uri="/WEB-INF/tld/omp3p-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/omp3p.js"></script>
<link rel="stylesheet" type="text/css" href="css/mstyles.css"/>
<title>OpenMp3Player Playlists</title>
</head>
<body>
	
	<f:view>
		
		<h:panelGrid columns="1" styleClass="background" cellpadding="0" cellspacing="0" width="100%">
			
			
			<h:panelGrid columns="2" styleClass="valign-middle">
				<h:form>
					<h:commandLink action="/">
						<h:graphicImage url="img/DMD.png" styleClass="icon" />
					</h:commandLink>
				</h:form>
				<h:outputLabel value="Playlists" styleClass="title"></h:outputLabel>
			</h:panelGrid>
			
			<hr/>
			
			<h:panelGrid columns="2" styleClass="valign-middle">
				<h:form>
					<h:commandLink action="home">
						<o:icon img="back"/>
					</h:commandLink>
				</h:form>
				<h:form>
					<h:commandLink action="home">
						<h:outputLabel value="Back" styleClass="text-white largeText"></h:outputLabel>
					</h:commandLink>
				</h:form>
			</h:panelGrid>
			
			<hr/>
			
			<h:form>
				<h:commandLink value="Add mp3s from directory" action="addPlaylist" styleClass="text-white" />
			</h:form>
			
			<hr/>
			
			<h:dataTable value="#{playlistsBean.playlistsList}" binding="#{playlistsBean.dataTable}" var="plist" width="100%">
				
				<h:column>
					<h:form>
						<h:commandLink action="#{playlistsBean.gotoPlaylist}" title="Goto">
							<o:icon img="folder"/>
						</h:commandLink>
					</h:form>
				</h:column>
				
				<h:column>
					<h:outputText value="#{plist.name}" styleClass="text-white largeText" /><br/>
					<h:outputText value="#{plist.source}" styleClass="text-white" />
				</h:column>
				
				<h:column>
					<h:form>
						<h:commandLink action="#{playlistsBean.deletePlaylist}" title="Remove">
							<o:icon img="remove"/>
						</h:commandLink>
					</h:form>
				</h:column>
				
			</h:dataTable>
			
			<hr/>
			
			<jsp:include page="/includes/version.jsp"></jsp:include>
			
			<hr/>
			
			
		</h:panelGrid>
	
		
	
	</f:view>
	
</body>
</html>
