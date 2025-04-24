document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", function (e) {
        const correo = document.getElementById("correo").value.trim();
        const password = document.getElementById("password").value.trim();

        if (correo === "" || password === "") {
            e.preventDefault(); // cancela el envío
            alert("Por favor, complete todos los campos.");
        }
    });
});
