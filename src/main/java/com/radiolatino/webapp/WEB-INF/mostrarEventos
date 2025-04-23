<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Resultados de Búsqueda</title>
</head>
<body>
    <h2>Resultados para: "${criterio}"</h2>

    <c:if test="${not empty canciones}">
        <h3>Canciones</h3>
        <ul>
            <c:forEach var="c" items="${canciones}">
                <li>${c.titulo} - ${c.cantante.nombre} (${c.genero.nombre})</li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${not empty podcasts}">
        <h3>Podcasts</h3>
        <ul>
            <c:forEach var="p" items="${podcasts}">
                <li>${p.titulo} (${p.genero.nombre})</li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty canciones and empty podcasts}">
        <p>No hay resultados.</p>
    </c:if>

    <br/>
    <a href="buscarEventos.jsp">Volver</a> |
    <a href="logout">Cerrar sesión</a>
</body>
</html>