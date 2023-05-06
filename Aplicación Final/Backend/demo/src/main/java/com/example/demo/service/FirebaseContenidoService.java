package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Contenido;
import com.example.demo.model.Usuario;

public interface FirebaseContenidoService {

    List<Contenido> listarContenidosCatalogo();
    List<Contenido> listarContenidosTendencias();
    List<Contenido> listarContenidosDrama();
    List<Contenido> listarContenidosComedia();
    List<Contenido> listarContenidosAcción();
    List<Contenido> listarContenidosSeries();
    String crearContenido(Contenido contenido);

    List<Contenido> ordenarListaContenidos (List<Contenido> contenidos);
}

