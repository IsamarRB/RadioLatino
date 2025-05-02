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
    <title>Detalle de Canción</title>
</head>
<body>
    <h2>Detalle de la Canción</h2>

    <p><strong>Título:</strong> ${cancion.titulo}</p>
    <p><strong>Cantante:</strong> ${cancion.cantante.nombre}</p>
    <p><strong>Género:</strong> ${cancion.genero.nombre}</p>

    <a href="canciones">Volver al listado</a>
</body>
</html>
