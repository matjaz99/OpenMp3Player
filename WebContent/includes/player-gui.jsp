<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


				
				<h:panelGrid columns="3">
					
					<h:form>
					<h:commandLink id="rewBtn" action="#">
						<h:graphicImage url="img/rewind.png" styleClass="icon" alt="Previous" />
					</h:commandLink>
					</h:form>
					
					<h:form>
					<h:commandLink id="playBtn" action="#">
						<h:graphicImage url="img/play.png" styleClass="icon" alt="Play" />
					</h:commandLink>
					</h:form>
					
					<h:form>
					<h:commandLink id="fwdBtn" action="#">
						<h:graphicImage url="img/fast_forward.png" styleClass="icon" alt="Next" />
					</h:commandLink>
					</h:form>
					
				</h:panelGrid>
				
			