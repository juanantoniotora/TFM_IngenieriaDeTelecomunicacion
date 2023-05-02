import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';

@Injectable({
  providedIn: 'root'
})

export class ServicioUsuariosService {

  private url:string = "http://localhost:8080"
  private nombreNuevoUsuario:string="";

  constructor(private http:HttpClient) { }

  getAll():Observable<Usuario[]>{
    console.log("entro en el servicio getAll");
    return this.http.get<Usuario[]>(this.url+"?ordenadosPor=edad");
  }
  //createNewUser():String{
  //  console.log("entro en el servicio creNewUser");
  //  this.nombreNuevoUsuario:String = this.http.post(this.url+"/crearusuario/", Usuario);
  //  return "";
  //}
}
