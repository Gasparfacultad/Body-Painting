# 🎨 Body Painting - Sistema de Autenticación

Trabajo Práctico Integrador  
**Metodología de Sistemas II**  
UTN Facultad Regional Villa María — Grupo 4

## 📌 Descripción

Body Painting es una aplicación web desarrollada para gestionar el registro e inicio de sesión de usuarios dentro del alcance del MVP definido para el proyecto. La solución fue implementada con una arquitectura separada por capas y priorizó la autenticación segura, el manejo de errores y la integración entre frontend y backend. El frontend fue construido con HTML, CSS y JavaScript puro, mientras que el backend se desarrolló con Java, Spring Boot, JPA/Hibernate, Spring Security y JWT.

## 🚀 Tecnologías utilizadas

- **Backend:** Java 21, Spring Boot, Spring Security, Spring Data JPA, Hibernate, JWT y MySQL.
- **Frontend:** HTML5, CSS3 y JavaScript puro.
- **Seguridad:** BCrypt para el hash de contraseñas y JWT para autenticación stateless.
- **Persistencia:** MySQL.
- **Integración:** API Fetch de JavaScript para el consumo de servicios REST.

##  Funcionalidades principales

- Registro de usuario con validaciones de campos obligatorios.
- Inicio de sesión con credenciales seguras.
- Hash de contraseñas con BCrypt.
- Autenticación mediante JWT.
- Manejo global de excepciones.
- Persistencia de sesión mediante `localStorage`.
- Comunicación entre frontend y backend mediante servicios REST.

##  Alcance del proyecto

El sprint actual se centró en las historias de usuario de **Registro de usuario** e **Inicio de sesión**. La historia de **Gestión de direcciones** fue despriorizada para un sprint futuro, con el objetivo de concentrar el trabajo en la autenticación, la seguridad y la integración completa del MVP.

##  Estructura del proyecto

El repositorio está organizado en dos módulos principales:

- **Back**: contiene la lógica del backend.
- **Front**: contiene la interfaz de usuario.

## 🛠️ Requisitos previos

Antes de ejecutar el proyecto, asegurate de contar con:

- Java 21 o superior
- Maven
- MySQL Server
- Git
- Un navegador web moderno

### 1. Clonar el repositorio

```bash
git clone https://github.com/Gasparfacultad/Body-Painting.git
cd Body-Painting
