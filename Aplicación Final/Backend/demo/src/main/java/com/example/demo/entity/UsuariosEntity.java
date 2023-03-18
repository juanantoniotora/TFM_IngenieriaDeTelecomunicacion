package com.example.demo.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UsuariosEntity {
    
    // fields, getters and setters
    @Id
    @GeneratedValue
    public Long id;

    private String nombre;
    private String apellidos;
    private Integer edad;
    private boolean activado;
    private Long telefono;
    private int genero;
    private String aficion;


    // constructor sin parametros
    public UsuariosEntity() {
    }

    // constructor parametrizado
    public UsuariosEntity(Long id,  String nombre, String apellidos, Integer edad, 
            boolean activado, Long telefono, int genero, String aficion) {
        this.id = id;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.edad=edad;
        this.activado=activado;
        this.telefono=telefono;
        this.genero=genero;
        this.aficion = aficion;
    }

    /**
     * Method getter for the ID
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Method setter for the ID
     * @param id
     * @return void
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method getter for the nombre
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Method setter for the nombre
     * @param nombre
     * @return void
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Method getter for the apellidos
     * @return the apellidos
     */
    public String getApellidos() {
        return this.apellidos;
    }

    /**
     * Method setter for the apellidos
     * @param apellidos
     * @return void
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Method getter for the edad
     * @return the edad
     */
    public Integer getEdad() {
        return this.edad;
    }

    /**
     * Method setter for the edad
     * @param edad
     * @return void
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * Method getter for the activado
     * @return the activado
     */
    public boolean getActivado() {
        return this.activado;
    }

    /**
     * Method setter for the activado
     * @param activado
     * @return void
     */
    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    /**
     * Method getter for the telefono
     * @return the telefono
     */
    public Long getTelefono() {
        return this.telefono;
    }

    /**
     * Method setter for the telefono
     * @param telefono
     * @return void
     */
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    /**
     * Method getter for the genero
     * @return the genero
     */
    public int getGenero() {
        return this.genero;
    }

    /**
     * Method setter for the genero
     * @param genero
     * @return void
     */
    public void setGenero(int genero) {
        this.genero = genero;
    }

    /**
     * Method getter for the aficion
     * @return a String value
     */
    public String getAficion() {
        return this.aficion;
    }

    /**
     * Method setter for the ilusion
     * @param aficion
     * @return void
     */
    public void setAficion(String aficion) {
        this.aficion = aficion;
    }

}