package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Usuario;
import com.example.demo.service.DemoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/")
public class DemoController {
    
    final private Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    DemoService miServicio;                                  // Servicio sin constructor
    DemoService miServicioConConstructor = new DemoService();// Servicio con constructor

    @Autowired
    UsuarioMapper usuarioMapper;                             // Componente sin constructor

    // Llamada CREATE: crea un usuario, con llamada HTTP tipo POST.
    @PostMapping("crearusuario/{detalleUsuario}")
    public ResponseEntity crearUsuario (  @PathVariable String detalleUsuario, 
                                @RequestBody UsuarioDTO usuarioDTO){

        this.logger.info("INICIO [DemoController.crearUsuario] Creando usuario.");
        
        Usuario usuario = this.usuarioMapper.comoUsuario(usuarioDTO, detalleUsuario);
        Long idNuevoUsuario = this.miServicio.crearUsuario(usuario);

        this.logger.info("FIN [DemoController.crearUsuario] Creando usuario.");
        return new ResponseEntity(idNuevoUsuario, HttpStatus.OK );
    }

    // Llamada READ: lee todos los usuarios, con llamada HTTP tipo GET.
    @GetMapping
    public ResponseEntity<List<Usuario>> mostrarTodosLosUsuarios (@RequestParam String ordenadosPor){
        this.logger.info("INICIO [DemoController.mostrarTodosLosUsuarios] mostrando todos los usuarios.");

        List<Usuario> usuarios = miServicio.devolverTodosLosUsuarios();
        final ResponseEntity<List<Usuario>> response = ResponseEntity.ok(usuarios);

        this.logger.info("FIN [DemoController.mostrarTodosLosUsuarios] mostrando todos los usuarios.");
        return response;
    }

    // Llamada READ: trae solo un usuario por ID
    @GetMapping("mostrarUsuario/{userId}")
    public ResponseEntity<Usuario> mostrarUsuario(@PathVariable Integer userId){
        this.logger.info("INICIO [DemoController.mostrarUsuario] mostrando el usuario concreto por ID.");

        Usuario usuario = miServicio.devolverUsuarioPorId(userId);
        final ResponseEntity<Usuario> response = ResponseEntity.ok(usuario);

        this.logger.info("INICIO [DemoController.mostrarUsuario] mostrando el usuario concreto por ID.");
        return response;
    }

    // Llamada UPDATE: modifica un usuario con llamada HTTP tipo PUT.
    @PutMapping("/modificarDetalleUsuario/{idUsuario}/{nuevoDetalle}")
    public ResponseEntity<Void> modificarDetalleDeUsuario (@PathVariable String idUsuario, 
                            @PathVariable String nuevoDetalle){
        this.logger.info("INICIO [DemoController.modificarDetalleDeUsuario] modificando detalles de usuarios.");
        Usuario usuario = miServicio.modificarUsuario(Long.valueOf(idUsuario), nuevoDetalle);
        this.logger.info("FIN [DemoController.modificarDetalleDeUsuario] modificando detalles de usuarios.");
        return ResponseEntity.noContent().build();
    }

    // Llamada DELETE: elimina un usuario con llamada HTTP tipo POST.
    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String id){
        this.logger.info("INICIO [DemoController.eliminarUsuario] Eliminando usuario por su ID.");
        Long idUserEliminado = miServicio.eliminarUsuarioPorId(Long.valueOf(id));
        if(idUserEliminado!=null){
            this.logger.info("FIN [DemoController.eliminarUsuario] Eliminando usuario por su ID.");
            return ResponseEntity.noContent().build();
        }
        else {
            this.logger.error("ERROR [DemoController.eliminarUsuario] No se ha encontrado el ID {} en BBDD.", id);
            return ResponseEntity.notFound().build();
        }
    }

    /************************** PRUEBAS EXTRA ***************************************
    ************ Consiguiendo valores desde el constructor del service **************
    *************************************** vs **************************************
    ************ Consiguiendo valores desde el fichero "application.property" *******
    *********************************************************************************/

    @GetMapping("/getServiceConConstructor")
    public String getServiceConConstructor (){
        return this.miServicioConConstructor.consiguePrefijoSufijoDelConstructor();
    }

    @GetMapping("/getServiceConAutowired")
    public String getServiceConAutowired (){
        return this.miServicio.consiguePrefijoSufijoDeApplicationProperties();
    }
}
