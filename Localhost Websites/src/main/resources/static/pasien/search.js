//Pake Click
$("#btn-search").click(function() {

	keyword = $("#example-search-input").val();

	refreshList(keyword, null, limit, sorter, ascdesc);

});

$("#btnResetFilter").click(function() {

	refreshList(null, null, limit, sorter, ascdesc);
	$("#example-search-input").val("");
	
});

//Pake Enter
$("#example-search-input").keypress(function(e) {
	console.log('asd')
	if (e.which == 13) {
		$("#btn-search").click();
	}
});