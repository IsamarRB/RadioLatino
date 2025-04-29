<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Grupos Musicales</title>
</head>
<body>
    <h1>Lista de Grupos Musicales</h1>
    <a href="GrupoServlet?accion=nuevo">Agregar Nuevo Grupo</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Género</th>
                <th>Integrantes</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="grupo" items="${grupos}">
                <tr>
                    <td>${grupo.id}</td>
                    <td>${grupo.nombre}</td>
                    <td>${grupo.genero}</td>
                    <td>${grupo.integrantes}</td>
                    <td>
                        <a href="GrupoServlet?accion=detalle&id=${grupo.id}">Ver Detalles</a>
                        <a href="GrupoServlet?accion=editar&id=${grupo.id}">Editar</a>
                        <a href="GrupoServlet?accion=eliminar&id=${grupo.id}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
