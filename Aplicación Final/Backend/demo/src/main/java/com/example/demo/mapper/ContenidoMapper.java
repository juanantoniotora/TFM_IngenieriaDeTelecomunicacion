package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ContenidoDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.Contenido;

@Component
public class ContenidoMapper{


    public Contenido comoContenido(ContenidoDTO src) {
        
        if(src == null){
            return null;
        }
        Contenido contenido = new Contenido();
        contenido.setAnho(src.getAnho());
        contenido.setDescripcion(src.getDescripcion());
        contenido.setGenerosPertenece(src.getGenerosPertenece());
        contenido.setIdContenido(src.getIdContenido());
        contenido.setLinkVideo(src.getLinkVideo());
        contenido.setNombre(src.getNombre());
        contenido.setTipoContenido(src.getTipoContenido());
        return contenido;
    }

    public ContenidoDTO comoContenidoDTO(Contenido src) {
        if(src == null){
            return null;
        }
        ContenidoDTO contenidoDTO = new ContenidoDTO();
        contenidoDTO.setAnho(src.getAnho());
        contenidoDTO.setDescripcion(src.getDescripcion());
        contenidoDTO.setGenerosPertenece(src.getGenerosPertenece());
        contenidoDTO.setIdContenido(src.getIdContenido());
        contenidoDTO.setLinkVideo(src.getLinkVideo());
        contenidoDTO.setNombre(src.getNombre());
        contenidoDTO.setTipoContenido(src.getTipoContenido());
        return contenidoDTO;
    }
}
