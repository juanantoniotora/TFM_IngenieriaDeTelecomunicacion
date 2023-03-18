package com.example.demo.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import com.example.demo.entity.UsuariosEntity;
import com.example.demo.mapper.UsuarioEntityMapper;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class DemoService {
    
    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    private UsuarioEntityMapper usuarioEntityMapper;

    @Value("${propiedad1}")
    private String prefijo;
    @Value("${propiedad2}")
    private String sufijo;
    public DemoService (){
        this.prefijo = "relleno desde el constructor del service.";
        this.sufijo  = " relleno desde el constructor del service.";
    }

    // servicio CREATE
    public Long crearUsuario(Usuario src){
        
        UsuariosEntity usuariosEntity = new UsuariosEntity();

        // Entra un usuario con ID null porque aun no se asigno ID
        // Por eso  settear todo excepto el ID
        usuariosEntity.setNombre(src.getNombre());
        usuariosEntity.setApellidos(src.getApellidos());
        usuariosEntity.setEdad(src.getEdad());
        usuariosEntity.setActivado(src.getActivado());
        usuariosEntity.setTelefono(src.getTelefono());
        usuariosEntity.setGenero(src.getGenero());
        usuariosEntity.setAficion(src.getAficion());
        
        UsuariosEntity usuarioGuardadoEnDB = usuariosRepository.save(usuariosEntity);
        Usuario usuario = usuarioEntityMapper.comoUsuario(usuarioGuardadoEnDB);
        return usuario.getId();
    }

    // servicio READ
    public List<Usuario> devolverTodosLosUsuarios(){
        
        List<UsuariosEntity> usuariosEntity = usuariosRepository.findAll();
        List<Usuario> usuarios = usuarioEntityMapper.comoListaDeUsuarios(usuariosEntity);
        return usuarios;
    }

    // servicio UPDATE
    public Usuario modificarUsuario(Long id, String valorModificada){
        
        Optional<UsuariosEntity> usuarioOpcional = usuariosRepository.findById(id); 
        if (usuarioOpcional.isPresent() ){ 
            // si me devuelve true esta este optional
            // si me devuelve false no esta en el optional
            UsuariosEntity usuarioRecuperado = usuarioOpcional.get();
            usuarioRecuperado.setAficion(valorModificada);
            UsuariosEntity usuarioEntityActualizado = usuariosRepository.save(usuarioRecuperado);
            Usuario usuarioActualizado = usuarioEntityMapper.comoUsuario(usuarioEntityActualizado);
            return usuarioActualizado;
        }
        else{
            return null;
        }
    }

    // servicio DELETE
    public Long eliminarUsuarioPorId(Long id){
        
        Optional<UsuariosEntity> usuarioOpcional = usuariosRepository.findById(id); 
        if (usuarioOpcional.isPresent() ){ 
            // si me devuelve true esta este optional
            // si me devuelve false no esta en el optional
            UsuariosEntity usuarioRecuperado = usuarioOpcional.get();
            usuariosRepository.delete(usuarioRecuperado);
            return id;
        }
        else{
            return null;
        }
    }

    

    /********************************************************************************
     ********* Ordenamiento de los usuarios segun valor de RequesParam **************
     ********************************************************************************/
    public List<Usuario> ordenarListaUsuarios (List<Usuario> usuarios, String ordenadosPor){
        Boolean ordenado = false;
        if(ordenadosPor.equals("id")){
            usuarios.sort(Comparator.comparing(Usuario::getId));
            ordenado = true;
        }
        if(ordenadosPor.equals("nombre")){
            usuarios.sort(Comparator.comparing(Usuario::getNombre));
            ordenado = true;
        }
        if(ordenadosPor.equals("edad")){
            usuarios.sort(Comparator.comparing(Usuario::getEdad));
            ordenado = true;
        }
        if(!ordenado){
            usuarios.sort(Comparator.comparing(Usuario::getId));
        }
        return usuarios;
    }

    

    /********************************************************************************
    ************ Consiguiendo valores desde el constructor del service **************
    *************************************** vs **************************************
    ************ Consiguiendo valores desde el fichero "application.property" *******
    *********************************************************************************/

    public String consiguePrefijoSufijoDelConstructor(){
        return ("prefijo: " + prefijo + "\nsufijo: " + sufijo + "\n");
    }

    public String consiguePrefijoSufijoDeApplicationProperties(){
        return ("prefijo: " + prefijo + "\nsufijo: " + sufijo + "\n");
    }

}
