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

<title>Grupos Musicales</title>

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

			<!-- Espacio de enlace de Volver y Cerrar sesión -->
			<div class="form-row">
				<div class="col">
					<a href="">Volver</a>
				</div>
				<div class="col-11"><a href="">Cerrar sesión</a></div>
			</div>

			<p></p>

			<!-- Espacio con los datos del usuario -->
			<div class="form-row">
				<div class="col-1"></div>
				<div class="col-10">
					<div class="form-body">
						<div class="form-group">
							<div>
								<form role="form" id="datos-usuario" autocomplete="off"
									class="credentials">
									<div class="input-group">
										Nombre:
									</div>
									<div class="input-group">
										Email:
									</div>
									<div class="input-group">
										Dirección:
									</div>
									<div class="input-group">
										Teléfono:
									</div>

								</form>
							</div>

						</div>
					</div>

				</div>

				<div class="col-1"></div>
			</div>

			<p></p>

			<hr class="separador">
			<!-- Espacio para la lista de Grupos musicales -->
			<div class="release">
				<span class="welcome">LISTADO DE GRUPOS MUSICALES</span>
				<div class="form-row tableHeaderAll">
					<div class="col">Id</div>
					<div class="col-2">Nombre del grupo</div>
					<div class="col">Año de creación</div>
					<div class="col-2">Origen</div>
					<div class="col-2">Género musical</div>
					<div class="col"></div>
					<div class="col"></div>
					<div class="col"></div>
				</div>

				<div class="form-row tableHeaderUser" style="font-size: small;">
					<div class="col"></div>
					<div class="col-2"></div>
					<div class="col"></div>
					<div class="col-2"></div>
					<div class="col-2"></div>
					<div class="col">
						<a href="">	Detalle </a>
					</div>
					<div class="col">
						<a href="">	Modificar </a>
					</div>
					<div class="col">
						<a href="">	Eliminar </a>
					</div>
				</div>

				<div class="col-11"></div>

			</div>

			<p></p>

			<!-- Espacio para el botón de Añadir grupo -->
			<div class="form-row">
				<div class="col-1"></div>
				<form role="form" id="grupos-musicales" autocomplete="off">
					<input type="hidden" name="" value="">
					<span class="input-group-btn">
						<button type="submit" class="btn btn-info">Añadir grupo</button>
					</span>
				</form>
			</div>

			<!-- Espacio para el mensaje -->
			<div class="form-row">
				<div class="col error">
					<c:out value="${requestScope.error}"></c:out>
				</div>
			</div>

		</div>
	</div>

</body>

</html>
