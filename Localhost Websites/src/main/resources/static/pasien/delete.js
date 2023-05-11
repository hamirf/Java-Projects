//------------------------------ DELETE ------------------------------
//------------------------------ Single DELETE ------------------------------
function deletePasienAPI(customerID, deletedBy) {
	var form = new FormData();
	form.append("customer_id", customerID);
	form.append("deleted_by", deletedBy);

	return $.ajax({
		"url": "/api/pasien/deletePasien",
		"method": "PUT",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		async: false
	});
}

function popupHapusPasien(customerID, fullname) {
	console.log("Delete Kepencet!")

	//Ganti Title
	$(".modal-title").html("Hapus Pasien");

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<div class="row p-2" style="margin: 0px 10px; color: royalblue;">
			Anda yakin ingin menghapus pasien
		</div>
		<b><i style="padding-left: 30px; color: royalblue;">${fullname}</i></b>
		<div class="row p-2" style="margin: 0px 10px; color: royalblue;">
			<p>Riwayat medis pasien akan tetap tersimpan, namun Anda tidak dapat lagi membuat janji dokter / chat online untuk pasien ini</p>
		</div>
		<div class="p-2" style="text-align: center;">
			<button id="input-cancel" class="btn btn-lg btn-light" style="color: royalblue; width: 150px;">Batal</button>
			<i class="p-1"></i>
			<button id="input-delete" class="btn btn-lg btn-primary" style="width: 150px;">Hapus</button>
		</div>
		`
	);

	$("#input-cancel").click(function() {
		$(".modal").modal("hide");
	});

	$("#input-delete").click(function() {

		var deletedBy = user;
		//Ambil Response Text
		var responseText = deletePasienAPI(customerID, deletedBy).responseText;
		console.log(responseText);

		//Convert ke Json
		var response = $.parseJSON(responseText);

		if (response.code == 200) {
			alert(response.message);
			$(".modal").modal("hide");
			refreshList(keyword, null, limit, sorter, ascdesc);
		} else {
			alert(response.message);
		}

	});

	//. class
	//# id
	$(".modal").modal("show");

}
//------------------------------ Single DELETE ------------------------------

//------------------------------ Multiple DELETE ------------------------------
function deletePasiensesAPI(customerID, deletedBy) {
	var form = new FormData();
	form.append("customer_id", customerID);
	form.append("deleted_by", deletedBy);

	return $.ajax({
		"url": "/api/pasien/deletePasienses",
		"method": "PUT",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		async: false
	});
}

function getPasiensesNameAPI(customerIDs) {
	console.log("Customer IDs: " + customerIDs);
	return $.ajax({
		"url": "/api/pasien/customerName?customer_id=" + customerIDs,
		"method": "GET",
		"async": false
	});
}

function popupHapusPasienses() {
	var selectedPasien = [];
	$.each($(".pasienSelector:checked"), function() {
		selectedPasien.push($(this).val());
	});
	//	alert("Selected say(s) are: " + selectedPasien.join(","));
	var pasiensesID = selectedPasien.join(",");
	console.log(pasiensesID);

	var pasienNameResponse = getPasiensesNameAPI(pasiensesID).responseJSON.data;

//	var response = $.parseJSON(pasienNameResponseText);
	console.log("Nama Pasienses: ");
	console.log(pasienNameResponse);

	console.log("Hapuses Kepencet");
	//Ganti Title
	$(".modal-title").html("Hapus Pasien");

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<div class="row p-2" style="margin: 0px 10px; color: royalblue;">
			Anda yakin ingin menghapus pasien
		</div>
		<div class="pasiensesName">
			
				<b><i style="padding-left: 30px; color: royalblue;">Pasienses</i></b>
			</div>
		</div>
		<div class="row p-2" style="margin: 0px 10px; color: royalblue;">
			<p>Riwayat medis pasien akan tetap tersimpan, namun Anda tidak dapat lagi membuat janji dokter / chat online untuk pasien ini</p>
		</div>
		<div class="p-2" style="text-align: center;">
			<button id="input-cancel" class="btn btn-lg btn-light" style="color: royalblue; width: 150px;">Batal</button>
			<i class="p-1"></i>
			<button id="input-delete" class="btn btn-lg btn-primary" style="width: 150px;">Hapus</button>
		</div>
		`
	);
	
	$(".pasiensesName").html("");
	for (i = 0; i < pasienNameResponse.length; i++) {
		$(".pasiensesName").append(`
		<div>
			<b><i style="padding-left: 30px; color: royalblue;">${pasienNameResponse[i].fullname}</i></b>
		</div>
		`);
	}

	$("#input-cancel").click(function() {
		$(".modal").modal("hide");
	});

	$("#input-delete").click(function() {

		var deletedBy = user;
		//Ambil Response Text
		var responseText = deletePasiensesAPI(pasiensesID, deletedBy).responseText;
		console.log(responseText);

		//Convert ke Json
		var response = $.parseJSON(responseText);

		if (response.code == 200) {
			alert(response.message);
			$(".modal").modal("hide");
			$('#multipleDelete').attr('disabled', true); //disable input
			refreshList(keyword, null, limit, sorter, ascdesc);
		} else {
			alert(response.message);
		}

	});

	//. class
	//# id
	$(".modal").modal("show");

}
//------------------------------ Multiple DELETE ------------------------------