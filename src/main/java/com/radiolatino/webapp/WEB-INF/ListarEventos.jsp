<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Eventos</title>
</head>
<body>
    <h1>Lista de Eventos</h1>
    <a href="EventoServlet?accion=nuevo">Agregar Nuevo Evento</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Lugar</th>
                <th>Fecha</th>
                <th>Organizador</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="evento" items="${eventos}">
                <tr>
                    <td>${evento.id}</td>
                    <td>${evento.nombre}</td>
                    <td>${evento.lugar}</td>
                    <td>${evento.fecha}</td>
                    <td>${evento.organizador}</td>
                    <td>
                        <a href="EventoServlet?accion=detalle&id=${evento.id}">Ver Detalles</a>
                        <a href="EventoServlet?accion=editar&id=${evento.id}">Editar</a>
                        <a href="EventoServlet?accion=eliminar&id=${evento.id}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
