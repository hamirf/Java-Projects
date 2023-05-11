//-----------------EDIT DATA PRIBADI ------------------------

function editPersonalDataAPI(biodataID, fullname, dob, mobilePhone, modifyBy) {
	var form = new FormData();
	form.append("biodata_id", biodataID);
	form.append("fullname", fullname);
	form.append("dob", dob);
	form.append("mobile_phone", mobilePhone);
	form.append("modify_by", modifyBy);

	return $.ajax({
		"url": "/api/user/updatePersonalData",
		"method": "PUT",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		"async": false
	});
}

function bukaPopupUbahDataPribadi(fullname, dob, mobile_phone, biodataID) {
	console.log("Edit Kepencet!");
	console.log(fullname + " , " + dob + " , " + mobile_phone);

	var resp = getBiodataIdAPI(user).responseJSON;
	console.log(resp);

	var modifyBy = user;
	console.log(biodataID + " , " + modifyBy);

	/*	
		var dobArr = dob.split(" ");
		var dobMonth = "4";
		var dobDate = new Date(dobArr[2], dobMonth, dobArr[0]);
		console.log(dobDate.toDateString());
	*/

	$.datepicker.setDefaults($.datepicker.regional["id"]);

	//Ganti Title
	$(".modal-title").html(`<h3>Data Pribadi</h3>`);

	$(function() {
		$("#input-datepicker").datepicker({
			dateFormat: 'dd MM yy',
			maxDate: "+0d",
			changeMonth: true,
			changeYear: true,
			yearRange: "1900:9999",
			defaultDate: dob
		});
	});

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
						<input type=text id="input-name" class="form-control" value="${fullname}" required>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						Tanggal Lahir*
					</div>
					<div>
						<input type="text" id="input-datepicker" class="form-control" value="${dob}">
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						Nomor Handphone*
					</div>
					<div>
						<input type="tel" id="input-phone" class="form-control" value="${mobile_phone}" required>
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
		//Ambil name
		var yourname = $("#input-name").val();
		console.log(yourname);

		//Ambil yourdate
		var yourdate = $("#input-datepicker").val();
		console.log(yourdate);

		//Ambil yourphone
		var yourphone = $("#input-phone").val();
		console.log(yourphone);

		//Modify By
		var modifyBy = user;
		console.log(modifyBy);

		//Panggil API Edit
		var responseText = editPersonalDataAPI(biodataID, yourname, yourdate, yourphone, modifyBy).responseText;
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
//-----------------EDIT DATA PRIBADI ------------------------
//-----------------EDIT DATA AKUN ------------------------

//-----------------EDIT EMAIL ------------------------
function sendOTPToEmail(email) {
	var form = new FormData();
	form.append("email", email);

	return $.ajax({
		"url": "/api/user/sendOTP",
		"method": "POST",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		"async": false
	})
}

function checkOTP(email, otp) {
	var form = new FormData();
	form.append("email", email);
	form.append("otp", otp);

	return $.ajax({
		"url": "/api/user/checkOTP",
		"method": "POST",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		"async": false
	})
}

function editUserEmailAPI(email, modifyBy, biodataID) {
	var form = new FormData();
	form.append("email", email);
	form.append("modify_by", modifyBy);
	form.append("biodata_id", biodataID);

	return $.ajax({
		"url": "/api/user/updateEmail",
		"method": "PUT",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		"async": false
	});
}

//-----------------UBAH EMAIL, DAPET OTP ------------------------
function bukaPopupUbahEmail(email, biodataID) {
	console.log("Edit email kepencet!");
	console.log(email);

	//Ganti Title
	$(".modal-title").html(`<h3>Ubah E-mail</h3>`);

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<table class="table table-borderless">
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						E-mail*
					</div>
					<div>
						<input type=text id="input-email" class="form-control" value="${email}" required>
						<span id = "errorMessage" style="color:red; font-size: 10pt;"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<button id="input-cancel" class="btn btn-lg btn-light" style="color: royalblue; width: 150px;">Batal</button>
					<i class="p-1"></i>
					<button id="input-change" class="btn btn-lg btn-primary" style="width: 150px;">Ubah E-mail</button>
				</td>
			</tr>
		</table>
		`
	);

	$("#input-cancel").click(function() {
		$(".modal").modal("hide");
	})

	$("#input-change").click(function() {
		//Ambil name
		var youremail = $("#input-email").val();
		console.log(youremail);

		var responseText = sendOTPToEmail(youremail).responseText;
		console.log(responseText);

		var response = $.parseJSON(responseText);
		console.log(response);

		if (response.code == 200) {
			alert(response.message);
			bukaPopupOTP(youremail, biodataID);
			countDown();
		} else {
			$("#errorMessage").html(`<i id = "errorMessage" style="color:red; font-size: 10pt;">*${response.message}</i>`);
			//			alert(response.message);
		}
	})

	//. class
	//# id
	$(".modal").modal("show");

}
//-----------------UBAH EMAIL, DAPET OTP ------------------------

//----------------- COUNTDOWN ------------------------
var x;

function countDown() {
	// Set the date we're counting down to
	var countDownDate = new Date().getTime();
	countDownDate += 1000 * 60 * 1;
	// Update the count down every 1 second
	x = setInterval(function() {

		// Get today's date and time
		var now = new Date().getTime();

		// Find the distance between now and the count down date
		var distance = countDownDate - now;

		// Time calculations for days, hours, minutes and seconds
		//var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		//var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		var seconds = Math.floor((distance % (1000 * 60)) / 1000);

		// Output the result in an element with id="demo"
		var mm = (minutes).toString().padStart(2, 0);
		var ss = (seconds).toString().padStart(2, 0);
		// If the count down is over, write some text 
		//document.getElementById("count-down").innerHTML = minutesss + ":" + secondsss;

		if (distance < 0) {
			clearInterval(x);
			$("#count-down").prop("hidden", true);
			$("#input-get-otp").prop("hidden", false);
		} else {
			$("#count-down").val("Kirim kembali OTP dalam " + mm + ":" + ss);
			$("#input-get-otp").prop("hidden", true);
		}
	}, 1000);
}
//----------------- COUNTDOWN ------------------------

//----------------- CHECK OTP ------------------------
function bukaPopupOTP(email, biodataID) {
	console.log("Cek OTP kepencet!");
	console.log(email);
	clearInterval(x);

	//Ganti Title
	$(".modal-title").html(`<h3>Ubah E-mail</h3>`);

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<table class="table table-borderless">
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						<p style="font-size: 14pt;">Masukkan kode OTP yang telah dikirimkan ke email anda</p>
					</div>
					<div class="p-1">
						<input id="input-otp" style="text-align: center;" type="tel" class="form-control" required>
						<i id = "errorMessage" style="color:red; font-size: 10pt;"></i>
					</div>
					<div class="p-1" style="color: royalblue; text-align: center;">
						<input id="count-down" class="form-control" style="color: royalblue; background-color: white; text-align: center; border: none;" disabled>
						<button id="input-get-otp" class="btn btn-sm btn-primary" style="width: 150px;" hidden>Kirim ulang OTP</button>
					</div>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<button id="input-confirm-otp" class="btn btn-lg btn-primary" style="width: 200px;">Konfirmasi OTP</button>
				</td>
			</tr>
		</table>
		`
	);

	var otp = $("#input-otp").val();

	$("#input-get-otp").click(function() {
		sendOTPToEmail(email);
		bukaPopupOTP(email, biodataID);
		checkOTP(email, otp);
		countDown();
	})

	$("#input-confirm-otp").click(function() {
		//Ambil otp
		//		var otp = $("#input-otp").val();
		console.log(otp);

		var responseText = checkOTP(email, otp).responseText;
		console.log(responseText);

		var response = $.parseJSON(responseText);
		console.log(response);

		if (response.code == 200) {
			bukaPopupSaveUpdatedEmail(email, biodataID);
		} else {
			$("#errorMessage").html(`<i id = "errorMessage" style="color:red; font-size: 10pt;">*${response.message}</i>`)
			//			alert(response.message);
		}
	})

	//. class
	//# id
	$(".modal").modal("show");
}
//----------------- CHECK OTP ------------------------

