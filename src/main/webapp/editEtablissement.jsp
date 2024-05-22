<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier un établissement</title>
</head>
<body>
    <h1>Modifier un établissement</h1>
    
    <form action="etablissementServlet" method="post">
        <input type="hidden" name="action" value="editer">
        <input type="hidden" name="id_etab" value="${etablissement.id_etab}">
        Nom du établissement: <input type="text" name="nom_etab" value="${etablissement.nom_etab}" required><br>
        Abbréviation: <input type="text" name="abreviation" value="${etablissement.abreviation}" required><br>
        ville: <input type="text" name="ville_etab" value="${etablissement.ville_etab}" required><br>
        <input type="submit" value="Enregistrer les modifications">
    </form>
    
    <br>
</body>
</html>
