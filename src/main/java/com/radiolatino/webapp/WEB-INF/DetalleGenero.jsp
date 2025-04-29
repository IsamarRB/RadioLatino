<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalle del Género</title>
</head>
<body>
    <h1>Detalle del Género</h1>
    <p><strong>ID:</strong> ${genero.id}</p>
    <p><strong>Nombre:</strong> ${genero.nombre}</p>
    <a href="GeneroServlet?accion=listar">Volver a la lista</a>
</body>
</html>
