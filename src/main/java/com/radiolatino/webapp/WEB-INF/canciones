<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    com.radiolatino.model.Usuario usuario = (com.radiolatino.model.Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>RadioLatino - Lista de Canciones</title>
</head>
<body>
    <h2>Listado de Canciones</h2>

    <a href="nuevaCancion.jsp">Añadir nueva canción</a> |
    <a href="buscarEventos.jsp">Volver</a> |
    <a href="logout">Cerrar sesión</a>

    <table border="1">
        <thead>
            <tr>
                <th>Título</th>
                <th>Cantante</th>
                <th>Género</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${canciones}">
                <tr>
                    <td>${c.titulo}</td>
                    <td>${c.cantante.nombre}</td>
                    <td>${c.genero.nombre}</td>
                    <td>
                        <a href="cancion?accion=detalle&id=${c.id}">Detalle</a> |
                        <a href="cancion?accion=editar&id=${c.id}">Editar</a> |
                        <a href="cancion?accion=eliminar&id=${c.id}" onclick="return confirm('¿Seguro que quieres eliminar esta canción?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
