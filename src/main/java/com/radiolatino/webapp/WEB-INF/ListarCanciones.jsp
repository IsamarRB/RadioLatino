<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Canciones</title>
</head>
<body>
    <h1>Lista de Canciones</h1>
    <a href="CancionServlet?accion=nuevo">Agregar Nueva Canción</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Género</th>
                <th>Cantante</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cancion" items="${canciones}">
                <tr>
                    <td>${cancion.id}</td>
                    <td>${cancion.titulo}</td>
                    <td>${cancion.genero}</td>
                    <td>${cancion.cantante}</td>
                    <td>
                        <a href="CancionServlet?accion=detalle&id=${cancion.id}">Ver Detalles</a>
                        <a href="CancionServlet?accion=editar&id=${cancion.id}">Editar</a>
                        <a href="CancionServlet?accion=eliminar&id=${cancion.id}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
