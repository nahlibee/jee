<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="modulee.Etablissement" %>
<%@ page import="modulee.EtablissementDAO" %>
<%@ page import="modulee.EtablissementDAOImpl" %>
<%@ page import="modulee.ModuleConnexion" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Créer un Compte Professeur</title>
</head>
<body>

<h2>Créer un Compte Professeur</h2>

<form action="http://localhost:8080/Projet3/authServlet" method="post">
    <input type="hidden" name="action" value="creerCompte">

    <!-- Menu déroulant pour choisir l'établissement -->
    <label for="id_etab">Sélectionnez un établissement:</label>
    <select name="id_etab" required>
        <!-- Boucle pour afficher les établissements disponibles -->
        <%
            try {
                Connection conn = ModuleConnexion.getConnection();
                EtablissementDAO etablissementDAO = new EtablissementDAOImpl(conn);
                List<Etablissement> etablissements = etablissementDAO.getAll();
                for (Etablissement etab : etablissements) {
        %>
            <option value="<%= etab.getId_etab() %>"><%= etab.getNom_etab() %></option>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </select><br>

    <!-- Ajoutez ici les champs du formulaire pour les autres informations du professeur -->
    <label for="nom_prof">Nom:</label>
    <input type="text" name="nom_prof" required><br>

    <label for="prenom_prof">Prénom:</label>
    <input type="text" name="prenom_prof" required><br>

    <label for="cin">CIN:</label>
    <input type="text" name="cin" required><br>

    <label for="numPRP">Numéro PRP:</label>
    <input type="text" name="numPRP" required><br>

    <label for="email">Email:</label>
    <input type="email" name="email" required><br>

    <label for="telephone">Téléphone:</label>
    <input type="text" name="telephone" required><br>

    <label for="specialite">Spécialité:</label>
    <input type="text" name="specialite" required><br>

    <label for="password">Mot de passe:</label>
    <input type="password" name="password" required><br>

    <input type="submit" value="CréerCompte" href="loginp.jsp">
</form>

</body>
</html>
