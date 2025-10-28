# Instalación del Proyecto

1. Instalar:
   - Java JDK 24 o superior  
   - Maven  
   - MySQL  

2. Crear base de datos:
  CREATE DATABASE empleadosdb;

3. EDITAR 
  src/main/resources/application.properties:
   spring.datasource.url=jdbc:mysql://localhost:3306/empleadosdb
   spring.datasource.username=root
   spring.datasource.password=tu_clave

4. Ejecutar desde IntelliJ:
   mvn spring-boot:run

5. Abrir en el navegador: 
   http://localhost:8080/empleados
