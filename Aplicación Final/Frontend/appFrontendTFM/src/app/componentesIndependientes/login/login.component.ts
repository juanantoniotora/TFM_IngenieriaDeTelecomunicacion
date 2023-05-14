import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Usuario } from '../../../modelos/usuario';
import { ServicioUsuariosService } from '../dashboard/servicio-usuarios.service';
import { ResponseString } from 'src/modelos/ResponseString';

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
  jwtCookie:string="";

  responseString: ResponseString;

  constructor(
    private fb: FormBuilder,
    private afAuth : AngularFireAuth,
    private router : Router,
    private cookie : CookieService,
    private servicioUsuariosService : ServicioUsuariosService
  ) {
    this.loginUsuario = this.fb.group({
      email : ['', [Validators.required, Validators.email]],
      password : ['', Validators.required],
    })
    this.loading = false;
    this.mostrarMensajeErrorRegistro = false;
    this.mensajeInfo = "";
    this.responseString = new ResponseString;
   }

  ngOnInit(): void {
    // Si estoy logeado no dejo ver este componente y me vuelvo al Dashboard
    if(this.cookie.get("JWT_PelisMiu")!=""){
      this.router.navigate(["catalogo"])
    }
  }

  login(){
    // cargo variable del metodo
    const email = this.loginUsuario.value.email;
    const password = this.loginUsuario.value.password;
    let existeUsuarioLocal = false;
    this.loading = true;

    // Compruebo si el usuario utilizado existe en BBDD "Usuarios"
    this.servicioUsuariosService.comprobarSiExisteUsuarioActual(email).subscribe(
      data=>{
        this.responseString=data
        if(this.responseString.result==="true"){
          existeUsuarioLocal=true;
        }
      }
    )
    
    // intento realizar el login contra Firebase
    this.afAuth.signInWithEmailAndPassword(email, password).then((user)=>{
      console.log(user)
      if(user.user?.emailVerified){
        /**
         * Recuperamos el JWT del usuario, recien conseguido al iniciar sesión
         */
        user.user.getIdToken().then(token=>{
          
          if(existeUsuarioLocal==true){
            this.JWTUsuario = token;          
            this.cookie.set("JWT_PelisMiu", this.JWTUsuario);
            this.jwtCookie = this.cookie.get("JWT_PelisMiu");
            this.cookie.set("cookies_correoRegistrado_PelisMiu", email);
            window.location.reload(); // va al Catalogo directamente por tener la cookie rellena
            //this.router.navigate(['/catalogo']);
          }
          else{
            window.location.reload(); // va solo a recargar el login porque la cookie esta vacia
          }
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
