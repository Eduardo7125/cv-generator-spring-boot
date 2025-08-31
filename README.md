# Generador de CV con Spring Boot

Este proyecto es una aplicación web construida con Java y el framework Spring Boot, diseñada para facilitar la creación de currículums vitae profesionales de forma rápida e intuitiva. La aplicación cuenta con un formulario dinámico, una vista previa en tiempo real que se actualiza a medida que el usuario introduce sus datos, y la capacidad de exportar el resultado final a un documento PDF.

![Captura de pantalla del formulario](https-apps-googleusercontent-com-v1-files-1wljtyb27072q7z02r9x11b7z9d4c79p5-content-image_png-800_419)

## Características Principales

-   **Formulario Dinámico:** Añade o elimina secciones como experiencia, educación y certificaciones de forma fluida sin recargar la página.
-   **Vista Previa en Tiempo Real:** Observa cómo queda tu CV en un panel lateral que se actualiza instantáneamente con cada cambio que realizas.
-   **Múltiples Plantillas:** Elige entre diferentes diseños de CV para encontrar el que mejor se adapte a tu perfil profesional.
-   **Exportación a PDF:** Descarga tu currículum finalizado en formato PDF con un solo clic, listo para ser enviado.
-   **Cambio de Tema:** Interfaz con tema claro y oscuro para una mayor comodidad visual.
-   **Subida de Imágenes:** Personaliza tu CV subiendo tu propia foto de perfil.

## Tecnologías Utilizadas

| Tecnología | Descripción |
| :--- | :--- |
| **Java 21** | Lenguaje de programación principal para el backend. |
| **Spring Boot 3.x** | Framework para la creación de la aplicación web y la gestión de dependencias. |
| **Thymeleaf** | Motor de plantillas para renderizar las vistas HTML del lado del servidor. |
| **Maven** | Herramienta para la gestión de dependencias y la construcción del proyecto. |
| **HTML5 / CSS3** | Estructura y diseño de las páginas web. |
| **JavaScript** | Lógica del lado del cliente para la interactividad y la vista previa en tiempo real. |
| **OpenHTMLtoPDF** | Librería para convertir el HTML generado por Thymeleaf en un documento PDF. |
| **Lombok** | Librería para reducir el código repetitivo en las clases del modelo (getters, setters, etc.). |

## ¿Cómo Funciona?

El flujo de trabajo de la aplicación es el siguiente:

1.  **Entrada de Datos:** El usuario accede al formulario (`cv-form.html`), donde introduce toda su información personal y profesional.
2.  **Envío al Controlador:** Al hacer clic en "Generar y Previsualizar", el formulario envía los datos a un endpoint (`@PostMapping("/preview-cv")`) en el `CvController`. Spring Boot automáticamente mapea los datos del formulario a un objeto Java (`CvData`).
3.  **Almacenamiento en Sesión:** El `CvController` guarda el objeto `CvData` en la `HttpSession` para mantener la información disponible entre peticiones.
4.  **Redirección a la Vista Previa:** El navegador es redirigido a la página de vista previa (`/cv-preview`).
5.  **Renderizado con Thymeleaf:** El endpoint (`@GetMapping("/cv-preview")`) recupera el objeto `CvData` de la sesión y lo pasa a una plantilla de Thymeleaf (`cv-template.html` o similar). Thymeleaf usa estos datos para generar el HTML final del CV.
6.  **Descarga de PDF:** Si el usuario hace clic en "Descargar como PDF", se llama a un endpoint (`/download-pdf`). Este endpoint utiliza el `PdfGenerationService`, que a su vez renderiza el HTML del CV con los datos de la sesión y lo convierte en un `byte[]` de PDF usando la librería OpenHTMLtoPDF.

## Cómo Empezar

Para ejecutar este proyecto en tu entorno local, sigue estos pasos:

### Pre-requisitos

-   JDK 21 o superior.
-   Apache Maven 3.6 o superior.

### Instalación

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/tu-usuario/cv-generator-spring-boot.git
    ```

2.  **Navega a la carpeta del proyecto:**
    ```bash
    cd cv-generator-spring-boot
    ```

3.  **Ejecuta la aplicación con Maven:**
    ```bash
    mvn spring-boot:run
    ```

4.  Abre tu navegador web y ve a `http://localhost:8080`.

## Estructura del Proyecto

El código fuente está organizado en los siguientes paquetes principales:

```
src/main/java/com/eduardocristea/cv_generator_spring_boot
├── controller/     # Controladores que manejan las peticiones HTTP.
├── model/          # Clases de dominio (POJOs) que representan los datos (CvData, Experience, etc.).
├── service/        # Lógica de negocio (generación de PDF, almacenamiento de archivos).
│   └── impl/       # Implementaciones de las interfaces de servicio.
└── util/           # Clases de utilidad (si las hubiera).

src/main/resources
├── static/         # Archivos estáticos: CSS, JavaScript, imágenes.
├── templates/      # Plantillas HTML de Thymeleaf.
└── application.properties # Archivo de configuración de Spring Boot.
```
