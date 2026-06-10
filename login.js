const loginForm = document.getElementById("loginForm");
const loginError = document.getElementById("loginError");

if (loginForm) {
loginForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    loginError.textContent = "";

    const email = document.getElementById("email-log").value.trim();
    const password = document.getElementById("pass-log").value;

    if (!email || !password) {
        loginError.textContent = "Completa todos los campos.";
        return;
    }

    try {

        const response = await fetch(
            "http://localhost:8080/api/auth/login",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
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
                "Credenciales inválidas."
            );
        }

        // Guardar token JWT
        localStorage.setItem("token", data.token);

        // Guardar datos básicos del usuario
        localStorage.setItem(
            "usuario",
            JSON.stringify({
                id: data.id,
                nombre: data.nombre,
                apellido: data.apellido,
                email: data.email
            })
        );

        // Limpiar loginFormulario
        loginForm.reset();

        // Mostrar modal
        mostrarModalLogin();

    } catch (error) {

        loginError.textContent = error.message;
        console.error(error);

    }
});
}


