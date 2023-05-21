package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.firebase.FirebaseInitializer;
import com.example.demo.model.Contenido;
import com.example.demo.model.ResponseString;
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
                    contenido.setIdContenido( Long.valueOf(doc.getId()) );
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
                List<String> generosPertenece = Arrays.asList(contenido.getGenerosPertenece().split(","));
                if(generosPertenece.contains("tendencia")){
                    try{
                        contenido.setIdContenido( Long.valueOf(doc.getId()) );
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
                List<String> generosPertenece = Arrays.asList(contenido.getGenerosPertenece().split(","));
                if(generosPertenece.contains("drama")){
                    try{
                        contenido.setIdContenido( Long.valueOf(doc.getId()) );
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
                List<String> generosPertenece = Arrays.asList(contenido.getGenerosPertenece().split(","));
                if(generosPertenece.contains("comedia")){
                    try{
                        contenido.setIdContenido( Long.valueOf(doc.getId()) );
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
                List<String> generosPertenece = Arrays.asList(contenido.getGenerosPertenece().split(","));
                if(generosPertenece.contains("accion")){
                    try{
                        contenido.setIdContenido( Long.valueOf(doc.getId()) );
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
                List<String> tipoContenido = Arrays.asList(contenido.getTipoContenido().split(","));
                if(tipoContenido.contains("serie")){
                    try{
                        contenido.setIdContenido( Long.valueOf(doc.getId()) );
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
    public Contenido conseguirDetallesContenidoSeleccionado(Long idContenidoSeleccionado){
        Contenido response = new Contenido();
        Contenido contenido;
        ApiFuture<QuerySnapshot> querySnapshotApiFeature = obtenerColeccion().get();
        try {
            for(DocumentSnapshot doc : querySnapshotApiFeature.get().getDocuments()){
                contenido = doc.toObject(Contenido.class);
                Long p= contenido.getIdContenido();
                if(p.longValue()==idContenidoSeleccionado.longValue()){
                    return contenido;
                }
            }
            return response;
        }  catch (Exception e) {
            return null;
        }
    }


    // servicio CREATE
    @Override
    public ResponseString crearContenido(Contenido contenido) {
        Map<String, Object> documentoUsuarios = new HashMap<>();
        Long datetime = System.currentTimeMillis() / 1000;
        documentoUsuarios = obtenerContenidosFirebase(contenido, datetime);
        ApiFuture<WriteResult> feature = obtenerColeccion().document(String.valueOf(datetime)).create(documentoUsuarios);
        ResponseString response= new ResponseString();
        try{
            if(feature.get() !=null){
                response.setResult(datetime.toString());
                return response;
            }
            response.setResult("-1");
            return response;
        }
        catch(Exception e){
            response.setResult("-1");
            return response;
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

    private Map<String, Object> obtenerContenidosFirebase(Contenido contenido, Long idContenido){
        Map<String, Object> documentoContenidosMap = new HashMap<>();
        documentoContenidosMap.put("nombre", contenido.getNombre());
        documentoContenidosMap.put("anho", contenido.getAnho());
        documentoContenidosMap.put("descripcion", contenido.getDescripcion());
        documentoContenidosMap.put("generosPertenece", contenido.getGenerosPertenece());
        documentoContenidosMap.put("idContenido", idContenido);
        documentoContenidosMap.put("linkVideo", contenido.getLinkVideo());
        documentoContenidosMap.put("nombre", contenido.getNombre());
        documentoContenidosMap.put("tipoContenido", contenido.getTipoContenido());
        return documentoContenidosMap;
    }
}
