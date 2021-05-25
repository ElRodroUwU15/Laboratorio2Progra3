<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/estilostablas.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js&quot"
	; integrity="
	sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
	crossorigin="anonymous"></script>
</head>
<body>
<%
	HttpSession sesion = (HttpSession) request.getSession();
	String usuSession = String.valueOf(sesion.getAttribute("usuario"));

	if (usuSession.equals(null) || usuSession.equals("null")) {

		response.sendRedirect("index.jsp");
	}
	%>
	<script type="text/javascript">
		$(document).ready(function() {
			$.post('ControllerTipos', {
			}, function(response) {
				let datos = JSON.parse(response);
				
				console.log(datos);
				
				var tabla = document.getElementById('tablaTipos');
				for(let item of datos){
					tabla.innerHTML += `
					<tr>
						<td>${item.idTipos_vuelo}</td>
						<td>${item.Tipo}</td>
						<td>${item.PorcentajeDesc}%</td>
						<td><a class="btn btn-danger" href="ControllerTipos?idTipos=${item.idTipos_vuelo}&Eliminar=btne">Eliminar</a>
						<a href="addtipo.jsp?Id=${item.idTipos_vuelo}&Tipo=${item.Tipo}&Porcentaje=${item.PorcentajeDesc}" class="btn btn-warning"> Actualizar</a>
						</td>
					</tr>
					`
				}
			});
		});
		</script>
<header>
	<input type="checkbox" id="btn-menu">
	<label for="btn-menu"><img alt="" src="IMG/menu.png" height="30px" width="30px"></label>
		
		<nav class="menu">
			<ul>
				<li><a href="menu.jsp">Inicio</a></li>
				<li><a href="aeropuerto.jsp">Aeropuertos</a></li>
				<li><a href="company.jsp">Compa�ias</a></li>
				<li><a href="avion.jsp">Aviones</a></li>
				<li><a href="tipos.jsp">Tipos de Vuelo</a></li>
				<li><a href="vuelo.jsp">Vuelos</a></li>
				<li><a href="cerrar.jsp">Cerrar Sesion</a></li>
			</ul>
	</nav>
</header>
	<div class="containertabla">
		<img class="icono" src="IMG/icono-avion-viaje_18591-39662.jpg"
			alt="Logo avion">
		<h1 align="center">Tipos de Vuelo</h1>
		<table>
			<thead>
				<th>ID</th>
				<th>Nombre</th>
				<th>Descuento</th>
				<th>Acciones</th>
			</thead>
			<tbody  id="tablaTipos">

			</tbody>
		</table>
		<div align="center">
			<a href="addtipo.jsp" type="button" class="btn btn-primary"><b>Agregar</b></a>
		</div>
	</div>
</body>
</html>