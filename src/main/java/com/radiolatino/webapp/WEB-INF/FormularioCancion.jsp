<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>${cancion != null ? "Editar Canción" : "Agregar Canción"}</title>
</head>
<body>
    <h1>${cancion != null ? "Editar Canción" : "Agregar Nueva Canción"}</h1>
    <form action="CancionServlet" method="post">
        <input type="hidden" name="accion" value="guardar">
        <input type="hidden" name="id" value="${cancion != null ? cancion.id : ''}">
        <p>
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" value="${cancion != null ? cancion.titulo : ''}" required>
        </p>
        <p>
            <label for="genero">Género:</label>
            <input type="text" id="genero" name="genero" value="${cancion != null ? cancion.genero : ''}" required>
        </p>
        <p>
            <label for="cantante">Cantante:</label>
            <input type="text" id="cantante" name="cantante" value="${cancion != null ? cancion.cantante : ''}" required>
        </p>
        <button type="submit">${cancion != null ? "Actualizar" : "Guardar"}</button>
        <a href="CancionServlet?accion=listar">Cancelar</a>
    </form>
</body>
</html>
