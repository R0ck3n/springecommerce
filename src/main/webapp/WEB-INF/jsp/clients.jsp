<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="includes/head.jsp" %>
<%@ taglib prefix="utils" uri="http://example.com/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<body class="text-light d-flex flex-column justify-content-between min-vh-100" style="max-height: 100vh;">

<%@ include file="includes/header.jsp" %>

<main role="main"
      class="flex-grow-1 d-flex flex-row overflow-y-auto admin-content-container">


    <div class="container py-5 bg-light">
        <h2 class="text-dark mb-4">Liste des clients</h2>

        <c:if test="${empty clients}">
            <div class="alert alert-warning" role="alert">
                Aucun client trouvé.
            </div>
        </c:if>

        <c:if test="${not empty clients}">
            <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped bg-white text-dark">
                    <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Identifiant</th>
                        <th>Mot de passe</th>
                        <th>Détail</th> <!-- Nouvelle colonne -->
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="client" items="${clients}">
                        <tr>
                            <td>${client.id}</td>
                            <td>${client.identifiant}</td>
                            <td>${utils:repeatStars(client.motDePasse)}</td>
                            <td>
                                <a href="/admin/${client.id}" class="btn btn-sm btn-outline-primary">Voir</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>

    <div class="container py-5 bg-light">
        <h4 class="text-dark">Test des API REST Clients</h4>

        <!-- Formulaire pour chercher un client par identifiant -->
        <div class="mb-3">
            <label for="identifiantInput" class="form-label">Rechercher par identifiant :</label>
            <input type="text" id="identifiantInput" class="form-control" placeholder="Ex: client1">
            <button class="btn btn-sm btn-success mt-2" onclick="getClientByIdentifiant()">Chercher</button>
        </div>

        <!-- Bouton pour récupérer tous les clients -->
        <div class="mb-3">
            <button class="btn btn-sm btn-primary" onclick="getAllClients()">Voir tous les clients</button>
        </div>

        <!-- Zone pour afficher la réponse -->
        <div class="mt-4">
            <h5 class="text-dark">Résultat JSON :</h5>
            <pre id="apiResult" class="bg-dark text-white p-3 rounded" style="max-height: 400px; overflow-y: auto;"></pre>
        </div>
    </div>

</main>

<%@ include file="includes/footer.jsp" %>
<script>
    function getClientByIdentifiant() {
        const identifiant = document.querySelector('#identifiantInput').value;

        if (!identifiant) {
            alert("Veuillez entrer un identifiant.");
            return;
        }


        fetch("/api/clients/identifiant/"+identifiant)
            .then(response => response.json())
            .then(data => displayResult(data))
            .catch(err => displayError(err));
    }

    function getAllClients() {
        fetch("/api/clients")
            .then(response => response.json())
            .then(data => displayResult(data))
            .catch(err => displayError(err));
    }

    function displayResult(data) {
        const json = JSON.stringify(data, null, 2);
        document.getElementById("apiResult").textContent = json;
    }

    function displayError(error) {
        document.getElementById("apiResult").textContent = "Erreur : " + error;
    }
</script>

</body>
</html>
