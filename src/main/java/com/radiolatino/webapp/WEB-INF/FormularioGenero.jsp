<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario Género</title>
</head>
<body>
    <h1>${genero != null ? "Editar Género" : "Agregar Género"}</h1>
    <form action="GeneroServlet" method="post">
        <input type="hidden" name="accion" value="guardar">
        <input type="hidden" name="id" value="${genero != null ? genero.id : ''}">
        <p>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="
