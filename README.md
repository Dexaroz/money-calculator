# Money Calculator

Este proyecto es una implementación de un convertidor de divisas basado en un enfoque modular y patrones arquitectónicos. A continuación, se explica la organización del código y los conceptos clave detrás de su arquitectura.  

## Ejemplo de vista:
![image](https://github.com/user-attachments/assets/f471769c-7fc3-4225-a7a3-e4f6eb7bbc70)  
![image](https://github.com/user-attachments/assets/420889a5-7283-4b08-89b0-055ca0d13c80)  
![image](https://github.com/user-attachments/assets/2170181a-d8db-4763-a311-9067c641e5d8)  

## Estructura del Directorio

La organización del proyecto se basa en una separación de responsabilidades, agrupando componentes relacionados:

![image](https://github.com/user-attachments/assets/e5390ebd-f854-49d4-ad30-440ecfb9e261)


  ## Patrones Arquitectónicos

### Modelo-Vista-Controlador (MVC)

La arquitectura general del proyecto sigue este enfoque, separando las responsabilidades en tres capas:

- **Modelo**:
  - Define los datos y la lógica del convertidor.
  - Contiene clases para representar transacciones, divisas y registros de intercambio.
  - Ejemplo: `ExchangeRecord`, `ExchangeTransaction`, `Currency`.

- **Vista**:
  - Gestiona la presentación visual.
  - Proporciona componentes para mostrar las transacciones, seleccionar divisas y otros elementos de la UI.
  - Ejemplo: `SwingMoneyDialog`, `SwingCurrencyDialog`, `SwingMoneyDisplay`.

- **Controlador**:
  - Media entre el modelo y la vista, actuando como coordinador.
  - Gestiona eventos de usuario, actualiza el modelo y notifica a la vista.
  - Ejemplo: `AddTransactionCommand`, `CalculateCommand`.

### Patrón Comando

Se utiliza para encapsular solicitudes como objetos, permitiendo la ejecución de comandos sin conocer sus detalles. Este enfoque simplifica la ejecución y modificación de acciones.

- Implementado en: `Command`, `AddTransactionCommand`, `CalculateCommand`.

### Patrón Observador

Permite la comunicación reactiva entre componentes. Se utiliza para que el modelo notifique cambios relevantes, como nuevas transacciones o actualizaciones de divisas, a los observadores interesados.

- Implementado en: `Observer`, `ExchangeRecordObserver`.

### Patrón Singleton

Garantiza que ciertas funcionalidades compartidas solo tengan una instancia global en toda la aplicación, como el registro de transacciones.

- Implementado en: `SwingFavoritiesContent`.

## Servicios Auxiliares

Encapsulan funcionalidades específicas para facilitar el mantenimiento y la reutilización del código:

- **Gestión de transacciones**:
  - Registro y almacenamiento de transacciones en una base de datos.
  - Implementado en: `DatabaseExchangeTransactionWriter`, `DatabaseExchangeTransactionReader`.

- **Gestión de divisas**:
  - Lectura de divisas desde una base de datos.
  - Implementado en: `DatabaseCurrencyReader`.

- **Cálculo de conversiones**:
  - Implementado en: `CalculateCommand`.

## Modularidad

Cada componente está diseñado con una única responsabilidad. Esto mejora la mantenibilidad y escalabilidad del proyecto. Por ejemplo:

- **Módulo de entrada/salida**:
  - Manejo de datos externos como bases de datos (`DatabaseCurrencyReader`, `DatabaseExchangeTransactionWriter`).

- **Módulo del modelo**:
  - Representación de entidades centrales (`Currency`, `ExchangeTransaction`, `ExchangeRecord`).

- **Módulo de la vista**:
  - Interfaz visual (`SwingMoneyDialog`, `SwingCurrencyDialog`, `SwingMoneyDisplay`).

## Ejemplo de Flujo

1. El usuario interactúa con la UI para seleccionar una divisa y un monto.
2. El controlador recibe la acción y crea una nueva transacción basada en los datos ingresados.
3. La vista se actualiza para reflejar el nuevo estado del modelo.
4. Durante el proceso de conversión:
   - Se consulta y actualiza el modelo.
   - La vista muestra los cambios en tiempo real.
   - Los observadores verifican si se han registrado correctamente las transacciones.

## Tecnologías Utilizadas

- **Java Swing**: Para la interfaz gráfica.
- **Maven**: Gestión de dependencias y configuración del proyecto.
- **Java 17**: Versión requerida para ejecutar el proyecto.

## Configuración y Ejecución

1. Clonar el repositorio.
2. Compilar el proyecto con Maven:
   ```bash
   mvn clean install  


