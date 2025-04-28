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

<title>Busqueda de Eventos</title>
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

			<!-- Espacio de enlace de Cerrar sesión -->
			<div class="form-row">
				<div class="col">
					<a href="">Cerrar sesión</a>
				</div>
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

			<!-- Espacio del botón de Grupos musicales -->
			<div class="form-row">
				<div class="col-1"></div>
				<form role="form" id="grupos-musicales" autocomplete="off">
					<input type="hidden" name="" value="">
					<span class="input-group-btn">
						<button type="submit" class="btn btn-info">Grupos musicales...</button>
					</span>
				</form>
			</div>

			<p></p>

			<!-- Espacio para la búsqueda -->
			<div class="form-row">
				<div class="col-1"></div>
				<div class="col-10">
					<div class="form-body">
						<div class="form-group">
							<div>
								<form role="form" id="busqueda-usuarios" autocomplete="off"
									class="credentials" method="" action="">
									<input type="hidden" name="" value="">
									Búsqueda de Eventos
									<div class="input-group">
										<input name="filtro" type="text"
											class="form-control fontAwesome"
											placeholder="&#xf007; Nombre/Descripción del evento">
										<span class="input-group-btn">
											<button type="submit" class="btn btn-info">Buscar...</button>
										</span>
									</div>
								</form>
							</div>

						</div>
					</div>
				</div>

				<div class="col-1"></div>
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
