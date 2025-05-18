<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="includes/head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<body class="text-light d-flex flex-column justify-content-between min-vh-100" style="max-height: 100vh;">

<%@ include file="includes/header.jsp" %>

<main role="main" class="flex-grow-1 d-flex flex-column align-items-center justify-content-start bg-light p-5">

    <div class="container bg-white rounded shadow p-4" style="max-width: 600px;">
        <h2 class="text-dark mb-4">Détail du client</h2>

        <table class="table table-striped">
            <tr>
                <th scope="row">ID :</th>
                <td>${client.id}</td>
            </tr>
            <tr>
                <th scope="row">Identifiant :</th>
                <td>${client.identifiant}</td>
            </tr>
            <tr>
                <th scope="row">Mot de passe :</th>
                <td>${client.motDePasse}</td>
            </tr>
        </table>

        <a href="/admin" class="btn btn-secondary mt-3">Retour à la liste</a>
    </div>

</main>
<%@ include file="includes/footer.jsp" %>

</body>
</html>
