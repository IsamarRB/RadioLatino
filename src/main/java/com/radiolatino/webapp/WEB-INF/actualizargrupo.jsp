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

<title>Grupos Musicales - Actualizar grupo musical</title>

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

			<!-- Espacio de enlace de Volver-->
			<div class="form-row">
				<div class="col">
					<a href="ServletEmisora?accion=cargar">Volver</a>
				</div>
			</div>

			<p></p>

			<!-- Espacio para modificar los datos -->
			<div class="form-row">
				<div class="col-3"></div>
				<div class="col-6">
					<div class="form-body">
						<div class="form-group">
							<div>
								<form role="form" id="login-usuarios" autocomplete="off"
									class="credentials" method="" action="">
									<input type="hidden" name="" value="">
									Actualizar grupo musical
									<p></p>

									<div class="input-group">
										<input name="grupoId" type="text" readonly="readonly"
											class="form-control fontAwesome"
											value="">
									</div>
									<p></p>

									<div class="input-group">
										<input name="nombre" type="text"
											class="form-control fontAwesome" value="">
									</div>
									<p></p>
									<div class="input-group">
										<input name="creacion" type="text"
											class="form-control fontAwesome"
											value="">
									</div>
									<p></p>

									<div class="input-group">
										<select name="origen" id="origen"
											class="form-control fontAwesome">
											<option selected>Estados Unidos</option>
											<option>Reino Unido</option>
											<option>Australia</option>
											<option>España</option>
											<option>Alemania</option>
										</select>
									</div>
									<p></p>

									<div class="input-group">
										<select name="genero" id="genero"
											class="form-control fontAwesome">
													<option selected>Heavy Metal</option>
													<option>Hard rock</option>
													<option>Rock</option>
													<option>Folk metal</option>
													<option>Rock andaluz</option>
										</select>
									</div>
									<p></p>

									<span class="input-group-btn">
										<button type="submit" class="btn btn-info">Guardar</button>
									</span> <span class="input-group-btn">
										<button type="reset" class="btn btn-info"
											onclick="">Cancelar</button>
									</span>

								</form>
							</div>

						</div>
					</div>
				</div>

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
