const registerForm = document.getElementById("registerForm");
const errorElement = document.getElementById("registerError");
const successElement = document.getElementById("registerSuccess");

if (registerForm) {
registerForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    errorElement.textContent = "";
    successElement.textContent = "";

    const nombre = document.getElementById("nombre").value.trim();
    const apellido = document.getElementById("apellido").value.trim();
    const email = document.getElementById("email-reg").value.trim();
    const password = document.getElementById("pass-reg").value;
    const repetirPassword = document.getElementById("pass-rep").value;

    // Validaciones
    if (!nombre || !apellido || !email || !password || !repetirPassword) {
        errorElement.textContent = "Completa todos los campos.";
        return;
    }

    if (password.length < 8) {
        errorElement.textContent = "La contraseña debe tener al menos 8 caracteres.";
        return;
    }

    if (password !== repetirPassword) {
        errorElement.textContent = "Las contraseñas no coinciden.";
        return;
    }

    try {
        const response = await fetch(
            "http://localhost:8080/api/auth/register",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    nombre,
                    apellido,
                    email,
                    password
                })
            }
        );

        const data = await response.json();

        if (!response.ok) {
            throw new Error(
                data.message ||
                data.error ||
                "No se pudo registrar el usuario."
            );
        }

        mostrarModalRegistro();

        registerForm.reset();

        setTimeout(() => {
            window.location.href = "index.html";
        }, 1500);

    } catch (error) {
        errorElement.textContent = error.message;
        console.error(error);
    }
});
}