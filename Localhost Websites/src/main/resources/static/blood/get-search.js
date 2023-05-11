//------------- GET ALL CATEGORIES -------------------------
function searchBloodAPI(code) {
	console.log(code);
	return $.ajax({
		"url": "/api/blood/search?code=" + code,
		"method": "GET",
		"async": false
	});
}

function refreshList(code) {
	
	if (code == null) {
		code = "";
	}

	var response = searchBloodAPI(code).responseJSON;
	console.log(response);

	var list = response.data;

	$("#content-tbody").html("");
	for (i = 0; i < list.length; i++) {
		$("#content-tbody").append(
			`
			<tr class="table-active">
				<td style="text-align: center; width: 30%;">${list[i].code}</td>
				<td style="text-align: center; width: 50%;">${list[i].description}</td>
				<td style="text-align: center; width: 20%;">
					<button class="btn btn-warning" 
					onclick="bukaPopupEdit(
							'${list[i].code}',
							'${list[i].description}',
							${list[i].id})">
						<i class="fa-solid fa-pen-to-square" color="white"></i>
					</button>
					<button class="btn btn-danger" 
					onclick="bukaPopupDelete(
							'${list[i].code}',
							'${list[i].description}',
							${list[i].id})">
						<i class="fa-solid fa-trash" color="white"></i>
					</button>
				</td>
			</tr>
			`
		)
	}
}

refreshList();
//----------------------------------------------------------