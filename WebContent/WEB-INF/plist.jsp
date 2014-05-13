<%@page import="si.matjazcerkvenik.openmp3player.backend.Playlist"%>
<%@page import="si.matjazcerkvenik.openmp3player.backend.Mp3File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*,si.matjazcerkvenik.openmp3player.backend.Mng" %>
<jsp:useBean id="mng" scope="application" class="si.matjazcerkvenik.openmp3player.backend.Mng"></jsp:useBean>

<div class="border">
<table>
  <%
  	List<Mp3File> files = mng.getActivePlaylist().getMp3Files();
      	if (files != null) {
      		String oddEven = "even";
      		for (int i = 0; i < files.size(); i++) {
      			Mp3File f = files.get(i);
      			if (i % 2 == 0) { 
      				oddEven = "odd"; 
      			} else {
      				oddEven = "even";
      			}
  %>
		<tr>
			
			<td class="<%=oddEven%>">
				<button onclick="onButton('play',<%=i%>)">&gt;</button>
			</td>
			<td class="<%=oddEven%>"><%=f.getTitle()%></td>
		</tr>
		<%
				} // for
			} // if
			
		%>
</table>

<hr>
<div>Size: <%=mng.getNumberOfMp3s()%></div>
</div>