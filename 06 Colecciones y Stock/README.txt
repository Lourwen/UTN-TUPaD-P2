Ejercicio de Colecciones, Stock y Relaciones entre Objetos



Los tres ejercicios comparten una idea central:

Modelar objetos del mundo real usando clases relacionadas, mantener la coherencia entre ellas y manipular colecciones mediante operaciones de alta, baja, búsqueda y actualización.

Cada ejercicio implementa un sistema distinto, pero de arquitectura similar

Clases de dominio (Libro, Autor – Profesor, Curso – etc.)

Relaciones 1 a N

Colecciones internas (ArrayList)

Una clase “administradora” (Biblioteca, Universidad)

Operaciones CRUD + listados

Métodos seguros que mantienen la integridad del modelo

--------------1 EJERCICIO: Biblioteca / Libros / Autores (Composición 1–N)
 Estructura del modelo

Biblioteca: contiene muchos Libros.

Libro: pertenece obligatoriamente a una Biblioteca (composición).

Autor: solo guarda datos; el libro almacena un objeto Autor.

 Características importantes

Si la biblioteca se elimina → sus libros también (composición fuerte).

mostrarInfo() del Libro muestra datos y usa autor.toString().

Se recorre una colección List<Libro>.

Operaciones: agregar libro, listar libros, buscar libro.

 Aprendizajes clave

Uso básico de colecciones.

Encapsulamiento.

Composición fuerte 1→N.

Preparación de métodos de reporte.

----------------2️ EJERCICIO: Almacén / Productos / Stock (Inventario simple)

(lo vimos en paralelo a Biblioteca o como variación del mismo patrón)

Estructura del modelo

Almacén mantiene una lista de Productos.

Cada Producto tiene stock y operaciones de entrada/salida.

Acciones

Agregar producto al sistema.

Descontar stock con validaciones.

Reponer stock.

Listado general con cantidades.

Búsqueda por código.

Aprendizajes clave

Colecciones con búsqueda por atributos (código, ID).

Validaciones de stock (no permitir negativo).

Mostrar reportes y totales.

Métodos de negocio en la clase de dominio.

-------------3️ EJERCICIO: Universidad / Profesor / Curso (Bidireccional 1–N)

(el ejercicio más completo y avanzado)

Estructura del modelo

Profesor dicta muchos Cursos → 1:N

Curso tiene exactamente un Profesor

Es una relación BIDIRECCIONAL, no composición.

Punto clave del ejercicio

Cuando se modifica un lado de la relación, el otro debe sincronizarse automáticamente.

Ejemplos:

Si se hace curso.setProfesor(p) → agregar el curso a p.getCursos().

Si se elimina un curso → removerlo de la lista del profesor.

Si se elimina un profesor → los cursos deben quedar con profesor = null.

Universidad actúa como "gestor"

agregarProfesor()

agregarCurso()

asignarProfesorACurso()

eliminarCurso(), eliminarProfesor()

Listados y reportes

Aprendizajes clave

Mantenimiento de invariante de asociación.

Métodos seguros con sincronización bidireccional.

Uso avanzado de colecciones + búsqueda.

Diferencia entre composición vs relación normal.

Reasignación controlada.

