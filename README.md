# Proyecto Money Calculator

Este proyecto es una aplicación de escritorio para la conversión de divisas, construida con un enfoque modular y patrones arquitectónicos. A continuación, se explica la organización del código y los conceptos clave detrás de su arquitectura.

---

## Estructura del Directorio

La organización del proyecto se basa en la separación de responsabilidades, agrupando componentes relacionados por función:

money-calculator/
├── pom.xml
└── src/
└── main/
└── java/
└── software/
└── ulpgc/
  └── apps/
    └── swing/ # Interfaz gráfica principal de usuario.
  └── arquitecture/
    ├── control/ # Comandos que gestionan acciones de usuario.
    ├── io/ # Manejo de la base de datos y datos externos.
    ├── model/ # Modelo de datos de la aplicación.
    ├── view/ # Manejo de la vista.
    
    
