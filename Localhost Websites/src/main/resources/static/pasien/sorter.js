$("#sorter").change(function() {
	
	sorter = $("#sorter").val();
	console.log("Sorted by: " + sorter);
	refreshList(keyword, null, limit, sorter, ascdesc);
});

/*	if ($("#sorter").val() == "name") {
		sorter = $("#sorter").val();
		console.log("Sorted by: " + sorter);
		refreshList(keyword, null, limit, sorter, ascdesc);
		$("#sorter").html(`
		<option id="nameSorter" value="name" selected>Nama</option>
		<option id="ccSorter" value="cc">Chat</option>
		<option id="acSorter" value="ac">Janji</option>
		`)
	} else if ($("#sorter").val() == "cc") {
		sorter = $("#sorter").val();
		console.log("Sorted by: " + sorter);
		refreshList(keyword, null, limit, sorter, ascdesc);
		$("#sorter").html(`
		<option id="nameSorter" value="name">Nama</option>
		<option id="ccSorter" value="cc" selected>Chat</option>
		<option id="acSorter" value="ac">Janji</option>
		`)
	} else if ($("#sorter").val() == "ac") {
		sorter = $("#sorter").val();
		console.log("Sorted by: " + sorter);
		refreshList(keyword, null, limit, sorter, ascdesc);
		$("#sorter").html(`
		<option id="nameSorter" value="name">Nama</option>
		<option id="ccSorter" value="cc">Chat</option>
		<option id="acSorter" value="ac" selected>Janji</option>
		`)
	}
}); */

$("#pasienSorter").click(function() {

	if ($("#ascdescSort").html() == "A-Z") {
		ascdesc = "desc";
		console.log("Sorted in: " + ascdesc);
		refreshList(keyword, null, limit, sorter, ascdesc);
		$("#ascdescSort").html(`Z-A`);
	} else if ($("#ascdescSort").html() == "Z-A") {
		ascdesc = "asc";
		console.log("Sorted in: " + ascdesc);
		refreshList(keyword, null, limit, sorter, ascdesc);
		$("#ascdescSort").html(`A-Z`);
	}
});