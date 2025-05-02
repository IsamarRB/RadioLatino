<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio</title>
</head>
<body>
    <h1>Bienvenido a Radio Latino</h1>
    <nav>
        <ul>
            <li><a href="CancionServlet?accion=listar">Gestión de Canciones</a></li>
            <li><a href="EventoServlet?accion=listar">Gestión de Eventos</a></li>
            <li><a href="GrupoServlet?accion=listar">Gestión de Grupos Musicales</a></li>
            <li><a href="GeneroServlet?accion=listar">Gestión de Géneros</a></li>
            <li><a href="PodcastServlet?accion=listar">Gestión de Podcasts</a></li>
        </ul>
    </nav>
</body>
</html>
