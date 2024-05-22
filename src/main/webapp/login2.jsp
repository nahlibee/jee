<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ajouter un Professeur</title>
</head>
<body>

<h2>Ajouter un Professeur</h2>

<form action="authServlet" method="post">
    <input type="hidden" name="action" value="ajouter">

    <!-- Menu déroulant pour choisir l'établissement -->
   <select name="id_etab" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500">
                        <c:forEach items="${etablissements}" var="etablissement">
                            <option value="${etablissement.id_etab}">${etablissement.nom_etab}</option>
                        </c:forEach>
                    </select>
            
        
  

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

    <input type="submit" value="Ajouter">
</form>

</body>
</html>
