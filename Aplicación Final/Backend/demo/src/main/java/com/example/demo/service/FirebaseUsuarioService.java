package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Usuario;

public interface FirebaseUsuarioService {
    
    List<Usuario> listarUsuariosFirebase();

    String crearUsuarioFirebase(Usuario usuario, String idUsuario);

    Boolean modificarUsuarioFirebase(String id, Usuario usuario);

    Boolean eliminarUsuarioPorIdFirebase(String id);

    List<Usuario> ordenarListaUsuarios (List<Usuario> usuarios, String ordenadosPor);
    
}
