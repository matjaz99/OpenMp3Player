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
<title>OpenMp3Player settings</title>
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
						<h:outputText value="Settings" styleClass="title" />
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
			
			<h:outputText value="Connect to OpenMp3Player from remote device:" styleClass="text-white" />
			<h:outputLink value="http://#{settingsBean.localIp}:8080/OpenMp3Player/" >
				<h:outputText value="http://#{settingsBean.localIp}:8080/OpenMp3Player/" styleClass="text-white" />
			</h:outputLink>
			
			<hr/>
			
			<h:outputText value="Volume level: #{settingsBean.currentSoundLevel}" styleClass="text-white" />
			
			<hr/>
			
			<h:outputText value="CLI: #{settingsBean.telnetEnabled}" styleClass="text-white" />
			<h:outputText value="$ telnet #{settingsBean.localIp} #{settingsBean.telnetPort}" styleClass="text-white" />
			<h:outputText value="> help" styleClass="text-white" />
			
			<hr/>
			
			<h:panelGrid columns="2" styleClass="background" cellpadding="0" cellspacing="0" width="100%">
				<h:form>
				<h:outputText value="Create new tag" styleClass="text-white" />
				<h:panelGrid columns="2">
					
						<h:outputLabel value="Name: " styleClass="text-white"></h:outputLabel>
						<h:inputText value="#{addTagBean.name}" required="true">
							<f:validator validatorId="tagValidator"/>
							<f:validateLength minimum="1"/>
						</h:inputText>
						<h:outputLabel value="Color: " styleClass="text-white"></h:outputLabel>
						<h:selectOneMenu value="#{addTagBean.color}" >
							<f:selectItems value="#{addTagBean.allColors}"/>
						</h:selectOneMenu>
						<h:commandButton action="#{addTagBean.addNew}" value="Add"></h:commandButton>
						
					
				</h:panelGrid>
				<h:messages layout="table" showDetail="true" showSummary="false" styleClass="error"></h:messages>
				</h:form>
				
				<h:panelGroup>
					<h:outputLabel value="Available tags" styleClass="text-white"></h:outputLabel>
					<h:dataTable value="#{addTagBean.allTags}" binding="#{addTagBean.availableTagsTable}" var="tag">
				
						<h:column>
							<o:tag value="#{tag}"/>
						</h:column>
						
						<h:column>
							<h:form>
								<h:commandLink action="#{addTagBean.deleteTag}" title="Delete tag">
									<o:icon img="remove-small"/>
								</h:commandLink>
							</h:form>
						</h:column>
						
					</h:dataTable>
					
				</h:panelGroup>
				
			</h:panelGrid>
			
			<hr/>
			
			<jsp:include page="/includes/version.jsp"></jsp:include>
			
			<hr/>
			
		</h:panelGrid>
		
	</f:view>
	
</body>
</html>
