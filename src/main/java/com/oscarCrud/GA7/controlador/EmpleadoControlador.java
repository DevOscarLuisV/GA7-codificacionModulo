package com.oscarCrud.GA7.controlador;


import com.oscarCrud.GA7.excepciones.RecursoNoEncontradoExcepcion;
import com.oscarCrud.GA7.modelo.Empleado;
import com.oscarCrud.GA7.servicio.EmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("GA7-app")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados() {
        var empleados = empleadoServicio.listarEmpleado();
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("*Empleado a agregar:"+ empleado );
        return empleadoServicio.guardarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el empleado id:" +id);
        return ResponseEntity.ok(empleado);
    }
    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoRecibido){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null)
            throw new RecursoNoEncontradoExcepcion("El id no existe:" + id);
        empleado.setNombres(empleadoRecibido.getNombres());
        empleado.setApellidos(empleadoRecibido.getApellidos());
        empleado.setCorreo(empleadoRecibido.getCorreo());
        empleado.setPassword(empleadoRecibido.getPassword());
        empleado.setCedula(empleadoRecibido.getCedula());
        empleado.setTelefono(empleadoRecibido.getTelefono());
        empleado.setDireccion(empleadoRecibido.getDireccion());
        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);

    }
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean >> eliminarEmpleado(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);

        if (empleado == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        empleadoServicio.eliminarEmpleado(empleado);
        // Json {"eliminado": "true"}
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }


}
