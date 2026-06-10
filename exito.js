const modalRegistro = document.getElementById("modalRegistro");
const modalLogin2 = document.getElementById("modalLogin");

function mostrarModalRegistro() {
    modalRegistro.classList.add("active");
}

function mostrarModalLogin() {
    modalLogin2.classList.add("active");
}

document.getElementById("btnRegistro").addEventListener("click", () => {
    modalRegistro.classList.remove("active");

    // Redirigir al login
    window.location.href = "index.html";
});

document.getElementById("btnLogin").addEventListener("click", () => {
    modalLogin2.classList.remove("active");

    // Redirigir al dashboard
    window.location.href = "index.html";
});