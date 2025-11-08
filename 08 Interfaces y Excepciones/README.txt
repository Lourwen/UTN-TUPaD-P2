Conclusiones del TP 8: Interfaces y Excepciones en Java

Uso de interfaces

Se logró entender cómo las interfaces actúan como contratos de comportamiento, permitiendo que distintas clases compartan métodos sin heredar implementación concreta.

La implementación de interfaces como Pagable, Pago, PagoConDescuento y Notificable permitió un diseño modular y flexible del sistema de e-commerce.


Herencia múltiple a través de interfaces

Se aplicó la herencia múltiple de forma segura, combinando distintos comportamientos en una misma clase sin depender de herencia de estado.

Por ejemplo, Pedido combina cálculo de total (Pagable) y notificación al cliente (Notificable).


Manejo de excepciones para robustez del programa

Se utilizaron correctamente estructuras try-catch-finally para evitar que el programa se detenga ante errores como división por cero, entrada de datos inválida o archivos inexistentes.

Esto refuerza la robustez del código y garantiza la continuidad de la ejecución.


Creación de excepciones personalizadas

Se desarrolló EdadInvalidaException para validar reglas de negocio, mostrando cómo se pueden definir restricciones propias y comunicar errores de manera clara al usuario.


Buenas prácticas en manejo de recursos

Se aplicó try-with-resources para lectura de archivos, asegurando el cierre automático de recursos y evitando fugas de memoria.

Se combinó con el uso de finally cuando se trabajó con Scanner para liberar recursos explícitamente.


Diseño escalable y mantenible

La integración de interfaces y manejo de excepciones permite crear un código más desacoplado, reutilizable y seguro, listo para ampliaciones futuras (por ejemplo, agregar nuevos métodos de pago o tipos de notificación).


Aprendizaje integral

Este TP reforzó la comprensión de conceptos clave de Java: POO, interfaces, polimorfismo, manejo de errores y buenas prácticas, integrándolos en un caso práctico de aplicación real (sistema de e-commerce y ejercicios de validación).