<%@page contentType="text/html"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Información del Usuario</title>
</head>
<body>
    <h1>Información del Usuario</h1>
    
    <% if (request.getAttribute("usuario") != null) { %>
        <p>RUT: <%= ((modelo.Usuario)request.getAttribute("usuario")).getRut() %></p>
        <p>Nombre de Usuario: <%= ((modelo.Usuario)request.getAttribute("usuario")).getUsername() %></p>
        <p>Nombre: <%= ((modelo.Usuario)request.getAttribute("usuario")).getNombre() %></p>
        <p>Apellido: <%= ((modelo.Usuario)request.getAttribute("usuario")).getApellido() %></p>
        <p>Telefono: <%= ((modelo.Usuario)request.getAttribute("usuario")).getTelefono() %></p>
        <p>Fecha de Ingreso: <%= ((modelo.Usuario)request.getAttribute("usuario")).getFechaIngreso() %></p>
    <% } else { %>
        <p>No hay información de usuario disponible.</p>
    <% } %>

</body>
</html>
