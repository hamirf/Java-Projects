//----------------- INSERT PASIEN ------------------------
function getBloodsAPI() {
	return $.ajax({
		"url": "/api/blood/get",
		"method": "GET",
		"async": false
	})
}

function getRelationsAPI() {
	return $.ajax({
		"url": "/api/pasien/relation",
		"method": "GET",
		"async": false
	})
}

function addPasienAPI(fullname, dob, gender, goldar, rhesus, height, weight, relation, biodataID, createdBy) {
	if (gender === undefined) {
		gender = "";
	}

	if (goldar === undefined) {
		goldar = "";
	}

	if (rhesus === undefined) {
		rhesus = "";
	}

	var form = new FormData();
	form.append("fullname", fullname);
	form.append("dob", dob);
	form.append("gender", gender);
	form.append("golongan_darah", goldar);
	form.append("rhesus", rhesus);
	form.append("height", height);
	form.append("weight", weight);
	form.append("relation", relation);
	form.append("parent_biodata_id", biodataID);
	form.append("created_by", createdBy);

	return $.ajax({
		"url": "/api/pasien/addPasien",
		"method": "POST",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		"async": false
	});
}

function popupAddPasien(parentBiodataID) {
	// fullname, dob, gender, golonganDarah, rhesus, height, weight, relation

	console.log("Add Kepencet!");

	var createdBy = user;

	console.log(parentBiodataID + " , " + createdBy);

	$.datepicker.setDefaults($.datepicker.regional["id"]);

	$(function() {
		$("#input-datepicker").datepicker({
			dateFormat: 'dd MM yy',
			maxDate: "+0d",
			changeMonth: true,
			changeYear: true,
			yearRange: "1900:9999",
			defaultDate: "+0d"
		});
	});

	var bloodList = getBloodsAPI().responseJSON.data;
	console.log(bloodList);
	var bloodListhtml = "";

	for (i = 0; i < bloodList.length; i++) {
		bloodListhtml += `<option value="${bloodList[i].id}">${bloodList[i].code}</option>`;
	}

	var relationList = getRelationsAPI().responseJSON.data;
	console.log(relationList);
	var relationListhtml = "";

	for (i = 0; i < relationList.length; i++) {
		relationListhtml += `<option value="${relationList[i].customer_relation_id}">${relationList[i].relation}</option>`;
	}

	//Ganti Title
	$(".modal-title").html("Tambah Pasien");

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<table class="table table-borderless">
		
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						Nama Lengkap*
					</div>
					<div>
						<input type=text id="input-name" class="form-control" required>
						<i id = "error-message-name" style="color:red; font-size: 10pt; padding-left: 4px;"></i>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						Tanggal Lahir*
					</div>
					<div>
						<input type="text" id="input-datepicker" class="form-control" required>
						<i id = "error-message-dob" style="color:red; font-size: 10pt; padding-left: 4px;"></i>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="row p-1">
						<div class="gender-text col-6" style="color: royalblue;">
							<div>Jenis Kelamin*</div>
							<i id = "error-message-gender" style="color:red; font-size: 10pt; padding-left: 4px;"></i>
						</div>
						<div class="gender-opt col-6" style="color: royalblue;">
							<label style="margin-bottom: 10px;">
								<input class="input-gender" id="gender-pria" type="radio" name="pria-wanita" value="L"> Pria
							</label>
							<label style="margin-left: 60px;">
								<input class="input-gender" id="gender-wanita" type="radio" name="pria-wanita" value="P"> Wanita
							</label>
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="row p-1">
						<div class="goldar-opt-text col-6" style="color: royalblue;">
							Golongan Darah / Rhesus
							<select id="goldar-opt" style="margin-top: 5px;">
							<option value="" hidden selected>--Pilih--</option>
								${bloodListhtml}
							</select>
						</div>
						<div class="rhesus-opt col-6" style="color: royalblue; margin-top: 30px;">
							<label style="margin-bottom: 10px;">
								<input class="input-rhesus" id="rhesus-plus" type="radio" name="rhesus" value="Rh +"> Rh +
							</label>
							<label style="margin-left: 50px;">
								<input class="input-rhesus" id="rhesus-min" type="radio" name="rhesus" value="Rh -"> Rh -
							</label>
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="row p-1">
						<div class="height-div col-6" style="color: royalblue;">
							Tinggi Badan
							<input id="height-form" type="number" style="margin-top: 5px;"> cm
							<i id = "error-message-height" style="color:red; font-size: 10pt; padding-left: 4px;"></i>
						</div>
						<div class="height-div col-6" style="color: royalblue;">
							Berat Badan
							<input id="weight-form" type="number" style="margin-top: 5px;"> kg
							<i id = "error-message-weight" style="color:red; font-size: 10pt; padding-left: 4px;"></i>
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="row p-1">
						<div class="relation-div col-12" style="color: royalblue;">
							Relasi*
							<select id="relation-opt" style="margin-top: 5px;">
							<option value="" hidden selected>--Pilih--</option>
								${relationListhtml}
							</select>
							<i id = "error-message-relation" style="color:red; font-size: 10pt; padding-left: 4px;"></i>
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<td style="text-align: center;">
					<button id="input-cancel" class="btn btn-lg btn-light" style="color: royalblue; width: 150px;">Batal</button>
					<i class="p-1"></i>
					<button id="input-save" class="btn btn-lg btn-primary" style="width: 150px;">Simpan</button>
				</td>
			</tr>
			
		</table>
		`
	);

	$("#input-cancel").click(function() {
		$(".modal").modal("hide");
	});

	$("#input-save").click(function() {
		//Ambil namaPasien
		var namaPasien = $("#input-name").val();
		console.log("Nama: " + namaPasien);

		//Ambil dobPasien
		var dobPasien = $("#input-datepicker").val();
		console.log("DOB: " + dobPasien);
	
		var genderPasien = $('input[name="pria-wanita"]:checked').val();
		console.log("Gender: " + genderPasien);

		//Ambil golDarPasien
		var golDarPasien = $("#goldar-opt").val();
		console.log("Golongan Darah: " + golDarPasien);

		var rhesusPasien = $('input[name="rhesus"]:checked').val();	
		console.log("Rhesus: " + rhesusPasien);

		//Ambil heightPasien
		var heightPasien = $("#height-form").val();
		console.log("Height: " + heightPasien);

		//Ambil weightPasien
		var weightPasien = $("#weight-form").val();
		console.log("Weight: " + weightPasien);

		//Ambil relationPasien
		var relationPasien = $("#relation-opt").val();
		console.log("Relation: " + relationPasien);

		console.log("Biodata Id: " + parentBiodataID);

		//Ambil createdBy
		var createdBy = user;
		console.log("Created By: " + createdBy);

		//Panggil API Add => fullname, dob, gender, goldar, rhesus, height, weight, relation, biodataID, createdBy
		var responseText = addPasienAPI(namaPasien, dobPasien, genderPasien, golDarPasien, rhesusPasien, heightPasien, weightPasien, relationPasien, parentBiodataID, createdBy).responseText;
		console.log(responseText);

		var response = $.parseJSON(responseText);

		if (response.code == 200) {
			alert(response.message);
			$(".modal").modal("hide");
			refreshList(keyword, null, limit, sorter, ascdesc);
		} else if (response.code > 10 && response.code < 20) {
			$("#error-message-name").html(`<i id = "error-message-name" style="color:red; font-size: 10pt;">${response.message}</i>`);
			$("#error-message-dob").html(`<i id = "error-message-dob" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-gender").html(`<i id = "error-message-gender" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-height").html(`<i id = "error-message-height" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-weight").html(`<i id = "error-message-weight" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-relation").html(`<i id = "error-message-relation" style="color:red; font-size: 10pt;"></i>`);
		} else if (response.code > 20 && response.code < 30) {
			$("#error-message-name").html(`<i id = "error-message-nam" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-dob").html(`<i id = "error-message-dob" style="color:red; font-size: 10pt;">${response.message}</i>`);
			$("#error-message-gender").html(`<i id = "error-message-gender" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-height").html(`<i id = "error-message-height" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-weight").html(`<i id = "error-message-weight" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-relation").html(`<i id = "error-message-relation" style="color:red; font-size: 10pt;"></i>`);
		} else if (response.code > 30 && response.code < 40) {
			$("#error-message-name").html(`<i id = "error-message-nam" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-dob").html(`<i id = "error-message-dob" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-gender").html(`<i id = "error-message-gender" style="color:red; font-size: 10pt;">${response.message}</i>`);
			$("#error-message-height").html(`<i id = "error-message-height" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-weight").html(`<i id = "error-message-weight" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-relation").html(`<i id = "error-message-relation" style="color:red; font-size: 10pt;"></i>`);
		} else if (response.code > 40 && response.code < 50) {
			$("#error-message-name").html(`<i id = "error-message-nam" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-dob").html(`<i id = "error-message-dob" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-gender").html(`<i id = "error-message-gender" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-height").html(`<i id = "error-message-height" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-weight").html(`<i id = "error-message-weight" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-relation").html(`<i id = "error-message-relation" style="color:red; font-size: 10pt;">${response.message}</i>`);
		} else if (response.code == 51) {
			$("#error-message-name").html(`<i id = "error-message-nam" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-dob").html(`<i id = "error-message-dob" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-gender").html(`<i id = "error-message-gender" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-height").html(`<i id = "error-message-height" style="color:red; font-size: 10pt;">${response.message}</i>`);
			$("#error-message-weight").html(`<i id = "error-message-weight" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-relation").html(`<i id = "error-message-relation" style="color:red; font-size: 10pt;"></i>`);
		} else if (response.code == 52) {
			$("#error-message-name").html(`<i id = "error-message-nam" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-dob").html(`<i id = "error-message-dob" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-gender").html(`<i id = "error-message-gender" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-height").html(`<i id = "error-message-height" style="color:red; font-size: 10pt;"></i>`);
			$("#error-message-weight").html(`<i id = "error-message-weight" style="color:red; font-size: 10pt;">${response.message}</i>`);
			$("#error-message-relation").html(`<i id = "error-message-relation" style="color:red; font-size: 10pt;"></i>`);
		}
	});

	//. class
	//# id
	$(".modal").modal("show");

}
//-----------------EDIT DATA PRIBADI ------------------------