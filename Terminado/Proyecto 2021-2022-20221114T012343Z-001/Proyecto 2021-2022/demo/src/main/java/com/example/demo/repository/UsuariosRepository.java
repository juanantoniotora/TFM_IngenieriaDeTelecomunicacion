package com.example.demo.repository;

import com.example.demo.entity.UsuariosEntity;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.jpa.repository.JpaRepository;

//El primer parametro es el tipo de la clase de la cual quiero hacer el repositorio
//el segundo es el tipo del ID de esa clase
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long>   {
    
}

// el repositorio es la interfaz a traves de la cual yo voy a escribir en BBDD esa entidad