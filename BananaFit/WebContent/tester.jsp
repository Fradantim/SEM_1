<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<html lang="en">
<head> 
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.css">
	<title>Tester</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-notify.js"></script>
	<script type="text/javascript">
		function callPostServletStartUp() {
			var url='ServletStartUp';
			$.blockUI({ message: '<center><img src="gifs/banana.gif" /><br>Aguanta...</center>' });
			console.log('boton apretado');	
			$.ajax({
		        url: url,
		        type: "post",
		        data: {}
		    	}).done(function (respuesta){
					$.unblockUI();
					$.notify({message: 'Todo ok!'},{type: 'success'});
				}).fail(function(data){
					$.unblockUI();
					var responseJsonObj = JSON.parse(data.responseText);
					$.notify({message: responseJsonObj.errorMessage},{type: 'danger'});
				});
		}
	</script>
</head>
<jsp:include page="jsp/bannerSuperior.jsp"></jsp:include>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<p style="padding:10px;">No rompas nada</p>
		
			<input id="buttonStartUp" type="button" value="StartUp" class="btn btn-info" onclick="callPostServletStartUp();" />
			
		</div>	
	</div>
</body>
</html>