<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Édition d'un professeur</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="flex items-center justify-center h-screen" style="background-image: url('assets/images/bg-01.jpg'); background-size: cover;">

    <div class="bg-white p-8 rounded-lg shadow-md w-3/4">

        <div class="flex flex-col items-center justify-center">
            <img src="assets/images/p.jpeg" alt="Logo" class="mx-auto my-4 w-16">
            <h1 class="text-3xl font-extrabold text-gray-800 mb-6">Édition d'un professeur</h1>
        </div>

        <form action="professeurServlet" method="post" class="space-y-4">
            <input type="hidden" name="action" value="editer">
            <input type="hidden" name="id_prof" value="${professeur.id_prof}">

            <div class="grid grid-cols-3 gap-4">
                <div class="col-span-1">
                    <label for="id_etab" class="text-sm font-medium text-gray-600">Etablissement:</label>
                    <select name="id_etab" id="id_etab" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500">
                        <c:forEach items="${etablissements}" var="etablissement">
                            <option value="${etablissement.id_etab}" ${professeur.etablissement != null && professeur.etablissement.id_etab == etablissement.id_etab ? 'selected' : ''}>
                                ${etablissement.nom_etab}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                 <div class="col-span-1">
        <label for="nom_prof" class="text-sm font-medium text-gray-600">Nom du professeur :</label>
        <input type="text" name="nom_prof" value="${professeur.nom_prof}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
    </div>

    <div class="col-span-1">
        <label for="prenom_prof" class="text-sm font-medium text-gray-600">Prénom du professeur :</label>
        <input type="text" name="prenom_prof" value="${professeur.prenom_prof}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
    </div>

    <div class="col-span-1">
        <label for="cin" class="text-sm font-medium text-gray-600">CIN :</label>
        <input type="text" name="cin" value="${professeur.cin}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
    </div>

    <div class="col-span-1">
        <label for="numPRP" class="text-sm font-medium text-gray-600">Numéro PRP :</label>
        <input type="text" name="numPRP" value="${professeur.numPRP}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
    </div>

    <div class="col-span-1">
        <label for="email" class="text-sm font-medium text-gray-600">Email :</label>
        <input type="text" name="email" value="${professeur.email}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
    </div>

    <div class="col-span-1">
        <label for="telephone" class="text-sm font-medium text-gray-600">Téléphone :</label>
        <input type="text" name="telephone" value="${professeur.telephone}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
    </div>

    <div class="col-span-1">
        <label for="specialite" class="text-sm font-medium text-gray-600">Spécialité :</label>
        <input type="text" name="specialite" value="${professeur.specialite}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
    </div>

    <div class="col-span-1">
        <label for="password" class="text-sm font-medium text-gray-600">Mot de passe :</label>
        <input type="password" name="password" value="${professeur.password}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
    </div>
</div>
            <button type="submit" class="mx-auto block w-full bg-gradient-to-r from-blue-500 to-green-500 text-white p-4 rounded-md hover:opacity-90 focus:outline-none focus:ring focus:border-indigo-500">Enregistrer les modifications</button>
        </form>
  </div>

</body>
</html>
