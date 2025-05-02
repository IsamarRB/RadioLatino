<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Cantantes</title>
</head>
<body>
    <h1>Lista de Cantantes</h1>
    <a href="CantanteServlet?accion=guardar">Agregar Cantante</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Género</th>
                <th>Nacionalidad</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cantante" items="${cantantes}">
                <tr>
                    <td>${cantante.id}</td>
                    <td>${cantante.nombre}</td>
                    <td>${cantante.genero}</td>
                    <td>${cantante.nacionalidad}</td>
                    <td>
                        <a href="CantanteServlet?accion=detalle&id=${cantante.id}">Ver Detalle</a>
                        <a href="CantanteServlet?accion=eliminar&id=${cantante.id}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

