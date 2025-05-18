<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/includes/head.jsp"/>

<body class="text-light d-flex flex-column justify-content-between min-vh-100" style="max-height: 100vh;">

<jsp:include page="/WEB-INF/jsp/includes/header.jsp"/>


<main role="main" class="flex-grow-1 d-flex flex-column overflow-y-auto">
    <section class="jumbotron text-center flex-grow-1 d-flex flex-column justify-content-between">
        <img src="/images/img404.jpg" alt="404" style="height: 50vh;margin: auto"/>
        <h1 class="display-4" style="color: #4e4e50">404 Not Found</h1>
        <p class="lead">
            <a href="/" class="btn btn-primary">Retour à l'accueil</a>
        </p>
    </section>
</main>


<jsp:include page="/WEB-INF/jsp/includes/footer.jsp"/>

</body>
</html>