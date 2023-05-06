package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.firebase.FirebaseInitializer;
import com.example.demo.model.Contenido;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseContenidoServiceImpl implements FirebaseContenidoService {

    @Autowired
    private FirebaseInitializer firebase;
    
    @Override
    public List<Contenido> listarContenidosCatalogo(){
        List<Contenido> response = new ArrayList<>();
        Contenido contenido;
        ApiFuture<QuerySnapshot> querySnapshotApiFeature = obtenerColeccion().get();
        try {
            for(DocumentSnapshot doc : querySnapshotApiFeature.get().getDocuments()){
                
                contenido = doc.toObject(Contenido.class);
                try{
                    contenido.setIdContenido( Integer.valueOf(doc.getId()) );
                    response.add(contenido);
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
    public List<Contenido> listarContenidosTendencias(){
        List<Contenido> response = new ArrayList<>();
        Contenido contenido;
        ApiFuture<QuerySnapshot> querySnapshotApiFeature = obtenerColeccion().get();
        try {
            for(DocumentSnapshot doc : querySnapshotApiFeature.get().getDocuments()){
                contenido = doc.toObject(Contenido.class);
                List<String> generosPertenece = Arrays.asList(contenido.getGenrosPertenece().split(","));
                if(generosPertenece.contains("tendencias")){
                    try{
                        contenido.setIdContenido( Integer.valueOf(doc.getId()) );
                        response.add(contenido);
                    }
                    catch(Exception e) {
                        e.getMessage();
                    }
                }
            }
            return response;
        } 
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Contenido> listarContenidosDrama(){
        List<Contenido> response = new ArrayList<>();
        Contenido contenido;
        ApiFuture<QuerySnapshot> querySnapshotApiFeature = obtenerColeccion().get();
        try {
            for(DocumentSnapshot doc : querySnapshotApiFeature.get().getDocuments()){
                contenido = doc.toObject(Contenido.class);
                List<String> generosPertenece = Arrays.asList(contenido.getGenrosPertenece().split(","));
                if(generosPertenece.contains("drama")){
                    try{
                        contenido.setIdContenido( Integer.valueOf(doc.getId()) );
                        response.add(contenido);
                    }
                    catch(Exception e) {
                        e.getMessage();
                    }
                }
            }
            return response;
        } 
        catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Contenido> listarContenidosComedia(){
        List<Contenido> response = new ArrayList<>();
        Contenido contenido;
        ApiFuture<QuerySnapshot> querySnapshotApiFeature = obtenerColeccion().get();
        try {
            for(DocumentSnapshot doc : querySnapshotApiFeature.get().getDocuments()){
                contenido = doc.toObject(Contenido.class);
                List<String> generosPertenece = Arrays.asList(contenido.getGenrosPertenece().split(","));
                if(generosPertenece.contains("comedia")){
                    try{
                        contenido.setIdContenido( Integer.valueOf(doc.getId()) );
                        response.add(contenido);
                    }
                    catch(Exception e) {
                        e.getMessage();
                    }
                }
            }
            return response;
        } 
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Contenido> listarContenidosAcción(){
        List<Contenido> response = new ArrayList<>();
        Contenido contenido;
        ApiFuture<QuerySnapshot> querySnapshotApiFeature = obtenerColeccion().get();
        try {
            for(DocumentSnapshot doc : querySnapshotApiFeature.get().getDocuments()){
                contenido = doc.toObject(Contenido.class);
                List<String> generosPertenece = Arrays.asList(contenido.getGenrosPertenece().split(","));
                if(generosPertenece.contains("accion")){
                    try{
                        contenido.setIdContenido( Integer.valueOf(doc.getId()) );
                        response.add(contenido);
                    }
                    catch(Exception e) {
                        e.getMessage();
                    }
                }
            }
            return response;
        } 
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Contenido> listarContenidosSeries(){
        List<Contenido> response = new ArrayList<>();
        Contenido contenido;
        ApiFuture<QuerySnapshot> querySnapshotApiFeature = obtenerColeccion().get();
        try {
            for(DocumentSnapshot doc : querySnapshotApiFeature.get().getDocuments()){
                contenido = doc.toObject(Contenido.class);
                List<String> generosPertenece = Arrays.asList(contenido.getGenrosPertenece().split(","));
                if(generosPertenece.contains("series")){
                    try{
                        contenido.setIdContenido( Integer.valueOf(doc.getId()) );
                        response.add(contenido);
                    }
                    catch(Exception e) {
                        e.getMessage();
                    }
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
    public String crearContenido(Contenido contenido) {
        Map<String, Object> documentoUsuarios = new HashMap<>();
        documentoUsuarios = obtenerContenidosFirebase(contenido);
        Long datetime = System.currentTimeMillis();
        ApiFuture<WriteResult> feature = obtenerColeccion().document(String.valueOf(datetime)).create(documentoUsuarios);
        try{
            if(feature.get() !=null){
                return datetime.toString();
            }
            return "-1";
        }
        catch(Exception e){
            return "-1";
        }
    }


    // metodo que ordena los Usuarios mostrados en operaciones GET HTTP, segun id, nombre o edad 
    @Override
     public List<Contenido> ordenarListaContenidos (List<Contenido> contenidos){
        contenidos.sort(Comparator.comparing(Contenido::getIdContenido));
        return contenidos;
    }

    private CollectionReference obtenerColeccion (){
        return firebase.getFirestore().collection("Contenidos");
    }

    private Map<String, Object> obtenerContenidosFirebase(Contenido contenido){
        Map<String, Object> documentoContenidosMap = new HashMap<>();
        documentoContenidosMap.put("nombre", contenido.getNombre());
        documentoContenidosMap.put("anho", contenido.getAnho());
        documentoContenidosMap.put("descripcion", contenido.getDescripcion());
        documentoContenidosMap.put("genrosPertenece", contenido.getGenrosPertenece());
        documentoContenidosMap.put("idContenido", contenido.getIdContenido());
        documentoContenidosMap.put("linkVideo", contenido.getLinkVideo());
        documentoContenidosMap.put("nombre", contenido.getNombre());
        documentoContenidosMap.put("tipoContenido", contenido.getTipoContenido());
        return documentoContenidosMap;
    }
}
