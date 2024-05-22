<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enregistrement Administrateur</title>
</head>
<body>
    <h2>Enregistrement Administrateur</h2>
    
 

    <%-- Formulaire d'enregistrement --%>
    <form action="login" method="post">
        <input type="hidden" name="action" value="register">
        <label for="login">Login:</label>
        <input type="text" name="login" required><br>
        
        <label for="password">Mot de passe:</label>
        <input type="password" name="password" required><br>
        
        <label for="nom">Nom:</label>
        <input type="text" name="nom" required><br>
        
        <label for="prenom">Prénom:</label>
        <input type="text" name="prenom" required><br>
        
        <input type="submit" value="S'inscrire">
    </form>
    
    <br>
    <a href="login.jsp">Retour à la page de connexion</a>
</body>
</html>
