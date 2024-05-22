<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Modifier un élément</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="flex items-center justify-center h-screen" style="background-image: url('assets/images/bg-01.jpg'); background-size: cover;">

    <div class="bg-white p-8 rounded-lg shadow-md w-96 flex flex-col items-center">
        <!-- Ajouter le logo ici -->
        <img src="assets/images/p.jpeg" alt="Logo" class="mx-auto my-4 w-16">

        <h1 class="text-3xl font-extrabold text-gray-800 mb-6">Modifier Element</h1>

        <form action="elementServlet" method="post" class="space-y-4">
            <input type="hidden" name="action" value="editer">
            <input type="hidden" name="id_element" value="${element.id_element}">

            <div>
                <label for="nom_element" class="text-sm font-medium text-gray-600">Nom de l'élément:</label>
                <input type="text" name="nom_element" value="${element.nom_element}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
            </div>

            <div>
                <label for="volumehoraire_element" class="text-sm font-medium text-gray-600">Volume horaire de l'élément:</label>
                <input type="number" name="volumehoraire_element" value="${element.volumehoraire_element}" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
            </div>

            <div>
                <label for="id_module" class="text-sm font-medium text-gray-600">Module:</label>
                <select name="id_module" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
                    <c:forEach items="${modules}" var="module">
                        <option value="${module.id_module}" ${element.module != null && element.module.id_module == module.id_module ? 'selected' : ''}>
                            ${module.nom_module}
                        </option>
                    </c:forEach>
                </select>
            </div>

           <button type="submit" class="bg-gradient-to-r from-blue-500 to-green-500 text-white p-2 rounded-md hover:opacity-90 focus:outline-none focus:ring focus:border-indigo-500">Enregistrer les modifications</button>
        </form>

           </div>

</body>
</html>