//----------------- SAVE EMAIL ------------------------
function bukaPopupSaveUpdatedEmail(email, biodataID) {

	var modifyBy = user;

	console.log(email + " , " + modifyBy + " , " + biodataID + " , ");

	var responseText = editUserEmailAPI(email, modifyBy, biodataID).responseText;

	var response = $.parseJSON(responseText);
	console.log(response);

	//Ganti Title
	$(".modal-title").html(`<h3>Ubah E-mail</h3>`);

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<table class="table table-borderless">
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						${response.message}
					</div>
				</td>
			</tr>
		</table>
		`
	);

	$("#modal-close-btn").attr('onclick', 'location.href = "/login"');

	//. class
	//# id
	$(".modal").modal("show");

	$(".modal").mouseup(function() {
		console.log('Closed')
		location.href = "/login"
	});
}
//----------------- SAVE EMAIL ------------------------

//----------------- VALIDASI PASSWORD LAMA ------------------------
function editPasswordAPI(oldPassword, password, password2, modifyBy, biodataID) {
	var form = new FormData();
	form.append("old_password", oldPassword);
	form.append("password", password);
	form.append("password2", password2);
	form.append("modify_by", modifyBy);
	form.append("biodata_id", biodataID);

	return $.ajax({
		"url": "/api/user/updatePassword",
		"method": "PUT",
		"processData": false,
		"mimeType": "multipart/form-data",
		"contentType": false,
		"data": form,
		"async": false
	})
}

function showPassword() {
	$("#input-password").attr('type', 'text');
	$("#togglePassword").attr('onclick', 'hidePassword()');
	//	$(this).toggleClass('fa-solid fa-eye-slash');
	$("#togglePassword").toggleClass('fa-solid fa-eye');
}

function hidePassword() {
	$("#input-password").attr('type', 'password');
	$("#togglePassword").attr('onclick', 'showPassword()');
	//	$(this).toggleClass('fa-solid fa-eye');
	$("#togglePassword").toggleClass('fa-solid fa-eye-slash');
}

function showRePassword() {
	$("#input-ulang-password").attr('type', 'text');
	$("#toggleUlangPassword").attr('onclick', 'hideRePassword()');
	//	$(this).toggleClass('fa-solid fa-eye-slash');
	$("#toggleUlangPassword").toggleClass('fa-solid fa-eye');
}

function hideRePassword() {
	$("#input-ulang-password").attr('type', 'password');
	$("#toggleUlangPassword").attr('onclick', 'showRePassword()');
	//	$(this).toggleClass('fa-solid fa-eye');
	$("#toggleUlangPassword").toggleClass('fa-solid fa-eye-slash');
}

function bukaPopupUbahPW(password, biodataID) {
	console.log("Edit pw kepencet!");
	//Ganti Title
	$(".modal-title").html(`<h3>Ubah Password</h3>`);

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<table class="table table-borderless">
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						<p style="font-size: 14pt;">Masukkan password Anda saat ini</p>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						Password*
					</div>
					<div>
						<input type="password" id="input-password" class="form-control" required>
						<i class="fa-solid fa-eye-slash" style="color: royalblue; margin-top: -27px; margin-left: 93%;" id="togglePassword" onclick="showPassword()"></i>
						<i id = "errorMessage" style="color:red"></i>		
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						Masukkan ulang password*
					</div>
					<div>
						<input type="password" id="input-ulang-password" class="form-control" required>
						<i class="fa-solid fa-eye-slash" style="color: royalblue; margin-top: -27px; margin-left: 93%;" id="toggleUlangPassword" onclick="showRePassword()"></i>
						<i id = "errorMessage1" style="color:red"></i>						
					</div>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<button id="input-change" class="btn btn-lg btn-primary" style="width: 200px;">Ubah Password</button>
				</td>
			</tr>
		</table>
		`
	);

	$("#input-change").click(function() {
		//Ambil name
		var yourpassword = $("#input-password").val();
		console.log(yourpassword);

		//Ambil name
		var repassword = $("#input-ulang-password").val();
		console.log(repassword);

		//var responseText = editPasswordAPI(yourpassword, modifyBy, biodataID).responseText;
		//console.log(responseText);

		//var response = $.parseJSON(responseText);
		//console.log(response);

		if (password == yourpassword && yourpassword == repassword) {
			alert("Password sesuai");
			bukaPopupUpdatePassword(password, biodataID);
		} else if (yourpassword == "") {
			$("#errorMessage").html(`<i id = "errorMessage" style="color:red; font-size: 10pt;">*Ketik password anda saat ini!</i>`);
			//			alert("Ketik password anda saat ini!");
		} else if (yourpassword != password) {
			$("#errorMessage").html(`<i id = "errorMessage" style="color:red; font-size: 10pt;">*Password salah!</i>`)
			//			alert("Password salah!");
		} else if (repassword == "") {
			$("#errorMessage").html(`<i id = "errorMessage" style="color:red; font-size: 10pt;"></i>`);
			$("#errorMessage1").html(`<i id = "errorMessage1" style="color:red; font-size: 10pt;">*Ketik ulang password!</i>`);
			//			alert("Ketik ulang password!");
		} else if (password == yourpassword && yourpassword != repassword) {
			$("#errorMessage").html(`<i id = "errorMessage" style="color:red; font-size: 10pt;"></i>`);
			$("#errorMessage1").html(`<i id = "errorMessage1" style="color:red; font-size: 10pt;">*Password tidak sama!</i>`);
			//			alert("Ulang password tidak sama!");
		}
	})

	//. class
	//# id
	$(".modal").modal("show");

}
//----------------- VALIDASI PASSWORD LAMA ------------------------

