$(document).ready(function () {

    $("#playerForm").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });
    
    $("[id^=deletePlaylistBtn-]").on("click", function () {

        //stop submit the form, we will post it manually.
        //event.preventDefault();

        //fire_ajax_submit();
    	
    	//bootbox.confirm("This is the default confirm!", function(result){ console.log('This was logged in the callback: ' + result); });
    	
    	var id = $(this).attr('id').substring(18);
    	
    	bootbox.confirm({
    	    title: "Delete playlist?",
    	    message: "Do you really want to delete a playlist?",
    	    buttons: {
    	        cancel: {
    	            label: '<i class="fa fa-times"></i> Cancel'
    	        },
    	        confirm: {
    	            label: '<i class="fa fa-check"></i> Confirm'
    	        }
    	    },
    	    callback: function (result) {
    	        console.log('This was logged in the callback: ' + result);
    	        if (result == true) {
					alert(id);
					deletePlaylistSubmit(id);
				}
    	    }
    	});

    });

});

function deletePlaylistSubmit(id) {
	$.ajax({
        type: "DELETE",
        url: "/OpenMp3Player/playlist/delete/submit/" + id,
        data: "id=" + id,
        success: function (data) {
            console.log("SUCCESS : ", data);
            location.reload();
        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);

        }
    });
}


function fire_ajax_submit() {

	var btn = $(document.activeElement).val();
    var search = {}
    search["action"] = btn;

    $("#" + btn).prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/OpenMp3Player/player",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 60000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#" + btn).prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#" + btn).prop("disabled", false);

        }
    });

}
