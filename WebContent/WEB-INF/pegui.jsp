<%@page import="si.matjazcerkvenik.openmp3player.backend.Playlist"%>
<%@page session="true" import="java.util.*,si.matjazcerkvenik.openmp3player.backend.Mng" %>
<jsp:useBean id="mng" scope="application" class="si.matjazcerkvenik.openmp3player.backend.Mng"></jsp:useBean>

<script type="text/javascript">
function onButton(button, name, descr) {  

  var x = "";
  if (descr != null) {
	  loadPopupBox(descr);
  }
  if (button == "add") {
	  x = "&name=" + $("#pName").val() + "&source=" + $("#pSource").val();
  } else if (button == "remove" || button == "goto") {
	  x = "&name=" + name;
  } else if (button == "savequeue") {
	  var n = prompt("Playlist name");
	  if (n == null) {
		return;
	  }
	  if (n.length == 0) {
			alert("Missing name of playlist");
			return;
		  }
	  x = "&name=" + n;
  }
  $.ajax({  
    type: "POST",  
    url: "playlistEditor",  
    data: "button=" + button + x,  
    success: function(result) {  
    	if (button == "goto") {
    		  openPlayer();
    	} else {
    		  location.reload();
    	}
    }                
  });  
}



</script>

<div>

<div class="border">

<div>
	<h2>Playlist Editor</h2>
</div>

<hr/>

<button onclick="openPlayer()">Back to player</button>

<div class="small">
Add new directory
</div>

<div>
<form name="addForm" action="javascript:onButton('add', null, 'Playlist added')" method="post">
Playlist name: <input id="pName" type="text" name="name"/>
Directory: <input id="pSource" type="text" name="source"/>
<input type="hidden" name="button" value="add"/> 
<input type="submit" value="Add">
</form>
</div>

</div>

<hr>



<div class="border">

<table>

<%

List<Playlist> list = mng.getPlistMng().getPlaylists();
String oddEven = "even";
for (int i = 0; i < list.size(); i++) {
	String pName = list.get(i).getName();
	String pSource = list.get(i).getSource();
	if (i % 2 == 0) { 
    	oddEven = "odd"; 
    } else {
    	oddEven = "even";
    }
	String icon = "";
	if (pSource.endsWith(".xml")) {
		// only name of file, without path and suffix
		icon = "xml-doc";
	} else {
		icon = "folder";
	}
%>
	
	<tr>
		<td class="<%=oddEven%>">
			<img id="gotoBtn<%=i %>" src="img/<%=icon%>.png" onclick="onButton('goto', '<%=pName %>')" 
						onmouseover="onMouse('#gotoBtn<%=i %>', 'img/<%=icon%>-shadow.png')" 
						onmouseout="onMouse('#gotoBtn<%=i %>', 'img/<%=icon%>.png')" 
						onmousedown="onMouse('#gotoBtn<%=i %>', 'img/<%=icon%>-pressed.png')" 
						onmouseup="onMouse('#gotoBtn<%=i %>', 'img/<%=icon%>-shadow.png')">
		</td>
		<td class="<%=oddEven%>">
<%-- 			<div><%=srcType %><%=pName %></div> --%>
			<div><%=pName %></div>
			<div class="small">Source: <%=pSource %></div>
		</td>
		<td class="<%=oddEven%>">
			<img id="removeBtn<%=i %>" src="img/remove.png" onclick="onButton('remove', '<%=pName %>', 'Deleted')" 
						onmouseover="onMouse('#removeBtn<%=i %>', 'img/remove-shadow.png')" 
						onmouseout="onMouse('#removeBtn<%=i %>', 'img/remove.png')" 
						onmousedown="onMouse('#removeBtn<%=i %>', 'img/remove-pressed.png')" 
						onmouseup="onMouse('#removeBtn<%=i %>', 'img/remove-shadow.png')">
		</td>
	</tr>

<%
} // end for

if (oddEven.equals("even")) { 
	oddEven = "odd"; 
}  else {
	oddEven = "even";
}

Playlist q = mng.getPlistMng().getQueue();
String qName = q.getName();
String qSource = q.getSource();
	
%>

<tr>
	<td class="<%=oddEven%>">
		<img id="queueBtn" src="img/queue.png" onclick="onButton('goto', 'Queue')" 
						onmouseover="onMouse('#queueBtn', 'img/queue-shadow.png')" 
						onmouseout="onMouse('#queueBtn', 'img/queue.png')" 
						onmousedown="onMouse('#queueBtn', 'img/queue-pressed.png')" 
						onmouseup="onMouse('#queueBtn', 'img/queue-shadow.png')">
	</td>
	<td class="<%=oddEven%>">
			<div><%=qName %></div>
			<div class="small">Source: Virtual playlist</div>
		</td>
		<td class="<%=oddEven%>">
<!-- 			<button onclick="onButton('savequeue')">Save as...</button> -->
			<img id="saveBtn" src="img/save.png" onclick="onButton('savequeue', null, 'Saved')" 
						onmouseover="onMouse('#saveBtn', 'img/save-shadow.png')" 
						onmouseout="onMouse('#saveBtn', 'img/save.png')" 
						onmousedown="onMouse('#saveBtn', 'img/save-pressed.png')" 
						onmouseup="onMouse('#saveBtn', 'img/save-shadow.png')">
			<img id="emptyBtn" src="img/empty.png" onclick="onButton('emptyqueue', null, 'Empty')" 
						onmouseover="onMouse('#emptyBtn', 'img/empty-shadow.png')" 
						onmouseout="onMouse('#emptyBtn', 'img/empty.png')" 
						onmousedown="onMouse('#emptyBtn', 'img/empty-pressed.png')" 
						onmouseup="onMouse('#emptyBtn', 'img/empty-shadow.png')">
<!-- 			<button onclick="onButton('emptyqueue')">Empty</button> -->
		</td>
</tr>

</table>

</div>
<div style="clear: both;"></div>


</div>
