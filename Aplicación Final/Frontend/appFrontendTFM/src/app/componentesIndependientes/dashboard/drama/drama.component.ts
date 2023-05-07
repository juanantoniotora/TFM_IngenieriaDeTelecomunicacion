import { Component } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ServicioUsuariosService } from '../servicio-usuarios.service';
import { Contenido } from '../contenido';
import { ServicioContenidosService } from '../servicio-contenidos.service';

@Component({
  selector: 'app-drama',
  templateUrl: './drama.component.html',
  styleUrls: ['./drama.component.css']
})
export class DramaComponent {
  contenidos : Contenido[];

  constructor(
     private afAuth : AngularFireAuth,
     private router : Router,
     private cookie : CookieService,
     private servicioContenidosService : ServicioContenidosService) { 
      this.contenidos = [new Contenido()];
     }

  cookieCorreo = this.cookie.get("cookies_correoRegistrado_PelisMiu");

  ngOnInit(): void {
    console.log("\nValor rescatado de la cookie en el componente <Dashboard>: "+  this.cookie.get("JWT_PelisMiu"));
    this.servicioContenidosService.getDramaContenidos().subscribe(
      e=>this.contenidos=e
    )
  }


  logout(){
    this.afAuth.signOut().then(()=>{
      console.log("LogOut realizado.\n")
      this.cookie.set("JWT_PelisMiu","")
      console.log("\nValor de la cookie después de logout: "+this.cookie.get("JWT_PelisMiu"))
      console.log(this.contenidos)

      //borrado de cookie del correo
      this.cookie.set("cookies_correoRegistrado_PelisMiu", "");
      
      this.router.navigate(["/login"])
    })
    .catch((error)=>{console.log("Error en LogOut")})
  }

}
