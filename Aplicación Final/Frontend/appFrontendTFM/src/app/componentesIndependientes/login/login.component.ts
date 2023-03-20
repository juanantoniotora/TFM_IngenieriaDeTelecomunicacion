import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Usuario } from '../dashboard/usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loading : boolean = false;
  mostrarMensajeErrorRegistro : boolean = false;
  mostrarMensajeExitoRegistro : boolean = false;
  mensajeInfo : String = "";
  loginUsuario : FormGroup;

  JWTUsuario : string = "";
  correoUsuarioPelismiu : string = "";

  constructor(
    private fb: FormBuilder,
    private afAuth : AngularFireAuth,
    private router : Router,
    private cookie : CookieService
  ) {
    this.loginUsuario = this.fb.group({
      email : ['', [Validators.required, Validators.email]],
      password : ['', Validators.required],
    })
    this.loading = false;
    this.mostrarMensajeErrorRegistro = false;
    this.mensajeInfo = "";
   }

  ngOnInit(): void {
    // Si estoy logeado no dejo ver este componente y me vuelvo al Dashboard
    if(this.cookie.get("JWT_PelisMiu")!=""){
      this.router.navigate(["dashboard"])
    }
  }

  login(){
    const email = this.loginUsuario.value.email;
    const password = this.loginUsuario.value.password;
    this.loading = true;
    this.afAuth.signInWithEmailAndPassword(email, password).then((user)=>{
      console.log(user)
      if(user.user?.emailVerified){
        /**
         * Recuperamos el JWT del usuario, recien conseguido al iniciar sesión
         */
        user.user.getIdToken().then(token=>{
          this.JWTUsuario = token;
          console.log("Propiedad <JWTUsuario> antes de salir de componente <login>: \n" + this.JWTUsuario);
          
          this.cookie.set("JWT_PelisMiu", this.JWTUsuario);
          const jwtCookie = this.cookie.get("JWT_PelisMiu");
          console.log("\n\nValor de la cookie antes de salir del componente <login>: " + jwtCookie)
          
          this.router.navigate(['/dashboard']);
        });
      }
      else{
        this.router.navigate(['/verificar-correo'])
      }
    }).catch((error)=>{
      this.loading = false;
      console.log('error');
      this.loading=false;
      this.mostrarMensajeErrorRegistro=true;
      this.mostrarMensajeExitoRegistro=false;
      this.mensajeInfo=this.firebaseError(error.code)
    })
  }

  firebaseError(code:String){
    switch(code){
      // password invalida
      case 'auth/invalid-password':
        return 'Contraseña inválida'
      // password incorrecta
      case 'auth/wrong-password':
        return 'Contraseña incorrecta'
      // correo invalido
      case 'auth/invalid-email':
        return 'Correo inválido'
      case 'auth/user-not-found':
        return 'El usuario no existe'
      // cualquier otro error no contemplado
      default: 
        return 'Email/Password incorrecto'
    }
  }

}
