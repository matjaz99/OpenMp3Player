<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
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
		
		<h:panelGrid columns="1" styleClass="background">
			
			<h:outputLabel value="Song details" styleClass="title"></h:outputLabel>
			
			<h:form>
				<h:commandLink value="Back" action="home" />
			</h:form>
			
			
			<hr/>
			
			<h:panelGrid columns="2">
				<h:outputLabel value="Title:"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.title}"></h:outputLabel>
				
				<h:outputLabel value="Artist:"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.artist}"></h:outputLabel>
				
				<h:outputLabel value="Album:"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.album}"></h:outputLabel>
				
				<h:outputLabel value="Genre:"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.genre}"></h:outputLabel>
				
				<h:outputLabel value="Year:"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.year}"></h:outputLabel>
				
				<h:outputLabel value="Size:"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.size}"></h:outputLabel>
				
				<h:outputLabel value="Path:"></h:outputLabel>
				<h:outputLabel value="#{songBean.mp3File.path}"></h:outputLabel>
			</h:panelGrid>
			
			<hr/>
			
			<h:outputText value="#{songBean.tagsAsString}"/>
			
			<hr/>
			
			<h:form>
				<h:outputText value="Add tag: " />
				<h:selectOneMenu onchange="submit()" value="#{songBean.selectedTag}"
					valueChangeListener="#{songBean.tagSelected}">
					<f:selectItems value="#{songBean.tags}"/>
				</h:selectOneMenu>
			</h:form>
			
			<hr/>
			
			<h:form>
				<h:commandLink value="New tag" action="addTag" />
			</h:form>
			
			<hr/>
			
			<h:outputLabel value="Version: #{settingsBean.version}"></h:outputLabel>
			
			<hr/>
			
			
		</h:panelGrid>
	
		
	
	</f:view>
	
</body>
</html>
