import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from './usuario';
import { Observable } from 'rxjs';
import { Contenido } from './contenido';

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

}
