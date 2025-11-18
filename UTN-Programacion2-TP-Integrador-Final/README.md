# 🐾 Sistema de Gestión de Mascotas y Microchips

## 📋 Descripción del Dominio

Este sistema implementa la gestión completa de **mascotas y microchips** con una relación **1→1**, donde cada mascota puede tener asociado un único microchip de identificación. El dominio elegido permite demostrar:

- **Entidad A (Mascota)**: Información básica del animal y su propietario
- **Entidad B (Microchip)**: Dispositivo de identificación implantado en la mascota
- **Relación 1→1**: Una mascota puede tener máximo un microchip, y un microchip pertenece a una sola mascota

### 🎯 Funcionalidades Implementadas

- **CRUD completo** para mascotas y microchips
- **Transacciones robustas** con commit/rollback automático
- **Validaciones de negocio** y campos obligatorios
- **Búsquedas específicas** por nombre, especie, código de microchip
- **Eliminación lógica** preservando integridad de datos
- **Manejo robusto de errores** con mensajes claros
- **Arquitectura por capas** (Presentación → Service → DAO → BD)

---

## 🛠️ Requisitos del Sistema

### Requisitos de Software

- **Java 8** o superior
- **MySQL 8.0** o superior
- **MySQL Connector/J** (incluido en el proyecto)

### Requisitos de Hardware

- 512 MB RAM mínimo
- 50 MB espacio en disco
- Conexión a red (para MySQL)

---

## 🗄️ Configuración de Base de Datos

### Paso 1: Crear la Base de Datos

Ejecute el siguiente script SQL en su servidor MySQL:

```sql
-- Crear base de datos
CREATE DATABASE IF NOT EXISTS mascotasdb
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE mascotasdb;

-- Tabla de microchips (entidad B)
CREATE TABLE microchips (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    fecha_implantacion DATE,
    veterinaria VARCHAR(100),
    observaciones TEXT,
    eliminado BOOLEAN DEFAULT FALSE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de mascotas (entidad A)
CREATE TABLE mascotas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    raza VARCHAR(100),
    fecha_nacimiento DATE,
    duenio VARCHAR(100) NOT NULL,
    microchip_id BIGINT UNIQUE,
    eliminado BOOLEAN DEFAULT FALSE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (microchip_id) REFERENCES microchips(id)
);

-- Índices para optimizar consultas
CREATE INDEX idx_mascotas_nombre ON mascotas(nombre);
CREATE INDEX idx_mascotas_especie ON mascotas(especie);
CREATE INDEX idx_microchips_codigo ON microchips(codigo);
CREATE INDEX idx_microchips_veterinaria ON microchips(veterinaria);
```

### Paso 2: Insertar Datos de Prueba (Opcional)

```sql
-- Datos de prueba para microchips
INSERT INTO microchips (codigo, fecha_implantacion, veterinaria, observaciones) VALUES
('MC001', '2023-01-15', 'Clínica Veterinaria San Martín', 'Primera implantación'),
('MC002', '2023-02-20', 'Hospital Animal Care', 'Microchip importado'),
('MC003', '2023-03-10', 'Veterinaria Del Centro', NULL);

-- Datos de prueba para mascotas
INSERT INTO mascotas (nombre, especie, raza, fecha_nacimiento, duenio, microchip_id) VALUES
('Max', 'Perro', 'Golden Retriever', '2020-05-15', 'Juan Pérez', 1),
('Luna', 'Gato', 'Siamés', '2021-03-10', 'María García', 2),
('Rocky', 'Perro', 'Pastor Alemán', '2019-08-22', 'Carlos López', 3),
('Mimi', 'Gato', 'Persa', '2022-01-05', 'Ana Martínez', NULL);
```

### Paso 3: Configurar Credenciales

Actualice las credenciales en `src/config/DatabaseConnection.java`:

```java
// Configuración por defecto (puede modificar según su entorno)
private static final String URL = "jdbc:mysql://localhost:3306/mascotasdb";
private static final String USER = "root";
private static final String PASSWORD = ""; // Cambie por su contraseña
```

---

## Compilación y Ejecución

### Compilar el Proyecto con IDE

1. Abrir el proyecto en **NetBeans**
2. Agregar `mysql-connector-java.jar` al classpath
3. Ejecutar la clase `TPI_Programacion_2.java`

---

## Credenciales de Prueba

### Datos para Pruebas Rápidas

#### Crear Mascota:

- **Nombre**: Luna
- **Especie**: Perro
- **Raza**: Golden Retriever
- **Dueño**: Juan Pérez
- **Fecha Nacimiento**: 2020-01-15

