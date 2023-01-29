package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Tora;
import com.example.demo.repository.ToraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class TestService {
    
    @Autowired
    ToraRepository toraRepository;

    @Value("${miProperty}")
    private String prefijo;
    
    @Value("${miproperty2}")
    private String sufijo;

    public TestService (){
        this.prefijo = "llamo al constructor";
        this.sufijo  = "11";
    }

    public String metodoServicio1(String str){
        return (prefijo + str);
    }

    public void crearToraServicio(String variableStringEntrante){
        Tora tora = new Tora();
        tora.setIlusion(variableStringEntrante);
        toraRepository.save(tora);
    }
    //la entity es una tabla en bbdd y el repositorio es una interfaz para acceder a BBDD sin tener que comerme cabeza con sesiones, trnasacciones, etc
    public List<Tora> metodoDevuelveListaToras(){

        return toraRepository.findAll();
    }

    // ESTE METODO ES EL DELETE
    public void eliminarToraServicio(Long id){
        //Tora queryTora = new Tora();
        Optional<Tora> contenedorOptionalDeFilaTora = toraRepository.findById(id); 
        if (contenedorOptionalDeFilaTora.isPresent() ){ // si me devuelve true esta este optional y si false es que no esta
            Tora filaToraRecuperado = contenedorOptionalDeFilaTora.get();
            toraRepository.delete(filaToraRecuperado);
        }
    }

    // ESTE METODO ES EL MODIFIER
    public void modificarTora(Long id, String valorModificada){
        //Tora queryTora = new Tora();
        Optional<Tora> contenedorOptionalDeFilaTora = toraRepository.findById(id); 
        if (contenedorOptionalDeFilaTora.isPresent() ){ // si me devuelve true esta este optional y si false es que no esta
            Tora filaToraRecuperado = contenedorOptionalDeFilaTora.get();
            filaToraRecuperado.setIlusion(valorModificada);
            toraRepository.save(filaToraRecuperado);
        
        }
    }
}
