//--------------------Insert Categoriy ---------------------
function insertNominalAPI(nominal, userID) {
	var form = new FormData();
	form.append("nominal", nominal);
	form.append("create_by", userID);

	return $.ajax({
		"url": "/api/nominal/insert",
		"method": "POST",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		"async": false
	});
}

function bukaPopupAdd() {
	console.log("Add Nominal Kepencet!")

	//Ganti Title
	$(".modal-title").html("Add New Nominal");

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<table class="table table-borderless">
			<tr>
				<td>Nominal*</td>
				<td>
					<input type=number id="input-nominal" class="form-control required">
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
		//Ambil Nominal
		var nominal = $("#input-nominal").val();
		console.log(nominal);

		//Create By
		var createBy = userID;
		console.log(createBy);
		
		//Panggil API Insert
		var responseText = insertNominalAPI(nominal, createBy).responseText;
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