#### Crear Microchip:

- **Código**: MC12345
- **Veterinaria**: Clínica Animal Care
- **Fecha Implantación**: 2023-11-18

#### IDs para Búsquedas:

- **Mascota ID**: 1, 2, 3, 4
- **Microchip ID**: 1, 2, 3

---

## Flujo de Uso Recomendado

### 1. **Demostración Básica**

```
1. Ejecutar aplicación
2. Opción 3: Listar todas las mascotas
3. Opción 2: Buscar mascota por ID (usar ID: 1)
4. Opción 6: Buscar mascotas por nombre (buscar: "Max")
5. Opción 16: Ver estadísticas del sistema
```

### 2. **Demostración de Transacciones**

```
1. Opción 15: Crear mascota CON microchip
   - Completar todos los datos
   - Observar mensaje de éxito de transacción
2. Opción 3: Verificar que se creó correctamente
3. Opción 10: Listar microchips y verificar asociación
```

### 3. **Demostración de Validaciones**

```
1. Opción 1: Crear mascota
   - Dejar nombre vacío → Ver error de campo obligatorio
   - Ingresar fecha inválida → Ver error de formato
2. Opción 8: Crear microchip
   - Usar código duplicado → Ver error de unicidad
3. Opción 2: Buscar por ID
   - Ingresar ID inexistente → Ver manejo de ID no encontrado
   - Ingresar texto en lugar de número → Ver validación de entrada
```

### 4. **Operaciones CRUD Completas**

```
1. CREATE: Opciones 1, 8, 15
2. READ: Opciones 2, 3, 6, 7, 9, 10, 13, 14
3. UPDATE: Opciones 4, 11
4. DELETE: Opciones 5, 12 (eliminación lógica)
```

---

## Arquitectura del Sistema

```
┌─────────────────────────────────────┐
│          PRESENTACIÓN               │
│     AppMenu.java + Main             │
│   • Validación de entradas          │
│   • Conversión a mayúsculas         │
│   • Manejo robusto de errores       │
└─────────────┬───────────────────────┘
              │
┌─────────────▼───────────────────────┐
│            NEGOCIO                  │
│   MascotaService + MicrochipService │
│   • Transacciones obligatorias      │
│   • Validaciones de reglas 1→1      │
│   • Lógica de negocio centralizada  │
└─────────────┬───────────────────────┘
              │
┌─────────────▼───────────────────────┐
│            DATOS                    │
│      GenericDAO + DAOs Concretos    │
│   • PreparedStatement (seguridad)   │
│   • Connection externa (transacc.)  │
│   • Eliminación lógica              │
└─────────────┬───────────────────────┘
              │
┌─────────────▼───────────────────────┐
│        INFRAESTRUCTURA              │
│       DatabaseConnection            │
│   • Pool de conexiones MySQL       │
│   • Configuración centralizada      │
└─────────────────────────────────────┘
```

---

## Video de Demostración

**🔗 [Ver Video Completo en YouTube](https://drive.google.com/file/d/12DUxY6UMrqbRf7wCEpzDKasrkC_hLrDD/view?usp=sharing)**

---

## Casos de Prueba Incluidos

### Archivo: `TestGenericDAO.java`

- **Pruebas de GenericService**: Insertar, actualizar, eliminar, getById, getAll
- **Pruebas de Transacciones**: Commit exitoso y rollback en caso de error
- **Pruebas de Validaciones**: Campos obligatorios y regla 1→1
- **Pruebas de Búsquedas**: Por nombre, especie, código, veterinaria

---

## Patrones y Principios Aplicados

### **Patrones de Diseño**

- 🔸 **Generic DAO**: Reutilización de operaciones CRUD
- 🔸 **Service Layer**: Centralización de lógica de negocio
- 🔸 **Transaction Script**: Gestión robusta de transacciones
- 🔸 **Factory Method**: Creación de conexiones de BD

### **Principios SOLID**

- 🔸 **SRP**: Cada clase tiene una responsabilidad específica
- 🔸 **OCP**: Extensible mediante interfaces genéricas
- 🔸 **DIP**: Dependencias sobre abstracciones (GenericDAO, GenericService)

### **Características Técnicas**

- **PreparedStatement**: Prevención de inyección SQL
- **Connection Externa**: Soporte para transacciones distribuidas
- **Eliminación Lógica**: Preservación de integridad referencial
- **Validaciones Robustas**: Campos obligatorios y formatos
- **Manejo de Excepciones**: Mensajes claros y recuperación de errores

---
