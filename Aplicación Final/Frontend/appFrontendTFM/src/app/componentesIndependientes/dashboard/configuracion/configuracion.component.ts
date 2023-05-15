import { Component } from '@angular/core';
import { ServicioUsuariosService } from '../servicio-usuarios.service';
import { CookieService } from 'ngx-cookie-service';
import { elementAt } from 'rxjs';
import { Usuario } from 'src/modelos/usuario';

@Component({
  selector: 'app-configuracion',
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})


export class ConfiguracionComponent {
  email = this.cookie.get("cookies_correoRegistrado_PelisMiu");
  deleteConseguido = false;
  usuario : Usuario;
  
  constructor(private cookie : CookieService,
    private servicioUsuariosService : ServicioUsuariosService){
      this.usuario = new Usuario;
  }

  eliminarUsuario(){
    this.servicioUsuariosService.eliminarUsuario(this.email).subscribe(data=>this.deleteConseguido = true)
  }

  actualizarUsuario(){
    this.servicioUsuariosService.actualizarUsuario(
      {
        "nombre": "juan antonio",
        "apellidos":null,
        "edad":120,
        "activado":null,
        "telefono":null,
        "genero": null,
        "id": this.email
      }, this.email).subscribe()
  }

  recuperarDatosDeUsuario(){
    this.servicioUsuariosService.recuperarUsuarioActual(this.email).subscribe(
      data=> 
        {  
          this.usuario=data
          console.log(this.usuario)
        }
    )
    
  }

}
