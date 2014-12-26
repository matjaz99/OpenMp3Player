<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/mstyles.css"/>
<title>About</title>
</head>
<body>

	<f:view>
		
		<h:panelGrid columns="1" styleClass="background" cellpadding="0" cellspacing="0" width="100%">
			
			<f:facet name="header">
				<h:form>
					<h:commandLink action="/">
						<h:outputText value="About OpenMp3Player" styleClass="title" />
					</h:commandLink>
				</h:form>
			</f:facet>
			
			<hr/>
			
			<h:form>
				<h:commandLink value="Back" action="home" styleClass="text-white largeText" />
			</h:form>
			
			<hr/>
			
			<jsp:include page="/includes/version.jsp"></jsp:include>
			
			<hr/>
			
			<h:panelGrid style="background-color: white;">
				<jsp:include page="/includes/about-data.jsp"></jsp:include>
			</h:panelGrid>
			
			
		</h:panelGrid>

	</f:view>
	
</body>
</html>