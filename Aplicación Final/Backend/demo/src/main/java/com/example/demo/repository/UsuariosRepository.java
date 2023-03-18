package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UsuariosEntity;

// El primer parametro es el tipo de la clase de la cual quiero hacer el repositorio.
// El segundo parametro es el tipo del ID de esa clase.
// El repositorio es la interfaz a traves de la cual yo voy a escribir en BBDD esa entidad.
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long>   {
    
}