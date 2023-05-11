function getBiodataIdAPI(userID) {
	console.log(userID);
	return $.ajax({
		"url": "/api/user/getbId?user_id=" + userID,
		"method": "GET",
		"async": false
	});
}
//------------- GET Data -------------------------
function getPersonalAccountDataAPI(biodataID) {
	console.log(biodataID);
	return $.ajax({
		"url": "/api/customer/getCustBioById?biodata_id=" + biodataID,
		"method": "GET",
		"async": false
	});
}

function refreshList(biodataID) {
	$(".modal").mouseup(function() {
	});

	var resp = getBiodataIdAPI(user).responseJSON;
	console.log(resp);
	
	var biodataID = resp.data[0].biodata_id;
	console.log(biodataID);

	var response = getPersonalAccountDataAPI(biodataID).responseJSON;
	console.log(response);

	var list = response.data[0];
	console.log(list);
	console.log(list.image_path);
	console.log(list.fullname);
	console.log(list.dob);
	console.log(list.mobile_phone);
	console.log(list.email);

	//	$("#btn-ubah-data-pribadi").onclick = function() {bukaPopupUbahDataPribadi(list.fullname, list.dob, list.mobile_phone)}

	$("#btn-ubah-data-pribadi").attr("onclick", "bukaPopupUbahDataPribadi('" + list.fullname + "', '" + list.dob + "', '" + list.mobile_phone + "', '" + biodataID + "')");

	$("#imgProfile").attr("src", list.image_path);

	$("#personal-tbody-content").html("");
	$("#account-tbody-content").html("");
	$("#personal-tbody-content").append(
		`
		<tr>
			<td>
				<p style="color: royalblue;">
					<i>Nama Lengkap</i>
				</p>
				<div class="col-3"></div>
				<div class="col-9" style="color: royalblue; font-size: 14pt;">
					${list.fullname}
				</div>
				<hr style="width: 50%; text-align: left; margin-left: 0;">
			</td>
		</tr>
		<tr>
			<td>
				<p style="color: royalblue;">
					<i>Tanggal Lahir</i>
				</p>
				<div class="col-3"></div>
				<div class="col-9" style="color: royalblue; font-size: 14pt;">
					${list.dob}
				</div>
				<hr style="width: 50%; text-align: left; margin-left: 0;">
			</td>
		</tr>
		<tr>
			<td>
				<p style="color: royalblue;">
					<i>Nomor Handphone</i>
				</p>
				<div class="col-3"></div>
				<div class="col-9" style="color: royalblue; font-size: 14pt;">
					${list.mobile_phone}
				</div>
				<hr style="width: 50%; text-align: left; margin-left: 0;">
			</td>
		</tr>
		`
	)
	$("#account-tbody-content").append(
		`
			<tr>
				<td>
					<div class="row">
						<div class="col" style="color: royalblue;">
							<i>Email</i>
						</div>
						<div class="col">
							<button class="btn" onclick="bukaPopupUbahEmail('${list.email}', ${biodataID})">
								<i class="fa-solid fa-pen"
									style="color: royalblue;"></i>
								<i style="color: royalblue;">Ubah</i>
							</button>
						</div>
					</div>
					<div class="col-3"></div>
					<div id="ubah-email" class="col-9" style="color: royalblue; font-size: 14pt;">
						${list.email}
					</div>
					<hr style="width: 50%; text-align: left; margin-left: 0;">
				</td>
			</tr>
			<tr>
				<td>
					<div class="row">
						<div class="col" style="color: royalblue;">
							<i>Password</i>
						</div>
						<div class="col">
							<button class="btn" onclick="bukaPopupUbahPW('${list.password}', ${biodataID})">
								<i class="fa-solid fa-pen"
									style="color: royalblue;"></i>
								<i style="color: royalblue;">Ubah</i>
							</button>
						</div>
					</div>
					<div class="col-3"></div>
					<div id="ubah-password" class="col-9" style="color: royalblue; font-size: 14pt;">
						<p>********</p>
					</div>
					<hr style="width: 50%; text-align: left; margin-left: 0;">
				</td>
			</tr>
			`
	)
}

refreshList();
//----------------------------------------------------------