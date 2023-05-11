//-----------------EDIT CATEGORY ------------------------

function editNominalAPI(id, nominal, userID) {
	var form = new FormData();
	form.append("id", id);
	form.append("nominal", nominal);
	form.append("modify_by", userID);

	return $.ajax({
		"url": "/api/nominal/update",
		"method": "PUT",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		"async": false
	});
}

function bukaPopupEdit(nominal, id) {
	console.log("Edit Nominal Kepencet!");
	console.log(nominal + " , " + id);


	//Ganti Title
	$(".modal-title").html("Change Nominal");

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<table class="table table-borderless">
			<tr>
				<td>Nominal*</td>
				<td>
					<input type=number id="input-nominal" class="form-control" value="${nominal}" required>
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
		//Ambil Nominal
		var nominal = $("#input-nominal").val();
		console.log(nominal);

		//Modify By
		var modifyBy = userID;
		console.log(modifyBy);
		
		//Panggil API Edit
		var responseText = editNominalAPI(id, nominal, modifyBy).responseText;
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