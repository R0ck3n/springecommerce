<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="includes/head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<body class="text-light d-flex flex-column justify-content-between min-vh-100" style="max-height: 100vh;">

<%@ include file="includes/header.jsp" %>

<main role="main" class="flex-grow-1 d-flex overflow-y-auto">
    <div class="card shadow-lg d-flex align-items-center flex-grow-1">
        <div class="row g-0 flex-grow-1">
            <div class="col-md-6 h-100 d-flex align-items-center">
                <img src="${produit.image}" class="img-fluid rounded-start w-100" alt="${produit.nom}"
                     style="object-fit: cover; min-height: 69vh;">
            </div>
            <div class="col-md-6 d-flex flex-column justify-content-center p-4 h-100">
                <h2 class="card-title">${produit.nom}</h2>
                <p class="card-text">${produit.description}</p>
                <h4 class="text-primary">${produit.prix} €</h4>

                <security:authorize access="hasRole('CLIENT')">
                <c:choose>
                    <c:when test="${produit.quantite > 0}">
                        <p class="text-success fw-bold">En stock : ${produit.quantite} disponible(s)</p>
                        <form method="post" action="${pageContext.request.contextPath}/panier/ajouter/${produit.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-success mt-2 w-100">Ajouter au panier</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p class="text-danger fw-bold">Indisponible actuellement</p>
                    </c:otherwise>
                </c:choose>
                </security:authorize>
                <security:authorize access="isAnonymous()">
                        <a class="btn btn-success mt-2" href="/login">Se connecter</a>
                </security:authorize>


                <a href="${pageContext.request.contextPath}/produits" class="btn btn-outline-secondary mt-3">Retour aux
                    produits</a>
            </div>
        </div>
    </div>
</main>

<%@ include file="includes/footer.jsp" %>

</body>
</html>
