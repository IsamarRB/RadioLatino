<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login - RadioLatino</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h2>Iniciar Sesión</h2>

        <!-- Mostrar error si lo hay -->
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form action="app" method="post">
            <input type="hidden" name="accion" value="login">

            <label for="correo">Correo electrónico</label>
            <input type="text" id="correo" name="correo" required>

            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Entrar">
        </form>
    </div>
</body>
</html>

