/**
 * Toggle between Play/Stop button (pgui.jsp)
 */
function togglePlayButton(result) {
		if (result.length == 0) {
			return;
		}
		if (result == 'null') {
			$("#currentlyPlaying").html("Stopped");
			$("#playBtn").attr("src", "img/play.png");
			$("#playBtn").attr("onClick", "onButton('play', null, 'Play')");
			$("#playBtn").attr("onmouseover", "onMouse('#playBtn', 'img/play-shadow.png')");
			$("#playBtn").attr("onmouseout", "onMouse('#playBtn', 'img/play.png')");
			$("#playBtn").attr("onmousedown", "onMouse('#playBtn', 'img/play-pressed.png')");
			$("#playBtn").attr("onmouseup", "onMouse('#playBtn', 'img/play-shadow.png')");
		} else {
			$("#currentlyPlaying").html("Playing: " + result);
			$("#playBtn").attr("src", "img/stop.png");
			$("#playBtn").attr("onClick", "onButton('stop', null, 'Stop')");
			$("#playBtn").attr("onmouseover", "onMouse('#playBtn', 'img/stop-shadow.png')");
			$("#playBtn").attr("onmouseout", "onMouse('#playBtn', 'img/stop.png')");
			$("#playBtn").attr("onmousedown", "onMouse('#playBtn', 'img/stop-pressed.png')");
			$("#playBtn").attr("onmouseup", "onMouse('#playBtn', 'img/stop-shadow.png')");
		}
	}

/**
 * This function waits to select a playlist from dropdown menu (pgui.jsp)
 */
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

/**
 * This function waits to toggle a dropdown panel when ddButton is pressed (pgui.jsp)
 */
$(document).ready(function(){
	  $("#ddButton").click(function(){
	    $("#ddPanel").slideToggle("slow");
	  });
	});



function openPlaylistEditor() {
	window.location.href = "playlistEditor";
}

function openPlayer() {
	window.location.href = "/OpenMp3Player";
}

function openHelp() {
	window.location.href = "/OpenMp3Player/about.jsp";
}

function onMouse(id, src) {
	$(id).attr("src", src);
}


function loadPopupBox(txt) {
	$("#popup_text").html(txt);
    $('#popup_box').fadeIn("slow");
    $('#popup_box').fadeOut("slow");
}
