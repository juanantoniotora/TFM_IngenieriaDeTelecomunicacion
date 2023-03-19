import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';

@Injectable({
  providedIn: 'root'
})

export class ServicioUsuariosService {

  private url:string = "http://localhost:8080"
  
  constructor(private http:HttpClient) { }

  getAll():Observable<Usuario[]>{
    console.log("entro en el servicio");
    return this.http.get<Usuario[]>(this.url+"?ordenadosPor=edad");
  }
}
