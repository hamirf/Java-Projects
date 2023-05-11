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
//------------- GET Data Customer -------------------------
//------------- GET Data Pasien -------------------------
function getPasienPerCustomerAPI(biodataID) {
	console.log(biodataID);
	return $.ajax({
		"url": "/api/pasien/pasienPerCustomer?biodata_id=" + biodataID,
		"method": "GET",
		"async": false
	});
}
//------------- GET Data Pasien -------------------------
//------------- GET Search Data Pasien -------------------------
function getSearchPasienPerCustomerAPI(biodataID, keyword) {
	console.log(biodataID + ", " + keyword);
	return $.ajax({
		"url": "/api/pasien/searchPasienPerCustomer?biodata_id=" + biodataID + "&keyword=" + keyword,
		"method": "GET",
		"async": false
	});
}
//------------- GET Search Data Pasien -------------------------
//------------- GET Pagination Data Pasien -------------------------
function getPaginationPasienPerCustomerAPI(biodataID, keyword, limit, page) {
	console.log(biodataID + ", " + keyword + ", " + limit + ", " + page);
	return $.ajax({
		"url": "/api/pasien/paginationPasienPerCustomer?biodata_id=" + biodataID + "&keyword=" + keyword + "&limit=" + limit + "&page=" + page,
		"method": "GET",
		"async": false
	});
}
//------------- GET Pagination Data Pasien -------------------------

function refreshList(keyword, page, limit) {

	if (keyword == null) {
		keyword = "";
	}

	if (page == null) {
		page = 1;
	}

	console.log("Halaman ke-" + page);

	if (limit == null) {
		limit = 1;
	}

	var resp = getBiodataIdAPI(user).responseJSON;
	console.log(resp);

	var parentBiodataID = resp.data[0].biodata_id;
	console.log("parentBiodataID = " + parentBiodataID + ", keyword = " + keyword);

	var response = getPersonalAccountDataAPI(parentBiodataID).responseJSON;
	console.log("Akun customer : ");
	console.log(response);

	var responsePasienPagination = getPaginationPasienPerCustomerAPI(parentBiodataID, keyword, limit, page).responseJSON;
	var dataPasienPagination = responsePasienPagination.data;
	var listPasien = dataPasienPagination.data;
	console.log(dataPasienPagination);
	console.log(dataPasienPagination.total_data);
	console.log("Daftar Pasien Pagination : ");
	console.log(listPasien);

	var list = response.data[0];
	console.log(list);
	console.log(list.image_path);

	$("#imgProfile").attr("src", list.image_path);

	$("#insertPasien").attr("onclick", "popupAddPasien('" + parentBiodataID + "')");

	$("#daftarPasien").html("");

	for (i = 0; i < listPasien.length; i++) {
		$("#daftarPasien").append(`
		<tr>
			<td>
				<div class="row">
					<div class="col-1" style="text-align: center; margin: auto;">
						<input type="checkbox" name="pasienSelector" value="${listPasien[i].id}" class="pasienSelector"">
					</div>
					<div class="col-7">
						<div class="namaPasien">${listPasien[i].fullname}</div>
						<div class="detailPasien">${listPasien[i].relation}, ${listPasien[i].age}</div>
						<div class="chatJanjiPasien">${listPasien[i].chat_count} Chat Online, ${listPasien[i].appointment_count} Janji Dokter</div>
					</div>
					<div class="col-4" style="text-align: right; margin: auto;">
						<button id="btnUbahDataPasien" class="btn"
							onclick="popupUpdatePasien(${parentBiodataID},
																${listPasien[i].biodata_id},
																'${listPasien[i].fullname}',
																${listPasien[i].id},
																'${listPasien[i].dob}',
																'${listPasien[i].gender}',
																${listPasien[i].blood_group_id},
																'${listPasien[i].rhesus_type}',
																'${listPasien[i].height}',
																'${listPasien[i].weight}',
																${listPasien[i].customer_relation_id})">
							<i class="fa-solid fa-pen-to-square" style="color: royalblue;"></i>
							<a style="color: royalblue;">Ubah</a>
						</button>
						<button id="btnHapusDataPasien" class="btn"
							onclick="popupHapusPasien(${listPasien[i].id},
														'${listPasien[i].fullname}')">
							<i class="fa-solid fa-trash-can" style="color: royalblue;"></i>
							<a style="color: royalblue;">Hapus</a>
						</button>
					</div>
				</div>
			</td>
		</tr>
		`);
	}
	var minData = (limit * (parseInt(page) - 1)) + 1;
	console.log("Ini data min - " + minData);

	//	var maxData = (limit * (parseInt(page) - 1)) + 1;
	//	console.log("Ini data max - " + maxData);

	// Data Yang Ditampilkan Pada Pagination 
	$("#banyakData").html(`Menampilkan ${minData} - ${(minData + parseInt(dataPasienPagination.item_per_page)) - 1} dari ${dataPasienPagination.total_data}`);

	// Pagination
	$('.pasienSelector').click(function() {
		//check if checkbox is checked
		if ($('.pasienSelector').is(':checked')) {

			$('#multipleDelete').attr('disabled', false); //enable input
			$('#multipleDelete').attr("onclick", "popupHapusPasienses()"); //onclick input
		} else {
			$('#multipleDelete').attr('disabled', true); //disable input
		}
	});

	var totalPage = Math.ceil(dataPasienPagination.total_data / limit);

	var prevPage = (parseInt(page) - 1);
	var nextPage = (parseInt(page) + 1);
	console.log("Halaman sebelumnya: " + prevPage + ", Halaman selanjutnya: " + nextPage);

	$('#btnSebelumnya').attr("onclick", "refreshList('" + keyword + "' , '" + prevPage + "' , '" + limit + "')");
	$('#btnSelanjutnya').attr("onclick", "refreshList('" + keyword + "' , '" + nextPage + "' , '" + limit + "')");

	if (page == 1) {
		$("#btnSebelumnya").prop("disabled", true);
	} else {
		$("#btnSebelumnya").prop("disabled", false);
	}

	if (page == totalPage) {
		$("#btnSelanjutnya").prop("disabled", true);
	} else {
		$("#btnSelanjutnya").prop("disabled", false);
	}

}

refreshList();
//----------------------------------------------------------