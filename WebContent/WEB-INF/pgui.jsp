<%@page import="si.matjazcerkvenik.openmp3player.backend.Playlist"%>
<%@page session="true" import="java.util.*,si.matjazcerkvenik.openmp3player.backend.Mng" %>
<jsp:useBean id="mng" scope="application" class="si.matjazcerkvenik.openmp3player.backend.Mng"></jsp:useBean>

<script type="text/javascript">
function onButton(button, index, descr) {  

	  if (descr != null) {
		loadPopupBox(descr);
	  }

	  var i = "";
	  if (index != null) {
		i = "&index=" + index;
	  }
	  $.ajax({  
	    type: "POST",  
	    url: "player",  
	    data: "button=" + button + i,  
	    success: function(result) {  
	    	togglePlayButton(result);
	    }                
	  });  
	}

</script>


<div class="border">

<div>
	<h2>OpenMp3Player</h2>
</div>

<hr/>

<div id="guiPanel" style="float: left;">


<img id="rewBtn" src="img/rewind.png" onclick="onButton('prev', null, 'Previous')" 
		onmouseover="onMouse('#rewBtn', 'img/rewind-shadow.png')" 
		onmouseout="onMouse('#rewBtn', 'img/rewind.png')" 
		onmousedown="onMouse('#rewBtn', 'img/rewind-pressed.png')" 
		onmouseup="onMouse('#rewBtn', 'img/rewind-shadow.png')">


<%
String btn = "play";
if (mng.isPlaying()) {
	btn = "stop";
}
%>
<img id="playBtn" src="img/<%=btn%>.png" onclick="onButton('<%=btn%>', null, '<%=btn.substring(0, 1).toUpperCase()+btn.substring(1)%>')" 
		onmouseover="onMouse('#playBtn', 'img/<%=btn%>-shadow.png')" 
		onmouseout="onMouse('#playBtn', 'img/<%=btn%>.png')" 
		onmousedown="onMouse('#playBtn', 'img/<%=btn%>-pressed.png')" 
		onmouseup="onMouse('#playBtn', 'img/<%=btn%>-shadow.png')">


<img id="fwdBtn" src="img/fast_forward.png" onclick="onButton('next', null, 'Next')" 
		onmouseover="onMouse('#fwdBtn', 'img/fast_forward-shadow.png')" 
		onmouseout="onMouse('#fwdBtn', 'img/fast_forward.png')" 
		onmousedown="onMouse('#fwdBtn', 'img/fast_forward-pressed.png')" 
		onmouseup="onMouse('#fwdBtn', 'img/fast_forward-shadow.png')">


<select id="dropdown" >
	<%
		String selected = "selected";
		String qselected = "selected";
		List<Playlist> list = mng.getPlistMng().getPlaylists();
			for (int i = 0; i < list.size(); i++) {
				String pName = list.get(i).getName();
				selected = "";
				if (list.get(i).getName().equals(mng.getPlistMng().getShowPlaylist().getName())) {
					selected = "selected";
					qselected = "";
				}
	%>
			
	<option <%=selected%> value="<%=list.get(i).getName()%>" title="<%=list.get(i).getSource()%>">
	<%=pName%>
	</option>
			<%
							} // end for
						%>
	<option <%=qselected%> value="Queue" title="queue">Queue</option>
</select>






</div>


<div id="ddButton" style="float: right;">
<img id="ddPanelBtn" src="img/showMenu.png" 
		onmouseover="onMouse('#ddPanelBtn', 'img/showMenu-shadow.png')" 
		onmouseout="onMouse('#ddPanelBtn', 'img/showMenu.png')" 
		onmousedown="onMouse('#ddPanelBtn', 'img/showMenu-pressed.png')" 
		onmouseup="onMouse('#ddPanelBtn', 'img/showMenu-shadow.png')">
</div>


<div style="clear: both;"></div>


<!-- ddPanel is below the guiPanel (and ddButton) -->
<div id="ddPanel">
<hr>
<img id="editorBtn" src="img/editor.png" onclick="openPlaylistEditor()" 
		onmouseover="onMouse('#editorBtn', 'img/editor-shadow.png')" 
		onmouseout="onMouse('#editorBtn', 'img/editor.png')" 
		onmousedown="onMouse('#editorBtn', 'img/editor-pressed.png')" 
		onmouseup="onMouse('#editorBtn', 'img/editor-shadow.png')">
<img id="helpBtn" src="img/help.png" onclick="openHelp()"
		onmouseover="onMouse('#helpBtn', 'img/help-shadow.png')" 
		onmouseout="onMouse('#helpBtn', 'img/help.png')" 
		onmousedown="onMouse('#helpBtn', 'img/help-pressed.png')" 
		onmouseup="onMouse('#helpBtn', 'img/help-shadow.png')">
</div>


<hr>



<div>

<div style="float: left;">
<img id="refreshBtn" src="img/refresh.png" onclick="onButton('refresh', null, 'Refresh')" 
		onmouseover="onMouse('#refreshBtn', 'img/refresh-shadow.png')" 
		onmouseout="onMouse('#refreshBtn', 'img/refresh.png')" 
		onmousedown="onMouse('#refreshBtn', 'img/refresh-pressed.png')" 
		onmouseup="onMouse('#refreshBtn', 'img/refresh-shadow.png')">
<!-- <button onclick="onButton('refresh')">Refresh</button> -->
</div>
<%
String currPlay = "Stopped";
if (!mng.getCurrentlyPlaying().equals("null")) {
	currPlay = "Playing: " + mng.getCurrentlyPlaying();
}
%>
<div id="currentlyPlaying" style="float: left;"><%=currPlay%></div>

</div>
<div style="clear: both;"></div>



</div>
