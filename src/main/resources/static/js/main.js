function envoyerRequete(url, data, type) {
    return $.ajax({
        url: url,
        dataType: 'json',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(data),
        type: type,
        async : false
    });
}

function showAndHideAlerts(response, success, fail) {
    if(response.status.toString().startsWith('2')) {
        $(fail).hide();
        $(success).show();

        setInterval(function () {
            location.reload(true);
        }, 3000);
    }
    else {
        $(success).hide();
        $(fail).show();
    }
}

function newAuteur() {
    let data = {
        "nom": $("#nomAuteur").val(),
        "prenom": $("#prenomAuteur").val()
    };
    let response = envoyerRequete('/auteurs/', data, 'POST');
    showAndHideAlerts(response, '#newauteur-success', '#newauteur-danger');
}

function newUsager() {
    let data = {
        "nom": $("#nomUsager").val(),
        "prenom": $("#prenomUsager").val(),
        "adresse": $("#adresseUsager").val(),
        "mail": $("#mailUsager").val()
    };
    let response = envoyerRequete('/usagers/', data, "POST");
    showAndHideAlerts(response, '#usager-success', '#usager-danger');
}

function newEmprunt() {
    let data = {
        "idexemplaire": $("#idExemplaireNewEmprunt").val(),
        "idusager": $("#idUsagerNewEmprunt").val()
    };
    let response = envoyerRequete('/emprunts', data, "POST");
    showAndHideAlerts(response, '#newemprunt-success', '#newemprunt-danger');
}

function terminerEmprunt() {
    let data = {
        "idexemplaire": $("#idExemplaireTerminerEmprunt").val(),
        "idusager": $("#idUsagerTerminerEmprunt").val()
    };
    let response = envoyerRequete('/emprunts', data, "DELETE");
    showAndHideAlerts(response,'#rendreemprunt-success', '#rendreemprunt-danger');
}

function newReservation() {
    let data = {
        "titre" : $("#titreoeuvre-newreservation").val(),
        "idusager" : $("#idUsagerNewReservation").val()
    };
    let response = envoyerRequete('/reservations', data, "POST");
    showAndHideAlerts(response, '#newreservation-success', '#newreservation-danger');
}

function annulerReservation() {
    let data = {
        "titre" : $("#titreoeuvre-annulation").val(),
        "idusager" : $("#idUsagerAnnulerReservation").val()
    };
    let response = envoyerRequete('/reservations', data, "DELETE");
    showAndHideAlerts(response, '#annulerreservation-success', '#annulerreservation-danger');
}

function newExemplaire() {
    let data = {
        "idoeuvre" : $("#idoeuvre-newexemplaire").val(),
        "etat" : $("#etat-newexemplaire").val()
    };
    let response = envoyerRequete('/exemplaires', data, "POST");
    showAndHideAlerts(response, '#newexemplaire-success', '#newexemplaire-danger');
}

function deleteExemplaire() {
    let response = envoyerRequete('/exemplaires/' + $('#idExemplaireDeleteExemplaire').val(), '', 'DELETE');
    showAndHideAlerts(response, '#deleteexemplaire-success', '#deleteexemplaire-danger');
}

function deleteOeuvre() {
    let response = envoyerRequete('/oeuvres/' + $('#idoeuvre-deleteoeuvre').val(), '', 'DELETE');
    showAndHideAlerts(response, '#deleteoeuvre-success', '#deleteoeuvre-danger');
}

function newLivre() {
    let data = {
        "titre" : $('#titrelivre-newlivre').val(),
        "ISBN" : $('#isbn-newlivre').val(),
        "parution" : $('#dateparution-newlivre').val(),
        "idauteur" : $('#auteur-newlivre').val()
    };
    let response = envoyerRequete('/livres/', data, 'POST');
    showAndHideAlerts(response, '#livre-success', '#livre-danger');
}

function newMagasine() {
    let data = {
        "titre" : $('#titrelivre-newmagasine').val(),
        "ISBN" : $('#isbn-newmagasine').val(),
        "parution" : $('#dateparution-newmagasine').val(),
        "type" : $('#type-newmagasine').val()
    };
    let response = envoyerRequete('/magasines/', data, 'POST');
    showAndHideAlerts(response, '#magasine-success', '#magasine-danger');
}

$(document).ready(function(){
    $('form').submit(function(e) {
        e.preventDefault();
    });

    $('.card-header').click(function () {
        $('.alert').hide();
        $('form')[0].reset();
    });

    $('.close-success').click(function() {
       location.reload(true);
    });
});
