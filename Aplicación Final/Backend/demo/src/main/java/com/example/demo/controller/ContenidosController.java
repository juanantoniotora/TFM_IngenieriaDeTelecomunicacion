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

import com.example.demo.dto.ContenidoDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.mapper.ContenidoMapper;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Contenido;
import com.example.demo.model.Usuario;
import com.example.demo.service.DemoService;
import com.example.demo.service.FirebaseContenidoService;
import com.example.demo.service.FirebaseDemoService;

@RestController
@RequestMapping("/contenidos")
@CrossOrigin(origins = "http://localhost:4200/")
public class ContenidosController {
    
    @Autowired
    private FirebaseContenidoService firebaseContenidoService;

    @Autowired
    private ContenidoMapper contenidoMapper;


    // Llamada READ: lee todos los contnenidos del catalogo, con llamada HTTP tipo GET.
    @GetMapping(value = "/catalogo" )
    public ResponseEntity<List<Contenido>> mostrarTodosLosContenidosDelCatalogo (){
    
        List<Contenido> contenidos = firebaseContenidoService.listarContenidosCatalogo();
        contenidos = firebaseContenidoService.ordenarListaContenidos(contenidos);
        ResponseEntity response = new ResponseEntity(contenidos, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "/tendencias" )
    public ResponseEntity<List<Contenido>> mostrarTodosLosContenidosDeTendencias (){
    
        List<Contenido> contenidos = firebaseContenidoService.listarContenidosTendencias();
        contenidos = firebaseContenidoService.ordenarListaContenidos(contenidos);
        ResponseEntity response = new ResponseEntity(contenidos, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "/drama" )
    public ResponseEntity<List<Contenido>> mostrarTodosLosContenidosDeDrama (){
    
        List<Contenido> contenidos = firebaseContenidoService.listarContenidosDrama();
        contenidos = firebaseContenidoService.ordenarListaContenidos(contenidos);
        ResponseEntity response = new ResponseEntity(contenidos, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "/comedia" )
    public ResponseEntity<List<Contenido>> mostrarTodosLosContenidosDeComedia (){
    
        List<Contenido> contenidos = firebaseContenidoService.listarContenidosComedia();
        contenidos = firebaseContenidoService.ordenarListaContenidos(contenidos);
        ResponseEntity response = new ResponseEntity(contenidos, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "/accion" )
    public ResponseEntity<List<Contenido>> mostrarTodosLosContenidosDeAccion (){
    
        List<Contenido> contenidos = firebaseContenidoService.listarContenidosAcción();
        contenidos = firebaseContenidoService.ordenarListaContenidos(contenidos);
        ResponseEntity response = new ResponseEntity(contenidos, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "/series" )
    public ResponseEntity<List<Contenido>> mostrarTodosLosContenidosDeSeries(){
    
        List<Contenido> contenidos = firebaseContenidoService.listarContenidosSeries();
        contenidos = firebaseContenidoService.ordenarListaContenidos(contenidos);
        ResponseEntity response = new ResponseEntity(contenidos, HttpStatus.OK);
        return response;
    }

    // Llamada CREATE: crea un contenido, con llamada HTTP tipo POST.
    @PostMapping("/nuevoContenido")
    public ResponseEntity crearNuevoContenido (  @RequestBody ContenidoDTO contenidoDTO ){
        
        Contenido contenido = this.contenidoMapper.comoContenido(contenidoDTO);
        String response = firebaseContenidoService.crearContenido(contenido);
        return new ResponseEntity( response, HttpStatus.OK );
    }

    
}
