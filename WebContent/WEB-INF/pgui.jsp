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
	$("#currentlyPlaying").html("Currently playing: " + result);
	if (result == 'null') {
		$("#currentlyPlaying").html("Stopped");
		$("#playBtn").html("Play");
		$("#playBtn").attr("onClick", "onButton('play')");
	} else {
		$("#currentlyPlaying").html("Currently playing: " + result);
		$("#playBtn").html("Stop");
		$("#playBtn").attr("onClick", "onButton('stop')");
	}
}
$(document).ready(function() {
    $('#dropdown').change(function() {
    	var s = $("#dropdown option:selected").val();
    	$.ajax({  
    	    type: "POST",  
    	    url: "player",  
    	    data: "button=dropdownmenu&selDir=" + s,  
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


<button onclick="onButton('refresh')">Refresh</button>

</div>

<div style="clear: both;"></div>

<hr>

<div>
<%
String currPlay = "Stopped";
if (!mng.getCurrentlyPlaying().equals("null")) {
	currPlay = "Currently playing: " + mng.getCurrentlyPlaying();
}
%>
<div id="currentlyPlaying"><%=currPlay%></div>
</div>

</div>
