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

  // GET
  getAll():Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.url+"usuarios/allUsers?ordenadosPor=id");
  }

  // GET booleano (retornado con string)
  comprobarSiExisteUsuarioActual(idUsuario:string): Observable<ResponseString> {
    let urlFinal = this.url+"/usuarios/existeUsuarioActual/"+idUsuario;
    return this.http.get<ResponseString>(urlFinal);
  }

  // GET usuarioActual (retornado el model Usuario)
  recuperarUsuarioActual(idUsuario:string): Observable<Usuario> {
    return this.http.get<Usuario>(this.url+"/usuarios/usuarioActual/"+idUsuario);
  }

  // CREAR USUARIO NUEVO (retorna el id como string)
  crearUsuario(usuarioDTO:any, idNuevoUsuario:string): Observable<any> {
    return this.http.post<ResponseString>(this.url+"/usuarios/crearusuario/"+idNuevoUsuario, usuarioDTO);
  }

  // ACTUALIZAR USUARIO (no retorna nada OK 204)
  actualizarUsuario(usuarioDTO:any, idUsuario:string):Observable<any> {
    return this.http.put(this.url+"/usuarios/actualizarUsuario/"+idUsuario, usuarioDTO);
  }
  
  // ELIMINAR USUARIO (no retorna nada OK 204)
  eliminarUsuario(id:string): Observable<any> {
    return this.http.post(this.url+"/usuarios/eliminarUsuario/"+id, "");
  }
}
