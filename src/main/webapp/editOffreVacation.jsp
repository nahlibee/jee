<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Édition de l'Offre de Vacation</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="flex items-center justify-center h-screen" style="background-image: url('assets/images/bg-01.jpg'); background-size: cover;">

    <div class="bg-white p-8 rounded-lg shadow-md w-96 flex flex-col items-center">
        <!-- Ajouter le logo ici -->
        <img src="assets/images/p.jpeg" alt="Logo" class="mx-auto my-4 w-16">

        <h1 class="text-3xl font-extrabold text-gray-800 mb-6">Modifier Offre Vacation</h1>

        <form action="offreVacationServlet" method="post" class="space-y-4">
            <!-- Champ caché pour l'action -->
            <input type="hidden" name="action" value="editer">

            <!-- Champ caché pour l'ID de l'offre de vacation -->
            <input type="hidden" name="id_offre" value="${offrevacation.id_offre}">

            <!-- Ajoutez d'autres champs pour la modification -->
            <div>
                <label for="id_element" class="text-sm font-medium text-gray-600">Élément :</label>
                <select id="id_element" name="id_element" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500">
                    <c:forEach items="${elements}" var="element">
                        <option value="${element.id_element}" ${element.id_element == offrevacation.element.id_element ? 'selected' : ''}>${element.nom_element}</option>
                    </c:forEach>
                </select>
            </div>

            <div>
                <label for="description" class="text-sm font-medium text-gray-600">Description :</label>
                <input type="text" id="description" name="description" value="${offrevacation.description}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
            </div>

            <div>
                <label for="poste" class="text-sm font-medium text-gray-600">Poste :</label>
                <input type="text" id="poste" name="poste" value="${offrevacation.poste}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
            </div>

            <div>
                <label for="profil" class="text-sm font-medium text-gray-600">Profil :</label>
                <input type="text" id="profil" name="profil" value="${offrevacation.profil}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
            </div>

            <!-- Ajoutez d'autres champs du formulaire au besoin -->

            <!-- Bouton de soumission -->
            <button type="submit" class="mx-auto block w-48 h-12 bg-gradient-to-r from-blue-500 to-green-500 text-white p-4 rounded-md hover:opacity-90 focus:outline-none focus:ring focus:border-indigo-500">Enregistrer les modifications</button>
        </form>

        <p class="mt-4"><a href="offreVacationServlet?action=list" class="text-blue-500">Retour à la liste des offres</a></p>
    </div>

</body>
</html>
