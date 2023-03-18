package com.example.demo.mapper;
import com.example.demo.entity.UsuariosEntity;
import com.example.demo.model.Usuario;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;;

@Component
public class UsuarioEntityMapper {
    
    /**
     * Transforma obj. UsuarioEntity en Usuario
     * @param src
     * @return un Usuario
     */
    public Usuario comoUsuario(UsuariosEntity src) {
        
        Usuario usuario = new Usuario();

        usuario.setActivado(src.getActivado());
        usuario.setAficion(src.getAficion());
        usuario.setApellidos(src.getApellidos());
        usuario.setEdad(src.getEdad());
        usuario.setGenero(src.getGenero());
        usuario.setId(src.getId());
        usuario.setNombre(src.getNombre());
        usuario.setTelefono(src.getTelefono());
        return usuario;
    }

    /**
     * Transforma obj. List de UsuarioEntity en List de Usuario
     * @param src como lista de obj. UsuariosEntity
     * @return lista de Usuario
     */
    public List<Usuario> comoListaDeUsuarios(List<UsuariosEntity> src) {
        
        List<Usuario> listaUsuarios = new ArrayList<>(src.size());
        for (UsuariosEntity u : src){
            listaUsuarios.add(comoUsuario(u) );
        }
        return listaUsuarios;
    }
}
