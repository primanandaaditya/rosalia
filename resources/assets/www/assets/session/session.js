


$(document).on("pageinit", "#login_page", function() {
    //console.log('pageinit event - login_page only');
	//alert("<");
    $('#login_buttonx').on('click', function(e){
		alert("S");	
		var data = {
						"member_id": $("#memid").val(),
						"password": $("#pass").val()
					};
		//alert(data);	
		$.ajax({
			type:"POST",
			delay: 250,
			dataType: "json",
			data: data,
			url: 'http://117.54.124.38:81/riis-prod/api/v1/member/login/',
			success: function(response){
				//alert(response);
				if(response.data.success){
					//alert("Login Berhasil");
					$.jStorage.set('id', response.data.data.member_urut);
					$.jStorage.set('memberid', response.data.data.member_id);
					$.jStorage.set('namamember', response.data.data.nama_member);
					window.location.href = 'index.html'; 
				}else{
					if(response.data.message){
						alert(response.data.message);
					}else{
						alert("Gagal");
					}
				}
				
			}
		});
        
			

	});

});