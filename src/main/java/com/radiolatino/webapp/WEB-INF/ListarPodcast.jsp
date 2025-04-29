<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Podcasts</title>
</head>
<body>
    <h1>Lista de Podcasts</h1>
    <a href="PodcastServlet?accion=guardar">Agregar Podcast</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Descripción</th>
                <th>URL</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="podcast" items="${podcasts}">
                <tr>
                    <td>${podcast.id}</td>
                    <td>${podcast.titulo}</td>
                    <td>${podcast.descripcion}</td>
                    <td><a href="${podcast.url}" target="_blank">Escuchar</a></td>
                    <td>
                        <a href="PodcastServlet?accion=detalle&id=${podcast.id}">Ver Detalle</a>
                        <a href="PodcastServlet?accion=eliminar&id=${podcast.id}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
