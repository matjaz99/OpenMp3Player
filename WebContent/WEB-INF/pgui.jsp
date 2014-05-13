<%@page import="si.matjazcerkvenik.openmp3player.backend.Playlist"%>
<%@page session="true" import="java.util.*,si.matjazcerkvenik.openmp3player.backend.Mng" %>
<jsp:useBean id="mng" scope="application" class="si.matjazcerkvenik.openmp3player.backend.Mng"></jsp:useBean>
<div style="float: left;">
<form name="prev" action="player" method="post" style="display:inline;">
	<input type="hidden" name="prev" /> 
	<input type="hidden" name="button" value="prev"/>
	<input type="submit" value="Prev"/>
</form>
<form name="play" action="player" method="post" style="display:inline;">
	<input type="hidden" name="play" /> 
	<input type="hidden" name="button" value="play"/>
	<input type="submit" value="Play"/>
</form>
<form name="next" action="player" method="post" style="display:inline;">
	<input type="hidden" name="next" /> 
	<input type="hidden" name="button" value="next"/>
	<input type="submit" value="Next"/>
</form>
<form name="stop" action="player" method="post" style="display:inline;">
	<input type="hidden" name="stop" /> 
	<input type="hidden" name="button" value="stop"/>
	<input type="submit" value="Stop"/>
</form>
<form name="selectDirForm" action="player" method="post" style="display:inline;">
<select name="selDir">
	<%
		List<Playlist> list = mng.getPlaylists();
			for (int i = 0; i < list.size(); i++) {
				String pName = list.get(i).getName();
				String selected = "";
				if (list.get(i).getName().equals(mng.getActivePlaylist().getName())) selected = "selected";
	%>
			
			<option <%=selected%> value="<%=list.get(i).getName()%>" title="<%=list.get(i).getSource()%>">
			<%=pName%>
			</option>
			
			<%
							}
						%>
</select>

<!-- 	<input type="hidden" name="dir" />  -->
	<input type="hidden" name="button" value="selectDir"/>
	<input type="submit" value="OK"/>
</form>
</div>
<div style="clear: both;"></div>