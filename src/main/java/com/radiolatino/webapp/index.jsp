<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login - RadioLatino</title>
</head>
<body>
<h2>Iniciar Sesión</h2>
<form action="UsuarioServlet" method="post">
    <input type="hidden" name="accion" value="login">
    <label>Usuario: <input type="text" name="usuario"/></label><br>
    <label>Contraseña: <input type="password" name="password"/></label><br>
    <button type="submit">Entrar</button>
</form>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
</body>
</html>
