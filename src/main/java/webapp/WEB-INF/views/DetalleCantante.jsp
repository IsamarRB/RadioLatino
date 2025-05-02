<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalle del Cantante</title>
</head>
<body>
    <h1>Detalle del Cantante</h1>
    <p><strong>ID:</strong> ${cantante.id}</p>
    <p><strong>Nombre:</strong> ${cantante.nombre}</p>
    <p><strong>Género:</strong> ${cantante.genero}</p>
    <p><strong>Nacionalidad:</strong> ${cantante.nacionalidad}</p>
    <a href="CantanteServlet?accion=listar">Volver a la lista</a>
</body>
</html>
