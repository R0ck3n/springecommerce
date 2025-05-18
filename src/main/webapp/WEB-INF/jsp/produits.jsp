<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="includes/head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<body class="text-light d-flex flex-column justify-content-between min-vh-100" style="max-height: 100vh;">

<%@ include file="includes/header.jsp" %>

<main role="main" class="flex-grow-1  overflow-y-auto ">
    <div class="album py-5 bg-light m-auto">
        <div class="container">

            <div class="row">
                <c:if test="${empty produits}">
                    <div class="col-12 text-center">
                        <div class="alert alert-warning" role="alert">
                            Aucun produit disponible pour le moment.
                        </div>
                    </div>
                </c:if>
                <c:forEach var="produit" items="${produits}">
                    <div class="col-md-4 card-container">
                        <div class="card mb-4 box-shadow">
                            <a href="/produits/${produit.id}" class="btn-thin-border">
                                <img class="card-img-top" src="${produit.image}" alt="${produit.nom}">
                                <div class="card-body">
                                    <h5>${produit.nom}</h5>
                                    <p class="card-text">${produit.description}</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <p>Voir</p>
                                        </div>
                                        <small class="text-muted">${produit.prix} €</small>
                                    </div>

                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</main>

<%@ include file="includes/footer.jsp" %>

</body>
</html>