//--------------------Insert Categoriy ---------------------
function insertBloodAPI(code, description, userID) {
	var form = new FormData();
	form.append("code", code);
	form.append("description", description);
	form.append("create_by", userID);

	return $.ajax({
		"url": "/api/blood/insert",
		"method": "POST",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		"async": false
	});
}

function bukaPopupAdd() {
	console.log("Add Blood Kepencet!")

	//Ganti Title
	$(".modal-title").html("Add New Blood");

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<table class="table table-borderless">
			<tr>
				<td>Code*</td>
				<td>
					<input type=text id="input-code" class="form-control required">
				</td>
			</tr>
			<tr>
				<td>Description</td>
				<td>
					<input type=text id="input-description" class="form-control">
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<button id="input-cancel" class="btn btn-light">Cancel</button>
					<button id="input-create" class="btn btn-warning">Create</button>
				</td>
			</tr>
		</table>
		`
	);

	$("#input-cancel").click(function() {
		$(".modal").modal("hide");
	});

	$("#input-create").click(function() {
		//Ambil Code
		var code = $("#input-code").val();
		console.log(code);
		
		//Ambil Description
		var description = $("#input-description").val();
		console.log(description);

		//Create By
		var createBy = userID;
		console.log(createBy);
		
		//Panggil API Insert
		var responseText = insertBloodAPI(code, description, createBy).responseText;
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
//-------------------------------------------------------------