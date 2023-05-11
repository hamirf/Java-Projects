//-----------------EDIT CATEGORY ------------------------

function editBloodAPI(id, code, description, userID) {
	var form = new FormData();
	form.append("id", id);
	form.append("code", code);
	form.append("description", description);
	form.append("modify_by", userID);

	return $.ajax({
		"url": "/api/blood/update",
		"method": "PUT",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		"async": false
	});
}

function bukaPopupEdit(code, description, id) {
	console.log("Edit Blood Kepencet!");
	console.log(code + " , " + description + " , " + id);


	//Ganti Title
	$(".modal-title").html("Change Blood");

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<table class="table table-borderless">
			<tr>
				<td>Code*</td>
				<td>
					<input type=text id="input-code" class="form-control" value="${code}" required>
				</td>
			</tr>
			<tr>
				<td>Description</td>
				<td>
					<input type=text id="input-description" class="form-control" value="${description}">
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<button id="input-cancel" class="btn btn-light">Cancel</button>
					<button id="input-save" class="btn btn-warning">Save Change</button>
				</td>
			</tr>
		</table>
		`
	);

	$("#input-cancel").click(function() {
		$(".modal").modal("hide");
	});

	$("#input-save").click(function() {
		//Ambil Code
		var code = $("#input-code").val();
		console.log(code);

		//Ambil Description
		var description = $("#input-description").val();
		console.log(description);

		//Modify By
		var modifyBy = userID;
		console.log(modifyBy);
		
		//Panggil API Edit
		var responseText = editBloodAPI(id, code, description, modifyBy).responseText;
		console.log(responseText);
		
		var response = $.parseJSON(responseText);
		
		if (response.code == 200) {
			alert(response.message);
			$(".modal").modal("hide");
			refreshList();
		} else {
			alert(response.message);
		}
	});

	//. class
	//# id
	$(".modal").modal("show");


}
	//-----------------------------------------------------------