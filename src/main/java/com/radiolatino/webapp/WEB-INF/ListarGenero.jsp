<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Géneros</title>
</head>
<body>
    <h1>Lista de Géneros</h1>
    <a href="GeneroServlet?accion=guardar">Agregar Género</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="genero" items="${generos}">
                <tr>
                    <td>${genero.id}</td>
                    <td>${genero.nombre}</td>
                    <td>
                        <a href="GeneroServlet?accion=detalle&id=${genero.id}">Ver Detalle</a>
                        <a href="GeneroServlet?accion=eliminar&id=${genero.id}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
