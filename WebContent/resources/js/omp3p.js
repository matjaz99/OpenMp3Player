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

function openSettings() {
	window.location.href = "settings";
}

function openHelp() {
	window.location.href = "/OpenMp3Player/about.jsp";
}

/**
 * Replace the src attribute of img element with specified id.
 * Used on icons on mouse over events. 
 * @param id
 * @param src
 */
function onMouse(id, src) {
	$(id).attr("src", src);
}


function volumeUp() {
	
	$.ajax({  
	    type: "POST",  
	    url: "player",  
	    data: "button=volumeUp",  
	    success: function(result) {  
	    	loadPopupBox("Volume " + result);
	    }                
	  }); 
	
}

function volumeDown() {
	
	$.ajax({  
	    type: "POST",  
	    url: "player",  
	    data: "button=volumeDown",  
	    success: function(result) {  
	    	loadPopupBox("Volume " + result);
	    }                
	  }); 
	
}

/**
 * Repeat song button. Also change icon on/off.
 */
function repeat() {
	
	$.ajax({  
	    type: "POST",  
	    url: "player",  
	    data: "button=repeat",  
	    success: function(result) {  
	    	if (result == 'repeatOn') {
				$("#repeatBtn").attr("src", "img/repeatOn.png");
				$("#repeatBtn").attr("onClick", "repeat()");
				$("#repeatBtn").attr("onmouseover", "onMouse('#repeatBtn', 'img/repeatOn-shadow.png')");
				$("#repeatBtn").attr("onmouseout", "onMouse('#repeatBtn', 'img/repeatOn.png')");
				$("#repeatBtn").attr("onmousedown", "onMouse('#repeatBtn', 'img/repeatOn-pressed.png')");
				$("#repeatBtn").attr("onmouseup", "onMouse('#repeatBtn', 'img/repeatOn-shadow.png')");
				loadPopupBox("Repeat ON");
			} else {
				$("#repeatBtn").attr("src", "img/repeatOff.png");
				$("#repeatBtn").attr("onClick", "repeat()");
				$("#repeatBtn").attr("onmouseover", "onMouse('#repeatBtn', 'img/repeatOff-shadow.png')");
				$("#repeatBtn").attr("onmouseout", "onMouse('#repeatBtn', 'img/repeatOff.png')");
				$("#repeatBtn").attr("onmousedown", "onMouse('#repeatBtn', 'img/repeatOff-pressed.png')");
				$("#repeatBtn").attr("onmouseup", "onMouse('#repeatBtn', 'img/repeatOff-shadow.png')");
				loadPopupBox("Repeat OFF");
			}
	    }                
	  }); 
}


/**
 * Show and hide popup box with selected message
 * @param txt
 */
function loadPopupBox(txt) {
	$("#popup_text").html(txt);
    $('#popup_box').fadeIn("slow");
    $('#popup_box').delay(500);
    $('#popup_box').fadeOut("slow");
}

