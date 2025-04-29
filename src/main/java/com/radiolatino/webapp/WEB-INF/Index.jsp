<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesión</title>
</head>
<body>
    <h1>Inicio de Sesión</h1>
    <form action="app" method="post">
        <input type="hidden" name="accion" value="login">
        <p>
            <label for="correo">Correo:</label>
            <input type="email" id="correo" name="correo" required>
        </p>
        <button type="submit">Iniciar Sesión</button>
    </form>
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
</body>
</html>