<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="o" uri="/WEB-INF/tld/omp3p-tags.tld"%>

<f:subview id="toolbar">

	<h:panelGroup styleClass="align-right">
						<h:form>
							<h:commandLink action="playlists">
								<o:icon img="playlists"/>
							</h:commandLink>
							<h:commandLink action="#{playerBean.gotoQueue}">
								<o:icon img="queue"/>
							</h:commandLink>
							<h:graphicImage url="img/separator.png" styleClass="icon" />
							<h:commandLink action="#{playerBean.volumeDown}">
								<o:icon img="volumeDn"/>
							</h:commandLink>
							<h:commandLink action="#{playerBean.volumeUp}">
								<o:icon img="volumeUp"/>
							</h:commandLink>
							<h:graphicImage url="img/separator.png" styleClass="icon" />
							<h:commandLink action="settings">
								<o:icon img="settings"/>
							</h:commandLink>
							<h:commandLink action="about">
								<o:icon img="help"/>
							</h:commandLink>
						</h:form>
					</h:panelGroup>


</f:subview>
