package com.example.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.firebase.FirebaseInitializer;
import com.example.demo.model.ResponseString;
import com.example.demo.model.Usuario;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.rpc.context.AttributeContext.Response;

import org.apache.http.protocol.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseUsuarioServiceImpl implements FirebaseUsuarioService {

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
                    usuario.setId( doc.getId().toString() );
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

    @Override
    public ResponseString probarSiExisteUsuarioFirebase(String idUsuarioActual) {
        List<Usuario> response = new ArrayList<>();
        Usuario usuario;
        ResponseString result= new ResponseString();

        ApiFuture<QuerySnapshot> querySnapshotApiFeature = obtenerColeccion().get();
        try {
            for(DocumentSnapshot doc : querySnapshotApiFeature.get().getDocuments()){
                usuario = doc.toObject(Usuario.class);
                try{
                    usuario.setId( doc.getId().toString() );
                } catch(Exception e) {
                    e.getMessage();
                }
                if( idUsuarioActual.equals(usuario.getId()) ){
                    result.setResult("true");
                    break;
                } else {
                    result.setResult("false");
                }
            }
            return result;
        } 
        catch (Exception e) {
            result.setResult("false");
            return result;
        }
    }


    @Override
    public Usuario mostrarUsuarioActual(String idUsuario){
        Usuario response = new Usuario();
        Usuario usuario;
        ApiFuture<QuerySnapshot> querySnapshotApiFeature = obtenerColeccion().get();
        try {
            for(DocumentSnapshot doc : querySnapshotApiFeature.get().getDocuments()){
                usuario = doc.toObject(Usuario.class);
                if(usuario.getId().equals(idUsuario)){
                    return usuario;
                }
            }
            return response;
        }  catch (Exception e) {
            return null;
        }
    }

    // servicio CREATE
    @Override
    public String crearUsuarioFirebase(Usuario usuario, String idUsuario) {
        Map<String, Object> documentoUsuarios = new HashMap<>();
        documentoUsuarios = obtenerUsuariosFirebase(usuario);
        Long datetime = System.currentTimeMillis();
        ApiFuture<WriteResult> feature = obtenerColeccion().document(String.valueOf(idUsuario)).create(documentoUsuarios);
        try{
            if(feature.get() !=null){
                return idUsuario;
            }
            return "-1";
        }
        catch(Exception e){
            return "-1";
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
        return firebase.getFirestore().collection("Usuarios");
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
        documentoUsuariosMap.put("id", usuario.getId());
        return documentoUsuariosMap;
    }
}
