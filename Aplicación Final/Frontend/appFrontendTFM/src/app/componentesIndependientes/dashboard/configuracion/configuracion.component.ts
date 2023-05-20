import { Component } from '@angular/core';
import { ServicioUsuariosService } from '../servicio-usuarios.service';
import { CookieService } from 'ngx-cookie-service';
import { elementAt } from 'rxjs';
import { Usuario } from 'src/modelos/usuario';
import { compileNgModule } from '@angular/compiler';

@Component({
  selector: 'app-configuracion',
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})


export class ConfiguracionComponent {
  email = this.cookie.get("cookies_correoRegistrado_PelisMiu");
  deleteConseguido = false;
  usuario : Usuario;
  
  form_activo = false;
  form_apellidos="";
  form_edad=0;
  form_genero=0;
  form_id="";
  form_nombre="";
  form_telefono=0;


  constructor(private cookie : CookieService,
    private servicioUsuariosService : ServicioUsuariosService){
      this.usuario = new Usuario;
  }

  ngOnInit(): void {
    // recupero los datos del usuario almacenado en la cookie
    this.servicioUsuariosService.recuperarUsuarioActual( this.email).subscribe( data => {
      this.usuario=data;
      console.log(this.usuario);
      
      this.form_activo    = this.usuario.activado;
      this.form_apellidos  = this.usuario.apellidos;
      this.form_edad      = this.usuario.edad;
      this.form_genero    = this.usuario.genero;
      this.form_id        = this.usuario.id;
      this.form_nombre    = this.usuario.nombre;
      this.form_telefono  = this.usuario.telefono;
    })
    
  }


  // ACCIONES POR BOTÓN
  // eliminar el usuario y la cuenta existente
  async eliminarUsuario(){
    this.servicioUsuariosService
      .eliminarUsuario(this.email)
        .subscribe(data=>
          {
            this.deleteConseguido = true
            this.cookie.set("cookies_correoRegistrado_PelisMiu", "");
            this.cookie.set("JWT_PelisMiu","");
            this.email="";
          }
        )
        await this.delay(500);
    // si ha eliminado usuario sale al LogIn, sino recarga Configuracion
    window.location.reload();
  }

  actualizarUsuario(){
    this.servicioUsuariosService.actualizarUsuario(
      {
        "nombre": this.form_nombre,
        "apellidos":this.form_apellidos,
        "edad":this.form_edad,
        "activado":this.form_activo,
        "telefono":this.form_telefono,
        "genero": this.form_genero,
        "id": this.form_id,
      }, this.email).subscribe()
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }
}
