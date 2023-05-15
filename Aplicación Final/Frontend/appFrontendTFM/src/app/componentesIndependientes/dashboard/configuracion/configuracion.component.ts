import { Component } from '@angular/core';
import { ServicioUsuariosService } from '../servicio-usuarios.service';
import { CookieService } from 'ngx-cookie-service';
import { elementAt } from 'rxjs';

@Component({
  selector: 'app-configuracion',
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})


export class ConfiguracionComponent {
  email = this.cookie.get("cookies_correoRegistrado_PelisMiu");
  deleteConseguido = false;
  
  
  constructor(private cookie : CookieService,
    private servicioUsuariosService : ServicioUsuariosService){

  }



  eliminarUsuario(){
    console.log("Eliminar usuario.")


    this.servicioUsuariosService.eliminarUsuario(
      {
        "nombre": null,
        "apellidos":null,
        "edad":null,
        "activado":null,
        "telefono":null,
        "genero": null,
        "id": this.email
      },
      this.email
    ).subscribe(
      data=>this.deleteConseguido = true)
  }

  actualizarUsuario(){
    console.log("Actualizar usuario.")


    this.servicioUsuariosService.actualizarUsuario(
      {
        "nombre": "juan antonio",
        "apellidos":null,
        "edad":120,
        "activado":null,
        "telefono":null,
        "genero": null,
        "id": this.email
      },
      this.email
    ).subscribe(
      data=>console.log("usuario actualizado") )
  }
}
