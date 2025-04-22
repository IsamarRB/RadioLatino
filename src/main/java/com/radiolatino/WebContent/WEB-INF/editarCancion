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
    <title>Editar Canción</title>
</head>
<body>
    <h2>Editar Canción</h2>

    <form action="actualizarCancion" method="post">
        <input type="hidden" name="id" value="${cancion.id}" />

        Título: <input type="text" name="titulo" value="${cancion.titulo}" required /><br><br>

        Cantante:
        <select name="cantanteId">
            <c:forEach var="cantante" items="${cantantes}">
                <option value="${cantante.id}"
                    <c:if test="${cantante.id == cancion.cantante.id}">selected</c:if>>
                    ${cantante.nombre}
                </option>
            </c:forEach>
        </select><br><br>

        Género:
        <select name="generoId">
            <c:forEach var="genero" items="${generos}">
                <option value="${genero.id}"
                    <c:if test="${genero.id == cancion.genero.id}">selected</c:if>>
                    ${genero.nombre}
                </option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Actualizar" />
        <a href="canciones">Cancelar</a>
    </form>
</body>
</html>
