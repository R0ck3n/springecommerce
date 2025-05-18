<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="includes/head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<body class="text-light d-flex flex-column justify-content-between min-vh-100" style="max-height: 100vh;">

<%@ include file="includes/header.jsp" %>


<main role="main" class="flex-grow-1 d-flex flex-column overflow-y-auto">
    <section class="jumbotron text-center flex-grow-1 d-flex flex-column justify-content-between">
        <img src="/images/img404.jpg" alt="404" style="height: 50vh;margin: auto"/>
        <h1 class="display-4" style="color: #4e4e50">Client introuvable</h1>
        <p class="lead">
            <a href="/" class="btn btn-primary">Retour à l'accueil</a>
        </p>
    </section>
</main>


<%@ include file="includes/footer.jsp" %>

</body>
</html>