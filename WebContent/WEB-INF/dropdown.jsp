<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="playlistsDropdown" class="si.matjazcerkvenik.openmp3player.backend.Mng" scope="page"/>

<select>
<c:forEach var="aa" items="${ adsf }">
<option value=""></option>
</c:forEach>
</select>

<!-- <select id="drp_player"> -->
<%-- 	<c:forEach var="item" items="${allPlayerInfo.items}"> --%>
<%-- 		<option value="${item}">${item}</option> --%>
<%-- 	</c:forEach> --%>
<!-- </select> -->