package com.example.demo.model;

public class Contenido {
    
    private Long anho;
    private String descripcion;
    private String genrosPertenece;
    private Integer idContenido;
    private String linkVideo;
    private String nombre;
    private String tipoContenido;

    
    public Long getAnho(){
        return this.anho;
    }

    public void setAnho(Long anho){
        this.anho= anho;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion= descripcion;
    }
    
    public String getGenrosPertenece(){
        return this.genrosPertenece;
    }

    public void setGenrosPertenece(String genrosPertenece){
        this.genrosPertenece= genrosPertenece;
    }

    public Integer getIdContenido(){
        return this.idContenido;
    }

    public void setIdContenido(Integer idContenido){
        this.idContenido= idContenido;
    }

    public String getLinkVideo(){
        return this.linkVideo;
    }

    public void setLinkVideo(String linkVideo){
        this.linkVideo= linkVideo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre= nombre;
    }

    public String getTipoContenido(){
        return this.tipoContenido;
    }

    public void setTipoContenido(String tipoContenido){
        this.tipoContenido= tipoContenido;
    }
}
