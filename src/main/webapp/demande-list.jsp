<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des demandes</title>
</head>
<body>
    <h1>Liste des demandes</h1>

    <c:if test="${not empty demandes}">
        <table border="1">
            <thead>
                <tr>
                    <th>ID Demande</th>
                    <th>Professeur</th>
                    <th>Offre Vacation</th>
                    <th>Date Demande</th>
                    <th>État Demande</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="demande" items="${demandes}">
                    <tr>
                        <td>${demande.id_demande}</td>
                        <td>${demande.professeur.id_prof}</td>
                        <td>${demande.offreVacation.id_offre}</td>
                        <td>${demande.date_demande}</td>
                        <td>${demande.etat_demande}</td>
                        <td>
                            <a href="demandeServlet?action=edit&id_demande=${demande.id_demande}">Éditer</a>
                            |
                            <a href="demandeServlet?action=consulter&id_demande=${demande.id_demande}">Consulter</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <br>

    <a href="demandeServlet?action=ajout">Ajouter une demande</a>
