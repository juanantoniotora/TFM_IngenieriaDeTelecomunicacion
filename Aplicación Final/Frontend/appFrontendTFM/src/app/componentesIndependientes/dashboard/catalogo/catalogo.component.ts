import { Component } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ServicioUsuariosService } from '../servicio-usuarios.service';
import { Contenido } from '../contenido';
import { ServicioContenidosService } from '../servicio-contenidos.service';
import { MatToolbarModule } from '@angular/material/toolbar';
//import { ContenidoDTO } from 'src/DTO/ContenidoDTO';
import { ResponseString } from 'src/modelos/ResponseString';


@Component({
  selector: 'app-catalogo',
  templateUrl: './catalogo.component.html',
  styleUrls: ['./catalogo.component.css']
})
export class CatalogoComponent {
  contenidos : Contenido[];
  responseString: ResponseString;

  constructor(
     private afAuth : AngularFireAuth,
     private router : Router,
     private cookie : CookieService,
     private servicioContenidosService : ServicioContenidosService) { 
      this.contenidos = [new Contenido()];
      this.responseString = new ResponseString;
     }

  cookieCorreo = this.cookie.get("cookies_correoRegistrado_PelisMiu");

  ngOnInit(): void {
    this.servicioContenidosService.getCatalogoContenidos().subscribe(
      data=>this.contenidos=data
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
