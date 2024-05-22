<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter etablissement</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="flex items-center justify-center h-screen" style="background-image: url('assets/images/bg-01.jpg'); background-size: cover;">

    <div class="bg-white p-8 rounded-lg shadow-md w-96 flex flex-col items-center mx-auto my-auto">
        <img src="assets/images/p.jpeg" alt="Logo" class="mx-auto my-4 w-16">
        <h1 class="text-3xl font-extrabold text-gray-800 mb-6">Ajouter Ecole </h1>

        <form action="etablissementServlet" method="post" class="space-y-4">
            <input type="hidden" name="action" value="ajouter">
            <div>
                <label for="nom_etab" class="text-sm font-medium text-gray-600">Etablissement</label>
                <input type="text" id="" name="nom_etab" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
            </div>
            
            <div>
                <label for="abreviation" class="text-sm font-medium text-gray-600">Abréviation</label>
                <input type="text" id="" name="abreviation" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
            </div>

            <div>
                <label for="ville_etab" class="text-sm font-medium text-gray-600">Ville</label>
                <input type="text" id="" name="ville_etab" class="mt-1 p-2 w-full border-2 border-gray-300 rounded-md focus:outline-none focus:border-indigo-500" required>
            </div>
            
            <button type="submit" class="mx-auto block w-48 h-12 bg-gradient-to-r from-blue-500 to-green-500 text-white p-4 rounded-md hover:opacity-90 focus:outline-none focus:ring focus:border-indigo-500">Ajouter</button>
        </form>
    </div>
</body>

</html>
