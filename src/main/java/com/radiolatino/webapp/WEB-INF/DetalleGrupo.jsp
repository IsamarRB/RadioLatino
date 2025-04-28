<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- CSS -->
<link rel="stylesheet" href="./css/newportal.css">

<title>Grupos Musicales - Detalle</title>

</head>

<body class="fondo">

	<div class="container">
		<div class="signup-form-container">

			<!-- Espacio de cabecera -->
			<div class="form-header">
				<h3 class="registration">
					<i class="fa fa-user"></i>Sistema de Gestión de Emisora Local
				</h3>
			</div>

			<!-- Espacio de enlace de Volver -->
			<div class="form-row">
				<div class="col">
					<a href="">Volver</a>
				</div>
			</div>

			<p></p>

			<!-- Espacio para los datos del grupo -->
			<div class="form-row">
				<div class="col-1"></div>
				<div class="col-10">
					<div class="form-body">
						<div class="form-group">
							<div>
								<form role="form" id="datos-usuario" autocomplete="off"
									class="credentials">
									<div class="input-group">
										Id del grupo:
									</div>
									<div class="input-group">
										Nombre del grupo:
									</div>
									<div class="input-group">
										Año de creación del grupo:
									</div>
									<div class="input-group">
										Lugar de origen del grupo:
									</div>
									<div class="input-group">
										Género musical del grupo:
									</div>
								</form>
							</div>

						</div>
					</div>

				</div>

				<div class="col-1"></div>
			</div>

			<hr class="separador">

			<!-- Espacio para la lista de componentes -->
			<div class="release">
				<span class="welcome">LISTADO DE COMPONENTES DEL GRUPO MUSICAL</span>
				<div class="form-row tableHeaderAll">
					<div class="col">Nombre del componente</div>
					<div class="col">Instrumento</div>
				</div>
				<c:forEach var="componente" items="${listaComponentes}">
					<div class="form-row tableHeaderUser" style="font-size: small;">
						<div class="col">${componente.nombre}</div>
						<div class="col">${componente.instrumento}</div>
					</div>
				</c:forEach>

				<div class="col-11"></div>

			</div>

		</div>
	</div>
</body>

</html>
