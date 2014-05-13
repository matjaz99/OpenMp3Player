<%@page import="si.matjazcerkvenik.openmp3player.backend.Playlist"%>
<%@page import="si.matjazcerkvenik.openmp3player.backend.Mp3File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*,si.matjazcerkvenik.openmp3player.backend.Mng" %>
<jsp:useBean id="mng" scope="application" class="si.matjazcerkvenik.openmp3player.backend.Mng"></jsp:useBean>
<div>
<div id="currentlyPlaying">Currently playing: <%=mng.getCurrentlyPlaying()%></div>
</div>
<table border="1" cellpadding="2">
  <tr>
    <th></th>
    <th>TITLE</th>

  </tr>
  <%
  	List<Mp3File> files = mng.getActivePlaylist().getMp3Files();
      	if (files != null) {
      		for (int i = 0; i < files.size(); i++) {
      			Mp3File f = files.get(i);
  %>
		<tr>
			
			<td>
				<form name="play" action="javascript:onButton('play',<%=i%>)" method="post">
					<input type="hidden" name="index" value="<%=i%>"/> 
					<input type="hidden" name="button" value="play"/>
					<input type="submit" value="&gt;"/>
				</form>
			</td>
			<td><%=f.getTitle()%></td>
		</tr>
		<%
				} // for
			} // if
			
		%>
</table>
<div>Size: <%=mng.getNumberOfMp3s()%></div>