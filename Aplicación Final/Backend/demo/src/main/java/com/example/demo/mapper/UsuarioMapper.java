package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.Usuario;

@Component
public class UsuarioMapper{

    /**
     * Transforma un UsuarioDTO a Usuario
     * @param src
     * @param aficion
     * @return obj. tipo Usuario
     */
    public Usuario comoUsuario(UsuarioDTO src, String aficion) {
        
        if(src == null){
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setActivado(src.getActivado());
        usuario.setAficion(aficion);
        usuario.setApellidos(src.getApellidos());
        usuario.setEdad(src.getEdad());
        usuario.setGenero(src.getGenero());
        usuario.setId(src.getId());
        usuario.setNombre(src.getNombre());
        usuario.setTelefono(src.getTelefono());
        return usuario;
    }

    /**
     * Transforma un Usuario a UsuarioDTO
     * @param src
     * @return obj. tipo UsuarioDTO
     */
    public UsuarioDTO comoUsuarioDTO(Usuario src) {
        if(src == null){
            return null;
        }
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setActivado(src.getActivado());
        usuarioDTO.setAficion(src.getAficion());
        usuarioDTO.setApellidos(src.getApellidos());
        usuarioDTO.setEdad(src.getEdad());
        usuarioDTO.setGenero(src.getGenero());
        usuarioDTO.setId(src.getId());
        usuarioDTO.setNombre(src.getNombre());
        usuarioDTO.setTelefono(src.getTelefono());
        return usuarioDTO;
    }
}
