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
    <title>Añadir Canción - RadioLatino</title>
</head>
<body>
    <h2>Añadir Nueva Canción</h2>

    <form action="guardarCancion" method="post">
        Título: <input type="text" name="titulo" required /><br><br>

        Cantante:
        <select name="cantanteId" required>
            <c:forEach var="cantante" items="${cantantes}">
                <option value="${cantante.id}">${cantante.nombre}</option>
            </c:forEach>
        </select><br><br>

        Género:
        <select name="generoId" required>
            <c:forEach var="genero" items="${generos}">
                <option value="${genero.id}">${genero.nombre}</option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Guardar" />
        <a href="canciones">Cancelar</a>
    </form>
</body>
</html>
