//Pake Click
$("#btn-search").click(function() {

	var keyword = $("#example-search-input").val();

	refreshList(keyword);

});

//Pake Enter
$("#example-search-input").keypress(function(e) {
	console.log('asd')
	if (e.which == 13) {
		$("#btn-search").click();
	}
});