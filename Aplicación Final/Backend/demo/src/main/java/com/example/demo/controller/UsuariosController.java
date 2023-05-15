package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.demo.model.ResponseString;
import com.example.demo.model.Usuario;
import com.example.demo.service.FirebaseUsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200/")
public class UsuariosController {
    
    @Autowired
    private FirebaseUsuarioService miFirebaseDemoService;

    @Autowired
    private UsuarioMapper usuarioMapper;                             // Componente sin constructor

    // Llamada CREATE: crea un usuario, con llamada HTTP tipo POST.
    @PostMapping("crearusuario/{idNuevoUsuario}")
    public ResponseEntity crearUsuario (@PathVariable String idNuevoUsuario, @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = this.usuarioMapper.comoUsuario(usuarioDTO);
        String response = miFirebaseDemoService.crearUsuarioFirebase(usuario, idNuevoUsuario);
        return new ResponseEntity(response, HttpStatus.OK );
    }

    // Llamada READ: lee todos los usuarios, con llamada HTTP tipo GET.
    @GetMapping(value = "/allUsers" )
    public ResponseEntity<List<Usuario>> mostrarTodosLosUsuarios (@RequestParam String ordenadosPor){
        List<Usuario> usuarios = miFirebaseDemoService.listarUsuariosFirebase();
        usuarios = miFirebaseDemoService.ordenarListaUsuarios(usuarios, ordenadosPor);
        ResponseEntity response = new ResponseEntity(usuarios, HttpStatus.OK);
        return response;
    }

    // Llamada GET: devuelve obj ResponseString con "true"/"false" si existe o no el idUsuario
    @GetMapping(value = "/existeUsuarioActual/{idUsuario}" )
    public ResponseEntity existeUsuarioActual(@PathVariable String idUsuario){
        ResponseString response = miFirebaseDemoService.probarSiExisteUsuarioFirebase(idUsuario);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    // Llamada UPDATE: modifica un usuario con llamada HTTP tipo PUT.
    @PutMapping("/actualizarUsuario/{idUsuario}")
    public ResponseEntity<Void> modificarDetalleDeUsuario (@PathVariable String idUsuario,
                            @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = this.usuarioMapper.comoUsuario(usuarioDTO);
        Boolean b = miFirebaseDemoService.modificarUsuarioFirebase(idUsuario, usuario);
        if( b == true){
            return ResponseEntity.noContent().build();
        } else{
            ResponseEntity responseEntity = new ResponseEntity("Imposible actualizar, el elemento no existe.", 
                                                            HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
    }

    // Llamada DELETE: elimina un usuario con llamada HTTP tipo POST.
    @PostMapping("/eliminarUsuario/{id}")
    public ResponseEntity<Void> eliminarUsuario (@PathVariable String id){
                Boolean b = miFirebaseDemoService.eliminarUsuarioPorIdFirebase(id);
        if (b != null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}