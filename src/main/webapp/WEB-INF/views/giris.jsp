<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>
	
	<script src = "https://code.jquery.com/jquery-3.3.1.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>


<script>
	$(document).ready(function() {
		$('#btnAjax').click(function(){
			// ajax başlıyor
			var kname = $('#ajaxDiv > input[name="kname"]').val();
			var ksurname = $('#ajaxDiv > input[name="ksurname"]').val();
			var kmail = $('#ajaxDiv > input[name="kmail"]').val();
			var kpassword = $('#ajaxDiv > input[name="kpassword"]').val();

			$.ajax({
				url : '<s:url value="/ajaxKayit"></s:url>',
				type: 'post',
				data : { 'kname':kname, 'ksurname':ksurname,'kmail':kmail, 'kpassword':kpassword  },
				success : function(data) {
					$('#cevap').text(data);
				}
			});
			
			
		});
	})

</script>

<body>

	<div class="container">

		<h1>User Insert</h1>

		<form action='<s:url value="/hiberInsert"></s:url>' method="post"
			autocomplete="off">
			<input type="text" name="kname" placeholder="Adınız" /> <input
				type="text" name="ksurname" placeholder="Soyadınız" /> <input
				type="text" name="kmail" placeholder="Mail" /> <input
				type="password" name="kpassword" placeholder="Şifre" />
			<button type="submit">Kayıt Yap</button>
		</form>

		<hr>
		<h1>ajax Insert</h1>
		<div id="ajaxDiv">
			<input type="text" name="kname" placeholder="Adınız" /> <input
				type="text" name="ksurname" placeholder="Soyadınız" /> <input
				type="text" name="kmail" placeholder="Mail" /> <input
				type="password" name="kpassword" placeholder="Şifre" />
			<button id = "btnAjax">Kayıt Yap</button>
			<div id = "cevap"></div>
		</div>

	</div>

</body>
</html>