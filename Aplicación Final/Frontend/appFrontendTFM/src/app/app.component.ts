import { Component } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ServicioContenidosService } from './componentesIndependientes/dashboard/servicio-contenidos.service';
import { Contenido } from './componentesIndependientes/dashboard/contenido';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'appFrontendTFM';

  constructor(
    private afAuth : AngularFireAuth,
    private router : Router,
    private cookie : CookieService) 
    { }

  cookieCorreo = this.cookie.get("cookies_correoRegistrado_PelisMiu");

  logout(){
    this.afAuth.signOut()
    .then(async ()=>{
      this.cookie.set("cookies_correoRegistrado_PelisMiu", "");
      this.cookie.set("JWT_PelisMiu","")
      window.location.reload(); // va directamente a la pagina Login por hacer perdido la Cookie.
      //his.router.navigate(["/login"])
    }).catch((error)=>{console.log("Error en LogOut")})
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }
}
