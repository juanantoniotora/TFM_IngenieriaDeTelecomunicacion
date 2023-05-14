import { Component } from '@angular/core';
import { Contenido } from '../contenido';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ServicioContenidosService } from '../servicio-contenidos.service';

@Component({
  selector: 'app-contenido-seleccionado',
  templateUrl: './contenido-seleccionado.component.html',
  styleUrls: ['./contenido-seleccionado.component.css']
})
export class ContenidoSeleccionadoComponent {
  contenido : Contenido;
  idContenidoSeleccionado: number;
  urlTree:any;

  constructor(
     private afAuth : AngularFireAuth,
     private router : Router,
     private cookie : CookieService,
     private servicioContenidosService : ServicioContenidosService) { 
      this.urlTree = this.router.parseUrl(this.router.url);
      this.idContenidoSeleccionado = this.urlTree.queryParams['idContenidoSeleccionado'];
      this.contenido = new Contenido();
    }

  cookieCorreo = this.cookie.get("cookies_correoRegistrado_PelisMiu");
  

  ngOnInit(): void {
    this.servicioContenidosService.getDetallesContenidoSeleccionado(this.idContenidoSeleccionado).subscribe(
      e=>this.contenido=e
    )
  }

  logout(){
    this.afAuth.signOut().then(()=>{
      this.cookie.set("JWT_PelisMiu","")
      this.cookie.set("cookies_correoRegistrado_PelisMiu", "");
      this.router.navigate(["/login"])
    })
    .catch((error)=>{console.log("Error en LogOut")})
  }
}
