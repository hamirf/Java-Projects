function getBiodataIdAPI(userID) {
	console.log(userID);
	return $.ajax({
		"url": "/api/user/getbId?user_id=" + userID,
		"method": "GET",
		"async": false
	});
}

//------------- GET Data Customer -------------------------
function getPersonalAccountDataAPI(biodataID) {
	console.log(biodataID);
	return $.ajax({
		"url": "/api/customer/getCustBioById?biodata_id=" + biodataID,
		"method": "GET",
		"async": false
	});
}

//------------- GET Data Pasien -------------------------
function getPasienPerCustomerAPI(biodataID) {
	console.log(biodataID);
	return $.ajax({
		"url": "/api/pasien/pasienPerCustomer?biodata_id=" + biodataID,
		"method": "GET",
		"async": false
	});
}

//------------- GET Search Data Pasien -------------------------
function getSearchPasienPerCustomerAPI(biodataID, keyword) {
	console.log(biodataID + ", " + keyword);
	return $.ajax({
		"url": "/api/pasien/searchPasienPerCustomer?biodata_id=" + biodataID + "&keyword=" + keyword,
		"method": "GET",
		"async": false
	});
}

function refreshList(keyword) {

	if (keyword == null) {
		keyword = "";
	}

	var resp = getBiodataIdAPI(user).responseJSON;
	console.log(resp);

	var biodataID = resp.data[0].biodata_id;
	console.log("biodataID = " + biodataID + ", keyword = " + keyword);

	var response = getPersonalAccountDataAPI(biodataID).responseJSON;
	console.log("Akun customer : ");
	console.log(response);

	//	var responsePasien = getPasienPerCustomerAPI(biodataID).responseJSON;
	//	var listPasien = responsePasien.data
	//	console.log("Daftar Pasien : ");
	//	console.log(listPasien);

	var responsePasien = getSearchPasienPerCustomerAPI(biodataID, keyword).responseJSON;
	var listPasien = responsePasien.data;
	console.log("Daftar Pasien : ");
	console.log(listPasien);

	var list = response.data[0];
	console.log(list);
	console.log(list.image_path);

	$("#imgProfile").attr("src", list.image_path);

	$("#daftarPasien").html("");

	for (i = 0; i < listPasien.length; i++) {
		$("#daftarPasien").append(`
		<tr>
			<td>
				<div class="row">
					<div class="col-1" style="text-align: center; margin: auto;">
						<input type="checkbox" value="${listPasien[i].id}" class="pasienSelector">
					</div>
					<div class="col-7">
						<div class="namaPasien">${listPasien[i].fullname}</div>
						<div class="detailPasien">${listPasien[i].relation}, ${listPasien[i].age}</div>
						<div class="chatJanjiPasien">${listPasien[i].chat_count} Chat Online, ${listPasien[i].appointment_count} Janji Dokter</div>
					</div>
					<div class="col-4" style="text-align: right; margin: auto;">
						<button id="btnUbahDataPasien" class="btn"
							onclick="bukaPopupUbahDataPasien()">
							<i class="fa-solid fa-pen-to-square" style="color: royalblue;"></i>
							<a style="color: royalblue;">Ubah</a>
						</button>
						<button id="btnHapusDataPasien" class="btn"
							onclick="bukaPopupHapusDataPasien()">
							<i class="fa-solid fa-trash-can" style="color: royalblue;"></i>
							<a style="color: royalblue;">Hapus</a>
						</button>
					</div>
				</div>
			</td>
		</tr>
		`);
	}

	$('.pasienSelector').click(function() {
		//check if checkbox is checked
		if ($('.pasienSelector').is(':checked')) {

			$('#multipleDelete').attr('disabled', false); //enable input

		} else {
			$('#multipleDelete').attr('disabled', true); //disable input
		}
	});

}

refreshList();
//----------------------------------------------------------