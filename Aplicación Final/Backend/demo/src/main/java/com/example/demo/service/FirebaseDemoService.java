package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Usuario;

public interface FirebaseDemoService {
    
    List<Usuario> listarUsuariosFirebase();

    Long crearUsuarioFirebase(Usuario usuario);

    Boolean modificarUsuarioFirebase(String id, Usuario usuario);

    Boolean eliminarUsuarioPorIdFirebase(String id);

    List<Usuario> ordenarListaUsuarios (List<Usuario> usuarios, String ordenadosPor);
    
}
