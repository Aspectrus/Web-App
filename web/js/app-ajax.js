
$(document).ready(function() {
	$('#Submitbtn').click(function() {
		$.ajax({
			type : 'POST',
			url : 'Submit',
			data : {
				bankcode : $('#BankCode').val(),
				countrycode : $('#CountryCode').val(),
			},
			success : function(responseText) {
				 $('#result').text(responseText);
			},
			fail: function(xhr, textStatus, errorThrown) {
				alert('request failed');
			},
			error : function (request, error) {
				alert(error+' Incorrect input');
			}
		});

	});
});