//------------------------------ DELETE --------------------
//--------------------------------------------------------
function deleteBloodAPI(id, userID) {
	var form = new FormData();
	form.append("id", id);
	form.append("delete_by", userID);

	return $.ajax({
		"url": "/api/blood/delete",
		"method": "PUT",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		"async": false
	});
}

function bukaPopupDelete(code, description, id) {
	console.log("Delete Blood Kepencet!")
	console.log(code + " , " + description + " , " + id);

	//Ganti Title
	$(".modal-title").html("Delete");

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<div class="row p-2">
			<p>Are you sure you want to delete blood group of ${code}?</p>
		</div>	
		<div class="p-2" style="text-align: right;">
			<button id="input-cancel" class="btn btn-light">Cancel</button>
			<button id="input-delete" class="btn btn-danger">Delete</button>
		</div>
		`
	);

	$("#input-cancel").click(function() {
		$(".modal").modal("hide");
	});

	$("#input-delete").click(function() {
		//Ambil Response Text
		var deleteBy = userID;
		console.log(deleteBy);
		
		var response = deleteBloodAPI(id, deleteBy).responseText;
		console.log(response);

		//Convert ke Json
		var responseJson = $.parseJSON(response);

		if (responseJson.code == 200) {
			alert(responseJson.message);
			$(".modal").modal("hide");
			refreshList();
		} else {
			alert(responseJson.message);
		}



	});

	//. class
	//# id
	$(".modal").modal("show");


}
//---------------------------------------------------------------------
