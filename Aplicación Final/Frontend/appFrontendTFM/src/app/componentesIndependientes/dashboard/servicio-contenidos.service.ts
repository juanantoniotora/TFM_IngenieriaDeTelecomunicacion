import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Contenido } from './contenido';
import { ResponseString } from 'src/modelos/ResponseString';
import { ContenidoDTO } from 'src/DTO/ContenidoDTO';

@Injectable({
  providedIn: 'root'
})
export class ServicioContenidosService {

  private url:string = "http://localhost:8080"

  constructor(private http:HttpClient) { }

  getCatalogoContenidos():Observable<Contenido[]>{
    console.log("entro en el servicio getCatalogoContenidos");
    return this.http.get<Contenido[]>(this.url+"/contenidos/catalogo");
  }

  getTendenciasContenidos():Observable<Contenido[]>{
    console.log("entro en el servicio getTendenciasContenidos");
    return this.http.get<Contenido[]>(this.url+"/contenidos/tendencias");
  }

  getDramaContenidos():Observable<Contenido[]>{
    console.log("entro en el servicio getDramaContenidos");
    return this.http.get<Contenido[]>(this.url+"/contenidos/drama");
  }

  getComediaContenidos():Observable<Contenido[]>{
    console.log("entro en el servicio getComediaContenidos");
    return this.http.get<Contenido[]>(this.url+"/contenidos/comedia");
  }

  getAccionContenidos():Observable<Contenido[]>{
    console.log("entro en el servicio getAccionContenidos");
    return this.http.get<Contenido[]>(this.url+"/contenidos/accion");
  }

  getSeriesContenidos():Observable<Contenido[]>{
    console.log("entro en el servicio getSeriesContenidos");
    return this.http.get<Contenido[]>(this.url+"/contenidos/series");
  }

  getDetallesContenidoSeleccionado( idContenidoSeleccionado: Number ):Observable<Contenido>{
    console.log("entro en el servicio getDetallesContenidoSeleccionado");
    return this.http.get<Contenido>(this.url+"/contenidos/detallesContenidoSeleccionado/"+idContenidoSeleccionado);
  }

  crearContenido(contenidoDTO:any): Observable<any> {
    return this.http.post<ResponseString>(this.url+"/contenidos/nuevoContenido", contenidoDTO);
  }

}
