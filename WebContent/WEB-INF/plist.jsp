<%@page import="si.matjazcerkvenik.openmp3player.player.Playlist"%>
<%@page import="si.matjazcerkvenik.openmp3player.player.Mp3File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*,si.matjazcerkvenik.openmp3player.backend.Mng" %>
<jsp:useBean id="mng" scope="application" class="si.matjazcerkvenik.openmp3player.backend.Mng"></jsp:useBean>

<section>
<table>
  <%
  	List<Mp3File> files = mng.getPlistMng().getShowPlaylist().getMp3Files();
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
<%-- 				<button onclick="onButton('play',<%=i%>)">&gt;</button> --%>
				<img id="playBtn<%=i%>" src="img/play.png" onclick="onButton('play',<%=i%>, 'Play')" 
						onmouseover="onMouse('#playBtn<%=i%>', 'img/play-shadow.png')" 
						onmouseout="onMouse('#playBtn<%=i%>', 'img/play.png')" 
						onmousedown="onMouse('#playBtn<%=i%>', 'img/play-pressed.png')" 
						onmouseup="onMouse('#playBtn<%=i%>', 'img/play-shadow.png')">
			</td>
			<td class="<%=oddEven%>">
				<div><%=f.getTitle()%></div>
				<div class="small">Path: <%=f.getPath()%></div>
			</td>
			<td class="<%=oddEven%>">
				<%
					if (!mng.getPlistMng().getShowPlaylist().getSource().equals("queue")) { %>
						<img id="qBtn<%=i%>" src="img/toqueue.png" onclick="onButton('queue',<%=i%>, 'Put to queue')" 
								onmouseover="onMouse('#qBtn<%=i%>', 'img/toqueue-shadow.png')" 
								onmouseout="onMouse('#qBtn<%=i%>', 'img/toqueue.png')" 
								onmousedown="onMouse('#qBtn<%=i%>', 'img/toqueue-pressed.png')" 
								onmouseup="onMouse('#qBtn<%=i%>', 'img/toqueue-shadow.png')">
<%-- 						<button onclick="onButton('queue',<%=i%>)">Q</button> --%>
				<%	} // end if
				%>
				
			</td>
		</tr>
		<%
				} // end for
			} // end if
			
		%>
</table>

<hr>
<div>Size: <%=mng.getPlistMng().getShowPlaylist().getMp3Files().size()%></div>
<hr>
<div>Version: <%=Mng.version%></div>
</section>