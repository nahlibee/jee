<%@ page import="modulee.Professeur" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Professor Profile</title>
</head>
<body>

<%
    // Récupérer le professeur à partir de la session
    Professeur professeur = (Professeur) session.getAttribute("professeur");

    // Vérifier si le professeur est connecté
    if (professeur != null) {
%>
    <h2>Welcome, <%= professeur.getNom_prof() %> <%= professeur.getPrenom_prof() %></h2>
    <p>Email: <%= professeur.getEmail() %></p>
    <p>CIN: <%= professeur.getCin() %></p>
    <p>Numéro PRP: <%= professeur.getNumPRP() %></p>
    <p>Téléphone: <%= professeur.getTelephone() %></p>
    <p>Spécialité: <%= professeur.getSpecialite() %></p>
    <!-- Ajoutez d'autres champs au besoin -->

    <!-- Vous pouvez ajouter des liens ou des boutons pour d'autres actions -->

<%
    } else {
        // Si le professeur n'est pas connecté, rediriger vers la page de connexion
        response.sendRedirect("loginp.jsp");
    }
%>

</body>
</html>
