<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="includes/head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<body class="text-dark d-flex flex-column justify-content-between min-vh-100" style="max-height: 100vh;">

<%@ include file="includes/header.jsp" %>

<main role="main" class="bg-light flex-grow-1 overflow-y-auto responsive-reverse">
    <div class="container py-5 bg-light">
        <h2 class="text-dark mb-4">Mes Commandes</h2>
        <p class="text-dark">Bienvenue dans votre espace <strong>${client.identifiant}</strong></p>

        <c:if test="${empty client.commandes}">
            <div class="alert alert-warning">Aucune commande enregistrée pour le moment.</div>
        </c:if>

        <c:forEach var="commande" items="${client.commandes}">
            <div class="card mb-4 shadow-sm">
                <div class="card-header bg-dark bg-gradient text-white">
                    <strong>Commande #${commande.id}</strong> – Date : ${commande.date} – Statut : ${commande.statut}
                </div>
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover m-0">
                            <thead class="table-info">
                            <tr>
                                <th>Image</th>
                                <th>Produit</th>
                                <th>Quantité</th>
                                <th>Prix unitaire</th>
                                <th>Montant</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="ligne" items="${commande.lignesDeCommande}">
                                <tr>
                                    <td>
                                        <img src="/images/${ligne.imageProduit}" alt="${ligne.nomProduit}" width="80" class="img-thumbnail" />
                                    </td>
                                    <td>${ligne.nomProduit}</td>
                                    <td>${ligne.quantite}</td>
                                    <td>${ligne.prixUnitaire} €</td>
                                    <td>${ligne.montant} €</td>
                                </tr>
                            </c:forEach>
                            <tr class="table-light">
                                <td colspan="3"><strong>Total commande : ${commande.montantTotal} €</strong></td>
                                <td colspan="2">
                                    <c:if test="${commande.statut eq 'EN_COURS'}">
                                        <form method="post" action="/commande/regler/${commande.id}" class="text-end p-3">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <input type="submit" value="Régler la commande" class="btn btn-success" />
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</main>

<%@ include file="includes/footer.jsp" %>
</body>
</html>
