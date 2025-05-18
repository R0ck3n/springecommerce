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
                <div class="card bg-light-subtle text-dark">
                    <div class="card-header text-center">
                        <h4 class="text-dark">Inscription</h4>
                    </div>
                    <div class="card-body">

                        <!-- Formulaire avec affichage des erreurs -->
                        <form:form method="post" action="${pageContext.request.contextPath}/register" modelAttribute="user">

                            <!-- Erreurs globales (ex: @PasswordMatches) -->
                            <form:errors path="*" cssClass="alert alert-danger" element="div"/>

                            <!-- Identifiant -->
                            <div class="form-group mb-3">
                                <form:label path="identifiant">Nom d'utilisateur</form:label>
                                <form:input path="identifiant" cssClass="form-control" placeholder="Entrez votre nom"/>
                                <form:errors path="identifiant" cssClass="text-danger"/>
                            </div>

                            <!-- Mot de passe -->
                            <div class="form-group mb-3">
                                <form:label path="motDePasse">Mot de passe</form:label>
                                <form:password path="motDePasse" cssClass="form-control" placeholder="Entrez votre mot de passe"/>
                                <form:errors path="motDePasse" cssClass="text-danger"/>
                            </div>

                            <!-- Confirmation du mot de passe -->
                            <div class="form-group mb-3">
                                <form:label path="confirmPassword">Vérification du mot de passe</form:label>
                                <form:password path="confirmPassword" cssClass="form-control" placeholder="Répétez votre mot de passe"/>
                                <form:errors path="confirmPassword" cssClass="text-danger"/>
                            </div>

                            <!-- Erreur de logique métier (username déjà pris) -->
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger">${error}</div>
                            </c:if>

                            <!-- Bouton -->
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">S'inscrire</button>
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
