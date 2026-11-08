# SoftCafé — Módulo CRUD de productos con Java y JDBC

**Evidencia:** GA7-220501096-AA2-EV01  
**Tecnología:** Java 17, Maven, MySQL y JDBC.

## Funcionalidades
- Inserción de productos.
- Consulta de todos los productos y búsqueda por ID.
- Actualización de productos.
- Eliminación de productos.
- Consultas parametrizadas mediante `PreparedStatement`.
- Cierre automático de conexiones y recursos con `try-with-resources`.

## Requisitos
- JDK 17 o superior.
- Maven.
- MySQL Server y MySQL Workbench (opcional).

## Configuración de la base de datos
1. Abra MySQL Workbench y conéctese a su servidor.
2. Abra y ejecute el archivo `database/softcafe.sql`.
3. Si su usuario de MySQL no es `root` o tiene contraseña, configure las variables de entorno antes de ejecutar:
   - `SOFTCAFE_DB_URL` (opcional; por defecto `jdbc:mysql://localhost:3306/softcafe?serverTimezone=UTC`)
   - `SOFTCAFE_DB_USER` (por defecto `root`)
   - `SOFTCAFE_DB_PASSWORD` (por defecto vacío)
4. No guarde contraseñas reales dentro del repositorio.

## Ejecutar
Desde la carpeta del proyecto:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="co.softcafe.app.Main"
```
Si Maven no tiene configurado el complemento exec, puede ejecutar `co.softcafe.app.Main` desde IntelliJ IDEA, Eclipse o VS Code con las extensiones de Java y Maven instaladas.

## Estructura
```text
src/main/java/co/softcafe/
├── app/Main.java
├── config/DatabaseConnection.java
├── dao/ProductoDao.java
└── model/Producto.java
database/softcafe.sql
pom.xml
```

## Estándares aplicados
- Paquetes en minúscula.
- Clases con PascalCase.
- Métodos y variables con camelCase.
- Constantes con mayúsculas y guion bajo.
- Responsabilidades separadas en aplicación, configuración, modelo y acceso a datos.
- `PreparedStatement` para parametrizar valores y reducir riesgos de inyección SQL.

## Control de versiones con Git
Después de crear un repositorio vacío en GitHub o GitLab, desde esta carpeta ejecute:
```bash
git init
git add .
git commit -m "Construye módulo CRUD de productos con JDBC"
git branch -M main
git remote add origin URL_DE_TU_REPOSITORIO
git push -u origin main
```
Reemplace `URL_DE_TU_REPOSITORIO` por la URL real del repositorio que cree. Luego actualice `enlace_repositorio.txt`.

## Pruebas sugeridas para las capturas
1. Registrar un producto nuevo.
2. Consultar la lista y buscar por ID.
3. Actualizar el nombre, precio o stock de un producto.
4. Eliminar un producto de prueba.
5. Mostrar el repositorio remoto y los commits con `git remote -v` y `git log --oneline`.

**Nota:** este es un módulo académico de ejemplo para el catálogo de productos de SoftCafé. Antes de integrarlo con una base de datos existente, verifique que sus nombres y tipos de columnas coincidan con el esquema de su proyecto.
