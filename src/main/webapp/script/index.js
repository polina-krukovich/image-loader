
$(document).ready(function() {
    var options = {
        success: formSubmitted
    };
    $('#upload-form').ajaxForm(options);

    $('#upload-file').change(function () {
        readUrl(this);
    });

    listImages();

    $('#upload-submit').hide();
    $('#upload-img').hide();
});

function formSubmitted(responseText, statusText, xhr, $form)  {
    $('#upload-submit').hide();
    $('#upload-img').hide();
    $('#upload-file').val("");
    listImages();
}

function listImages() {
    $.ajax({
        type: "GET",
        url: "/list",
        success: function(response) {
            let i = 0;
            let htmlString = "";
            while (response[i] !== undefined) {
                let imageUrl = response[i].url;
                let date = new Date(response[i].creationDate).toUTCString().slice(0, 22);
                let size = (response[i].size / 1024).toFixed(2) + "KB";
                htmlString += "<div class='gallery__box'>\n" +
                    "                <img class='gallery__box__img' src='"+ imageUrl +"' alt='Image'>\n" +
                    "                <div class='gallery__box__content'>\n" +
                    "                    <p>" + date + "</p>\n" +
                    "                    <p>" + size + "</p>\n" +
                    "                    <i class='fas fa-trash-alt' onclick='deleteImage(" + i + ")'></i>\n" +
                    "                    <div class='hidden' id='blob-id-" + i + "'>" + JSON.stringify(response[i].blobId) + "</div>" +
                    "                </div>\n" +
                    "          </div>";
                i++;
            }
            $("#gallery").html(htmlString);
        }
    });
}

function readUrl(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#upload-img')
                .attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
        $('#upload-img').show();
        $('#upload-submit').show();
    } else {
        if (!$('#upload-img').hidden) {
            $('#upload-img').hide();
        }
        if (!$('#upload-submit').hidden) {
            $('#upload-submit').hide();
        }
    }
}

function deleteImage(number) {
    if (confirm("Are you sure you want to delete this image?")) {
        let imageId = "blob-id-" + number;
        let blobId = document.getElementById(imageId).textContent;
        $.ajax({
            type: "POST",
            url: "/delete",
            data: {
                blobId: blobId
            },
            success: function (response) {
                listImages();
                alert("The image was successfully deleted.");
            }
        });
    }
}