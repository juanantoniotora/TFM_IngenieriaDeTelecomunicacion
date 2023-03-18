package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Usuario;
import com.example.demo.service.DemoService;
import com.example.demo.service.FirebaseDemoService;

@RestController
@RequestMapping("/")
public class DemoController {
    
    @Autowired
    private DemoService miServicio;                                  // Servicio sin constructor
    private DemoService miServicioConConstructor = new DemoService();// Servicio con constructor

    @Autowired
    private FirebaseDemoService miFirebaseDemoService;

    @Autowired
    private UsuarioMapper usuarioMapper;                             // Componente sin constructor

    // Llamada CREATE: crea un usuario, con llamada HTTP tipo POST.
    @PostMapping("crearusuario/{detalleUsuario}")
    public ResponseEntity crearUsuario (  @PathVariable String detalleUsuario, 
                                @RequestBody UsuarioDTO usuarioDTO){
        
        Usuario usuario = this.usuarioMapper.comoUsuario(usuarioDTO, detalleUsuario);
        ///Long idNuevoUsuario = this.miServicio.crearUsuario(usuario);
        Long response = miFirebaseDemoService.crearUsuarioFirebase(usuario);
        ///return idNuevoUsuario;
        return new ResponseEntity(response, HttpStatus.OK );
    }

    // Llamada READ: lee todos los usuarios, con llamada HTTP tipo GET.
    @GetMapping
    public ResponseEntity<List<Usuario>> mostrarTodosLosUsuarios (@RequestParam String ordenadosPor){
    
        ///List<Usuario> usuarios = miServicio.devolverTodosLosUsuarios();
        List<Usuario> usuarios = miFirebaseDemoService.listarUsuariosFirebase();
        usuarios = miFirebaseDemoService.ordenarListaUsuarios(usuarios, ordenadosPor);
        ResponseEntity response = new ResponseEntity(usuarios, HttpStatus.OK);
        return response;
    }

    // Llamada UPDATE: modifica un usuario con llamada HTTP tipo PUT.
    @PutMapping("/modificarDetalleUsuario/{idUsuario}/{nuevoDetalle}")
    public ResponseEntity<Void> modificarDetalleDeUsuario (@PathVariable String idUsuario, 
                            @PathVariable String nuevoDetalle, 
                            @RequestBody UsuarioDTO usuarioDTO){
        ///Usuario usuario = miServicio.modificarUsuario(idUsuario, nuevoDetalle);
        Usuario usuario = this.usuarioMapper.comoUsuario(usuarioDTO, nuevoDetalle);
        Boolean b = miFirebaseDemoService.modificarUsuarioFirebase(idUsuario, usuario);
        if( b == true){
            return ResponseEntity.noContent().build();
        }
        else{
            ResponseEntity responseEntity = new ResponseEntity("Imposible actualizar, el elemento no existe.", 
                                                            HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        
    }

    // Llamada DELETE: elimina un usuario con llamada HTTP tipo POST.
    @PostMapping("/eliminarUsuario/{id}")
    public ResponseEntity<Void> eliminarUsuario (@PathVariable String id){
        
        ///Long idUserEliminado = miServicio.eliminarUsuarioPorId(id);
        Boolean b = miFirebaseDemoService.eliminarUsuarioPorIdFirebase(id);
        if (b != null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
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
