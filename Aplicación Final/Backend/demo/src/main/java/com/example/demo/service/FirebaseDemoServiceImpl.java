package com.example.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.firebase.FirebaseInitializer;
import com.example.demo.model.Usuario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseDemoServiceImpl implements FirebaseDemoService {

    @Autowired
    private FirebaseInitializer firebase;
    
    // servicio READ
    @Override
    public List<Usuario> listarUsuariosFirebase() {
        List<Usuario> response = new ArrayList<>();
        Usuario usuario;

        ApiFuture<QuerySnapshot> querySnapshotApiFeature = obtenerColeccion().get();
        try {
            for(DocumentSnapshot doc : querySnapshotApiFeature.get().getDocuments()){
                
                usuario = doc.toObject(Usuario.class);
                try{
                    usuario.setId( Long.parseLong(doc.getId()) );
                    response.add(usuario);
                }
                catch(Exception e) {
                    e.getMessage();
                }
            }
            return response;
        } 
        catch (Exception e) {
            return null;
        }
    }

    // servicio CREATE
    @Override
    public Long crearUsuarioFirebase(Usuario usuario) {
        Map<String, Object> documentoUsuarios = new HashMap<>();
        documentoUsuarios = obtenerUsuariosFirebase(usuario);
        Long datetime = System.currentTimeMillis();
        Long idUsuarioNuevo = datetime;
        ApiFuture<WriteResult> feature = obtenerColeccion().document(String.valueOf(idUsuarioNuevo)).create(documentoUsuarios);
        try{
            if(feature.get() !=null){
                return idUsuarioNuevo;
            }
            return (long)-1;
        }
        catch(Exception e){
            return (long) -1;
        }
    }

    // servicio UPDATE
    @Override
    public Boolean modificarUsuarioFirebase(String id, Usuario usuario) {
        Map<String, Object> documentoUsuarios = new HashMap<>();
        documentoUsuarios = obtenerUsuariosFirebase(usuario);
        ApiFuture<WriteResult> feature = obtenerColeccion().document(id).set(documentoUsuarios);
        try{
            if(feature.get() !=null){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
        catch(Exception e){
            return Boolean.FALSE;
        }
    }

    // servicio DELETE
    @Override
    public Boolean eliminarUsuarioPorIdFirebase(String id) {
        ApiFuture<WriteResult> feature = obtenerColeccion().document(id).delete();
        try{
            if(feature.get() !=null){
                return Boolean.TRUE;
            }
            return null;
        }
        catch(Exception e){
            return null;
        }
    }

    // metodo que ordena los Usuarios mostrados en operaciones GET HTTP, segun id, nombre o edad 
    @Override
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

    // metodo interno 1: localiza referencia de mi collection Firebase
    private CollectionReference obtenerColeccion (){
        return firebase.getFirestore().collection("usuarios-coleccion");
    }

    // metodo interno 2: rellena el hashMap a partir del Usuario entrante y devuelve el hashMap completado
    private Map<String, Object> obtenerUsuariosFirebase(Usuario usuario){
        Map<String, Object> documentoUsuariosMap = new HashMap<>();
        documentoUsuariosMap.put("nombre", usuario.getNombre());
        documentoUsuariosMap.put("apellidos", usuario.getApellidos());
        documentoUsuariosMap.put("edad", usuario.getEdad());
        documentoUsuariosMap.put("activado", usuario.getActivado());
        documentoUsuariosMap.put("telefono", usuario.getTelefono());
        documentoUsuariosMap.put("genero", usuario.getGenero());
        documentoUsuariosMap.put("aficion", usuario.getAficion());
        return documentoUsuariosMap;
    }
}
