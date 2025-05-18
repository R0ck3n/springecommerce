<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%@ include file="includes/head.jsp" %>

<body class="text-light d-flex flex-column justify-content-between min-vh-100" style="max-height: 100vh;">

<%@ include file="includes/header.jsp" %>


<main role="main" class="flex-grow-1 d-flex flex-column overflow-y-auto">
    <section class="jumbotron text-center header-picture flex-grow-1 d-flex flex-column justify-content-between">
        <div class="container">
            <h1 class="jumbotron-heading">🏄‍♂️🏄‍♂️Planches, guitares et hippies !🎸🎸</h1>
        </div>
        <div class="container d-flex justify-content-center ">
            <a href="${pageContext.request.contextPath}/produits" class="special-btn">Voir nos produits</a>
        </div>
    </section>

</main>


<%@ include file="includes/footer.jsp" %>
</body>
</html>