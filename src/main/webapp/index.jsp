<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Image Loader</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="style/index.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
    <link rel="icon" type="images/png" href="https://storage.cloud.google.com/image-loader-265208.appspot.com/web/dowload.png?supportedpurview=project">
</head>
<body>
    <header class="header">
        <h1 class="header__text__name">Image Loader</h1>
        <p class="header__text__author">Developed by <b>Polina Krukovich</b></p>
    </header>
    <main class="main">
        <section class="form__box">
            <form method="POST" action="/create" enctype="multipart/form-data" id="upload-form" class="form">
                <label class="form__file__box glow-on-hover">
                    Select file
                    <input type="file" name="file" accept="image/*" id="upload-file" class="form__file hidden"/>
                </label>
                <img src="" alt="Preview" id="upload-img" class="form__img_preview">
                <button type="submit" id="upload-submit" class="form__submit glow-on-hover">Load</button>
            </form>
        </section>
        <section class="gallery" id="gallery">

        </section>
    </main>
    <footer class="footer">
        <i class="fab fa-vk"></i>
        <i class="fab fa-telegram-plane"></i>
        <i class="fab fa-twitter"></i>
        <i class="fab fa-facebook-square"></i>
        <i class="fab fa-instagram"></i>
    </footer>

    <script src="https://kit.fontawesome.com/f57120b98c.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"
            integrity="sha384-FzT3vTVGXqf7wRfy8k4BiyzvbNfeYjK+frTVqZeNDFl8woCbF0CYG6g2fMEFFo/i"
            crossorigin="anonymous"></script>
    <script src="script/index.js" type="text/javascript"></script>
</body>
</html>
