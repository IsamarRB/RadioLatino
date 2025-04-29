<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalle del Podcast</title>
</head>
<body>
    <h1>Detalle del Podcast</h1>
    <p><strong>ID:</strong> ${podcast.id}</p>
    <p><strong>Título:</strong> ${podcast.titulo}</p>
    <p><strong>Descripción:</strong> ${podcast.descripcion}</p>
    <p><strong>URL:</strong> <a href="${podcast.url}" target="_blank">${podcast.url}</a></p>
    <a href="PodcastServlet?accion=listar">Volver a la lista</a>
</body>
</html>
