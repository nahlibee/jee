<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Demand</title>
</head>
<body>
    <h1>Add Demand</h1>

    <!-- Display a message if it exists -->
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>

    <form action="demandeServlet" method="post">
        <input type="hidden" name="action" value="ajouter">
        
        <!-- Dropdown for selecting a professor -->
        <label for="id_prof">Professor :</label>
        <select name="id_prof">
            <c:forEach items="${professeurs}" var="professeur">
                <option value="${professeur.id_prof}">${professeur.nom}</option>
            </c:forEach>
        </select><br>

        <!-- Dropdown for selecting an offer vacation -->
        <label for="id_offre">Offer Vacation :</label>
        <select name="id_offre">
            <c:forEach items="${offresVacation}" var="offreVacation">
                <option value="${offreVacation.id_offre}">${offreVacation.nom}</option>
            </c:forEach>
        </select><br>

        <!-- Input for the status -->
        <label for="etat_demande">Status :</label>
        <input type="text" name="etat_demande" required><br>

        <input type="submit" value="Add Demand">
    </form>

    <p><a href="demandeServlet?action=list">Back to Demand List</a></p>
</body>
</html>
