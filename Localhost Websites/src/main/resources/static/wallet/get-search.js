//------------- GET ALL CATEGORIES -------------------------
function searchNominalAPI(nominal) {
	console.log(nominal);
	return $.ajax({
		"url": "/api/nominal/search?nominal=" + nominal,
		"method": "GET",
		"async": false
	});
}

function refreshList(nominal) {
	
	if (nominal == null) {
		nominal = "";
	}

	var response = searchNominalAPI(nominal).responseJSON;
	console.log(response);

	var list = response.data;
	
	$("#content-tbody").html("");
	for (i = 0; i < list.length; i++) {
		$("#content-tbody").append(
			`
			<tr class="table-active">
				<td width="70%" align="center">${new Intl.NumberFormat("id-ID", {style: "currency", currency: "IDR"}).format(list[i].nominal)}</td>
				<td width="30%" align="center">
					<button class="btn btn-warning" 
					onclick="bukaPopupEdit(
							${list[i].nominal},
							${list[i].id})">
						<i class="fa-solid fa-pen-to-square" color="white"></i>
					</button>
					<button class="btn btn-danger" 
					onclick="bukaPopupDelete(
							${list[i].nominal},
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