//----------------- UBAH PASSWORD ------------------------
function bukaPopupUpdatePassword(password, biodataID) {
	console.log("Edit ubah pw kepencet!");

	//Ganti Title
	$(".modal-title").html(`<h3>Ubah Password</h3>`);

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<table class="table table-borderless">
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						<p style="font-size: 14pt;">Masukkan password baru</p>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						Password*
					</div>
					<div>
						<input type="password" id="input-password" class="form-control" required>
						<i class="fa-solid fa-eye-slash" style="color: royalblue; margin-top: -27px; margin-left: 93%;" id="togglePassword" onclick="showPassword()"></i>
						<i id = "errorMessage" style="color:red"></i>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						Masukkan ulang password*
					</div>
					<div>
						<input type="password" id="input-ulang-password" class="form-control" required>
						<i class="fa-solid fa-eye-slash" style="color: royalblue; margin-top: -27px; margin-left: 93%;" id="toggleUlangPassword" onclick="showRePassword()"></i>
						<i id = "errorMessage1" style="color:red"></i>
					</div>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<button id="input-change" class="btn btn-lg btn-primary" style="width: 200px;">Ubah Password</button>
				</td>
			</tr>
		</table>
		`
	);

	$("#input-change").click(function() {

		//Ambil name
		var yourpassword = $("#input-password").val();
		console.log(yourpassword);

		//Ambil name
		var repassword = $("#input-ulang-password").val();
		console.log(repassword);

		var modifyBy = user;

		console.log(yourpassword + " , " + modifyBy + " , " + biodataID + " , ");

		var responseText = editPasswordAPI(password, yourpassword, repassword, modifyBy, biodataID).responseText;

		var response = $.parseJSON(responseText);
		console.log(response);

		if (response.code == 200) {
			bukaPopupSaveUpdatedPassword(response.message);
		} else if (response.code > 20 && response.code < 30) {
			$("#errorMessage").html(`<i id = "errorMessage" style="color:red; font-size: 10pt;">*${response.message}</i>`);
		} else if (response.code > 30 && response.code < 40) {
			$("#errorMessage").html(`<i id = "errorMessage" style="color:red; font-size: 10pt;"></i>`);
			$("#errorMessage1").html(`<i id = "errorMessage1" style="color:red; font-size: 10pt;">*${response.message}</i>`);
		}

		/*		if (yourpassword == repassword && response.code == 200) {
					bukaPopupSaveUpdatedPassword(response.message);
				} else if (response.code != 200) {
					$("#errorMessage").html(`<i id = "errorMessage" style="color:red; font-size: 10pt;">*${response.message}</i>`);
		//			alert(response.message);
				} else if (repassword == "") { 
					$("#errorMessage").html(`<i id = "errorMessage" style="color:red; font-size: 10pt;"></i>`);
					$("#errorMessage1").html(`<i id = "errorMessage1" style="color:red; font-size: 10pt;">*Ketik ulang password!</i>`);
		//			alert("Isi ulang passwordmu!");
				} else if (yourpassword != repassword) {
					$("#errorMessage").html(`<i id = "errorMessage" style="color:red; font-size: 10pt;"></i>`);
					$("#errorMessage1").html(`<i id = "errorMessage1" style="color:red; font-size: 10pt;">*Password tidak sama</i>`);
		//			alert("Password tidak sama!");
				} */
	})

	//. class
	//# id
	$(".modal").modal("show");

}
//----------------- UBAH PASSWORD ------------------------

//----------------- SAVE PASSWORD ------------------------
function bukaPopupSaveUpdatedPassword(message) {
	console.log("Edit save pw kepencet!");

	//Ganti Title
	$(".modal-title").html(`<h3>Ubah Password</h3>`);

	//Ngisi Modal Bodynya
	$(".modal-body").html(
		`
		<table class="table table-borderless">
			<tr>
				<td>
					<div class="p-1" style="color: royalblue;">
						${message}
					</div>
				</td>
			</tr>
		</table>
		`
	);

	$("#modal-close-btn").attr('onclick', 'location.href = "/login"');
	//. class
	//# id
	$(".modal").modal("show");

	$(".modal").mouseup(function() {
		console.log('Closed')
		location.href = "/login"
	});

}
//----------------- SAVE PASSWORD ------------------------