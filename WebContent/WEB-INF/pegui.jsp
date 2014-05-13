<%@page import="si.matjazcerkvenik.openmp3player.backend.Playlist"%>
<%@page session="true" import="java.util.*,si.matjazcerkvenik.openmp3player.backend.Mng" %>
<jsp:useBean id="mng" scope="application" class="si.matjazcerkvenik.openmp3player.backend.Mng"></jsp:useBean>

<script type="text/javascript">
function onButton(button, name) {  

  var x = "";
  if (button == "add") {
	  x = "&name=" + $("#pName").val() + "&source=" + $("#pSource").val();
  } else if (button == "remove") {
	  x = "&name=" + name;
  } else if (button == "savequeue") {
	  x = "&name=" + prompt("Save playlist as");
  }
  $.ajax({  
    type: "POST",  
    url: "playlistEditor",  
    data: "button=" + button + x,  
    success: function(result) {  
    	location.reload();
    }                
  });  
}

function openPlayer() {
	window.location.href = "/OpenMp3Player";
}

</script>


<div class="border">

<div>
	<h2>Playlist Editor</h2>
</div>

<hr/>

<button onclick="openPlayer()">Back to player</button>

<div>
<form name="addForm" action="javascript:onButton('add')" method="post">
Name: <input id="pName" type="text" name="name"/>
Source: <input id="pSource" type="text" name="source"/>
<input type="hidden" name="button" value="add"/> 
<input type="submit" value="Add">
</form>
</div>

<hr>



<div style="float: left;">

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
	String srcType = "DIR: ";
	if (pSource.endsWith(".xml")) {
		srcType = "XML: ";
	}
%>
	
	<tr>
		<td class="<%=oddEven%>">
			<div><%=srcType %><%=pName %></div>
<%-- 			<div><%=pSource %></div> --%>
		</td>
		<td class="<%=oddEven%>">
			<button onclick="onButton('remove', '<%=pName %>')">Remove</button>
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
			<div><%=qName %></div>
		</td>
		<td class="<%=oddEven%>">
			<button onclick="onButton('savequeue')">Save as...</button>
			<button onclick="onButton('emptyqueue')">Empty</button>
		</td>
</tr>

</table>

</div>
<div style="clear: both;"></div>


</div>
