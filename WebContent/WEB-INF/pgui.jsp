<%@page import="si.matjazcerkvenik.openmp3player.backend.Playlist"%>
<%@page session="true" import="java.util.*,si.matjazcerkvenik.openmp3player.backend.Mng" %>
<jsp:useBean id="mng" scope="application" class="si.matjazcerkvenik.openmp3player.backend.Mng"></jsp:useBean>

<script type="text/javascript">
function onButton(button, index) {  

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
function togglePlayButton(result) {
	if (result.length == 0) {
		return;
	}
	if (result == 'null') {
		$("#currentlyPlaying").html("Stopped");
		$("#playBtn").html("Play");
		$("#playBtn").attr("onClick", "onButton('play')");
	} else {
		$("#currentlyPlaying").html("Playing: " + result);
		$("#playBtn").html("Stop");
		$("#playBtn").attr("onClick", "onButton('stop')");
	}
}
function openPlaylistEditor() {
	window.location.href = "playlistEditor";
}
$(document).ready(function() {
	// waiting to select from dropdown menu
    $('#dropdown').change(function() {
    	var s = $("#dropdown option:selected").val();
    	$.ajax({  
    	    type: "POST",  
    	    url: "player",  
    	    data: "button=dropdownmenu&selPlist=" + s,  
    	    success: function(result){  
    	    	location.reload();
    	    }       
    	  });
  });  
});
</script>


<div class="border">

<div>
	<h2>OpenMp3Player</h2>
</div>

<hr/>

<div style="float: left;">

<button onclick="onButton('prev')">Prev</button>
<%
String btn = "play";
String btnVal = "Play";
if (mng.isPlaying()) {
	btn = "stop";
	btnVal = "Stop";
}
%>
<button id="playBtn" onclick="onButton('<%=btn%>')"><%=btnVal%></button>
<button onclick="onButton('next')">Next</button>


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


<button onclick="onButton('refresh')">Refresh</button>


<button onclick="openPlaylistEditor()">Editor</button>

</div>

<div style="clear: both;"></div>

<hr>

<div>
<%
String currPlay = "Stopped";
if (!mng.getCurrentlyPlaying().equals("null")) {
	currPlay = "Playing: " + mng.getCurrentlyPlaying();
}
%>
<div id="currentlyPlaying"><%=currPlay%></div>
</div>

</div>
