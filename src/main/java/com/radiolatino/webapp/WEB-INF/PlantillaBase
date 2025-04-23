<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${tituloPagina != null ? tituloPagina : "RadioLatino"}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <header>
        <h1>${tituloPagina != null ? tituloPagina : "RadioLatino"}</h1>
        <c:if test="${not empty usuario}">
            <div class="usuario-info">
                <span>Bienvenido, ${usuario.nombre}</span>
                <a href="RadioLatinoServlet?accion=logout">Cerrar sesión</a>
            </div>
        </c:if>
        <hr>
    </header>

    <main>
        <!-- Aquí irá el contenido dinámico de cada página -->
        <jsp:include page="${contenido}" />
    </main>

    <footer>
        <hr>
        <p style="text-align: center;">&copy; 2025 RadioLatino. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
