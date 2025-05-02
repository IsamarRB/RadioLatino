<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario Cantante</title>
</head>
<body>
    <h1>${cantante != null ? "Editar Cantante" : "Agregar Cantante"}</h1>
    <form action="CantanteServlet" method="post">
        <input type="hidden" name="accion" value="guardar">
        <input type="hidden" name="id" value="${cantante != null ? cantante.id : ''}">
        <p>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="${cantante != null ? cantante.nombre : ''}" required>
        </p>
        <p>
            <label for="genero">Género:</label>
            <input type="text" id="genero" name="genero" value="${cantante != null ? cantante.genero : ''}" required>
        </p>
        <p>
            <label for="nacionalidad">Nacionalidad:</label>
            <input type="text" id="nacionalidad" name="nacionalidad" value="${cantante != null ? cantante.nacionalidad : ''}" required>
        </p>
        <button type="submit">Guardar</button>
    </form>
    <a href="CantanteServlet?accion=listar">Cancelar</a>
</body>
</html>
