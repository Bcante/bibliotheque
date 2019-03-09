function envoyerRequete(url, data, type) {
    $.ajax({
        url: url,
        dataType: 'json',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(data),
        type: type,
        success: function (response) {
            console.log(response.status);
            console.log(response.statusText);
        },
        error: function (response) {

        }
    });
}

function newAuteur() {
    var data = {
        "nom": $("#nomAuteur").val(),
        "prenom": $("#prenomAuteur").val()
    };
    envoyerRequete('/auteurs/', data, "POST");
    // code avec le résultat de envoyerRequete()....
}