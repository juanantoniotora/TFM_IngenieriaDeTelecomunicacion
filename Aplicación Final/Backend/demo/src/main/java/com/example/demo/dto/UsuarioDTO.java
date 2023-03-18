package com.example.demo.dto;
import java.io.Serializable;

public class UsuarioDTO implements Serializable{

    private Long id;
    private String nombre;
    private String apellidos;
    private Integer edad;
    private boolean activado;
    private Long telefono;
    private int genero;
    private String aficion;

    /**
     * Getter del ID
     * @return id
     */
    public Long getId(){
        return this.id;
    }

    /**
     * Setter del ID
     * @param id
     */
    public void setId(Long id){
        this.id= id;
    }

    /**
     * Getter del NOMBRE
     * @return nombre
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Setter del NOMBRE
     * @param nombre
     */
    public void setNombre(String nombre){
        this.nombre= nombre;
    }

    /**
     * Getter del APELLIDOS
     * @return apellidos
     */
    public String getApellidos(){
        return this.apellidos;
    }

    /**
     * Setter del APELLIDOS
     * @param apellidos
     */
    public void setApellidos(String apellidos){
        this.apellidos= apellidos;
    }
    
    /**
     * Getter del EDAD
     * @return edad
     */
    public Integer getEdad(){
        return this.edad;
    }

    /**
     * Setter del EDAD
     * @param edad
     */
    public void setEdad(Integer edad){
        this.edad=edad;
    }

    /**
     * Getter del ACTIVADO
     * @return activado
     */
    public boolean getActivado(){
        return this.activado;
    }

    /**
     * Setter del ACTIVADO
     * @param activado
     */
    public void setActivado(boolean activado){
        this.activado=activado;
    }

    /**
     * Getter del TELEFONO
     * @return telefono
     */
    public Long getTelefono(){
        return this.telefono;
    }

    /**
     * Setter del TELEFONO
     * @param telefono
     */
    public void setTelefono(Long telefono){
        this.telefono=telefono;
    }

    /**
     * Getter del GENERO
     * @return genero
     */
    public int getGenero(){
        return this.genero;
    }

    /**
     * Setter del GENERO
     * @param genero
     */
    public void setGenero(int genero){
        this.genero=genero;
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
