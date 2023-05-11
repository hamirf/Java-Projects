$("#limit1").click(function() {
	limit = $("#limit1").text();
	console.log("Data limited by: "+ limit);
	refreshList(keyword, null, limit, sorter, ascdesc);
	$("#dropdownMenuButton").html(`${limit}`);
})

$("#limit2").click(function() {
	limit = $("#limit2").text();
	console.log("Data limited by: "+ limit);
	refreshList(keyword, null, limit, sorter, ascdesc);
	$("#dropdownMenuButton").html(`${limit}`);
})

$("#limit3").click(function() {
	limit = $("#limit3").text();
	console.log("Data limited by: "+ limit);
	refreshList(keyword, null, limit, sorter, ascdesc);
	$("#dropdownMenuButton").html(`${limit}`);
})
