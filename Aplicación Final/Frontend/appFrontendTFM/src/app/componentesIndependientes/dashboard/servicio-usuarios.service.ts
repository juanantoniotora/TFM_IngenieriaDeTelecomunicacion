import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseString } from 'src/modelos/ResponseString';
import { Usuario } from 'src/modelos/usuario';



@Injectable({
  providedIn: 'root'
})

export class ServicioUsuariosService {

  private url:string = "http://localhost:8080"
  private nombreNuevoUsuario:string="";

  constructor(private http:HttpClient) { }

  getAll():Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.url+"usuarios/allUsers?ordenadosPor=id");
  }

  comprobarSiExisteUsuarioActual(idUsuario:string): Observable<ResponseString> {
    console.log("pasa por aqui "+idUsuario);
    let urlFinal = this.url+"/usuarios/existeUsuarioActual/"+idUsuario;
    console.log("la url final es: "+urlFinal)
    return this.http.get<ResponseString>(urlFinal);
  }

  // CREAR USUARIO NUEVO
  crearUsuario(usuarioDTO:any, idNuevoUsuario:string): Observable<any> {
    return this.http.post<ResponseString>(this.url+"/usuarios/crearusuario/"+idNuevoUsuario, usuarioDTO);
  }

  
}
