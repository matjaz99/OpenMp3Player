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
<title>OpenMp3Player</title>
</head>
<body>
	
	<f:view>
		
		<h:panelGrid columns="1" styleClass="background" cellpadding="0" cellspacing="0" width="100%">
			
			<h:panelGrid columns="2" width="100%">
					
					<h:panelGrid columns="2" styleClass="valign-middle">
						<h:form>
							<h:commandLink action="home">
								<h:graphicImage url="img/DMD.png" styleClass="icon"/>
							</h:commandLink>
						</h:form>
						<h:outputText value="Song details" styleClass="title" />
					</h:panelGrid>
					
					<jsp:include page="/includes/toolbar.jsp"></jsp:include>
				
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
			
			<h:panelGrid columns="2">
				<h:outputLabel value="Title:" styleClass="text-white"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.title}" styleClass="text-white"></h:outputLabel>
				
				<h:outputLabel value="Artist:" styleClass="text-white"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.artist}" styleClass="text-white"></h:outputLabel>
				
				<h:outputLabel value="Album:" styleClass="text-white"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.album}" styleClass="text-white"></h:outputLabel>
				
				<h:outputLabel value="Genre:" styleClass="text-white"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.genre}" styleClass="text-white"></h:outputLabel>
				
				<h:outputLabel value="Year:" styleClass="text-white"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.year}" styleClass="text-white"></h:outputLabel>
				
				<h:outputLabel value="Path:" styleClass="text-white"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.path}" styleClass="text-white"></h:outputLabel>
			</h:panelGrid>
			
			<hr/>
			
			<h:dataTable value="#{songBean.tagList}" binding="#{songBean.tagDataTable}" var="tag" 
				cellpadding="0" cellspacing="0" >
				
				<h:column>
					
					<o:tag value="#{tag}"/>
					
				</h:column>
				
				<h:column>
					
					<h:form>
						<h:commandLink action="#{songBean.removeTag}" title="Remove tag">
							<o:icon img="remove-small"/>
						</h:commandLink>
					</h:form>
					
				</h:column>
				
			</h:dataTable>
						
			<hr/>
			
			<h:form>
				<h:outputText value="Select new tag: " styleClass="text-white" />
				<h:selectOneMenu onchange="submit()" value="#{songBean.selectedTag}"
					valueChangeListener="#{songBean.tagSelected}">
					<f:selectItems value="#{songBean.tagItems}"/>
				</h:selectOneMenu>
			</h:form>
			
			<hr/>
			
			<jsp:include page="/includes/version.jsp"></jsp:include>
			
			<hr/>
			
			
		</h:panelGrid>
	
		
	
	</f:view>
	
</body>
</html>
