import { Component } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Contenido } from '../contenido';
import { ServicioContenidosService } from '../servicio-contenidos.service';

@Component({
  selector: 'app-accion',
  templateUrl: './accion.component.html',
  styleUrls: ['./accion.component.css']
})
export class AccionComponent {
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
    this.servicioContenidosService.getAccionContenidos().subscribe(
      e=>this.contenidos=e
    )
  }


  logout(){
    this.afAuth.signOut().then(()=>{
      this.cookie.set("JWT_PelisMiu","")
      
      //borrado de cookie del correo
      this.cookie.set("cookies_correoRegistrado_PelisMiu", "");
      
      this.router.navigate(["/login"])
    })
    .catch((error)=>{console.log("Error en LogOut")})
  }

}
