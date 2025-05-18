<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="includes/head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<body class="text-light d-flex flex-column justify-content-between min-vh-100" style="max-height: 100vh;">

<%@ include file="includes/header.jsp" %>


<main role="main" class="flex-grow-1 d-flex flex-column justify-content-center align-items-center bg-login">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <div class="card bg-light-subtletext-white">
                    <div class="card-header text-center">
                        <h4>Connexion</h4>
                    </div>
                    <div class="card-body">
                        <form:form method="post" action="${pageContext.request.contextPath}/login" modelAttribute="user">
                            <div class="form-group mb-3">
                                <form:label path="username">Nom d'utilisateur</form:label>
                                <form:input path="username" cssClass="form-control" placeholder="Entrez votre nom" required="required"/>
                            </div>
                            <div class="form-group mb-3">
                                <form:label path="password">Mot de passe</form:label>
                                <form:password path="password" cssClass="form-control" placeholder="Entrez votre mot de passe" required="required"/>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-success">Se connecter</button>
                            </div>
                            <div class="d-grid">
                                pas encore inscrit ? <a href="${pageContext.request.contextPath}/register">Inscrivez-vous</a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

    <%@ include file="includes/footer.jsp" %>
</body>
</html>