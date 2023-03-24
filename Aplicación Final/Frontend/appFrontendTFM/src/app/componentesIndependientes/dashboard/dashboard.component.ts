import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Route, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { empty } from 'rxjs';
import { ServicioUsuariosService } from './servicio-usuarios.service';
import { Usuario } from './usuario';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  usuarios : Usuario[];

  constructor(
     private afAuth : AngularFireAuth,
     private router : Router,
     private cookie : CookieService,
     private servicioUsuariosService : ServicioUsuariosService) { 
      this.usuarios = [new Usuario()];
     }

  cookieCorreo = this.cookie.get("cookies_correoRegistrado_PelisMiu");

  ngOnInit(): void {
    console.log("\nValor rescatado de la cookie en el componente <Dashboard>: "+  this.cookie.get("JWT_PelisMiu"));
    this.servicioUsuariosService.getAll().subscribe(
      e=>this.usuarios=e
    )
  }


  logout(){
    this.afAuth.signOut().then(()=>{
      console.log("LogOut realizado.\n")
      this.cookie.set("JWT_PelisMiu","")
      console.log("\nValor de la cookie después de logout: "+this.cookie.get("JWT_PelisMiu"))
      console.log(this.usuarios)

      //borrado de cookie del correo
      this.cookie.set("cookies_correoRegistrado_PelisMiu", "");
      
      this.router.navigate(["/login"])
    })
    .catch((error)=>{console.log("Error en LogOut")})
  }

}
