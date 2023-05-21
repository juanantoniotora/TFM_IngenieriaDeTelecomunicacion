package com.example.demo.dto;
import java.io.Serializable;

public class UsuarioDTO implements Serializable{
    private String id;
    private String nombre;
    private String apellidos;
    private Integer edad;
    private boolean activado;
    private Long telefono;
    private int genero;
    private String aficion;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id= id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre= nombre;
    }

    public String getApellidos(){
        return this.apellidos;
    }

    public void setApellidos(String apellidos){
        this.apellidos= apellidos;
    }
    
    public Integer getEdad(){
        return this.edad;
    }

    public void setEdad(Integer edad){
        this.edad=edad;
    }

    public boolean getActivado(){
        return this.activado;
    }

    public void setActivado(boolean activado){
        this.activado=activado;
    }

    public Long getTelefono(){
        return this.telefono;
    }

    public void setTelefono(Long telefono){
        this.telefono=telefono;
    }

    public int getGenero(){
        return this.genero;
    }

    public void setGenero(int genero){
        this.genero=genero;
    }

    public String getAficion() {
        return this.aficion;
    }

    public void setAficion(String aficion) {
        this.aficion = aficion;
    }
}
