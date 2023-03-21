import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AngularFireAuth } from '@angular/fire/compat/auth';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'appFrontend';


  loading : boolean = false;
  mostrarMensajeErrorRegistro : boolean = false;
  mostrarMensajeExitoRegistro : boolean = false;
  mensajeInfo : String = "";
  loginUsuario : FormGroup;
  JWTUsuario : string = "";
  correoUsuarioPelismiu : string = "";
  datosEnviadosAlHijoCompApp:String     = "Datos del Comp.Padre enviados y escritos en el Comp.Hijo.";
  datosRecibidosDelHijoCompApp:String   = "";

  constructor(
    private afAuth : AngularFireAuth,
    private fb: FormBuilder,
    private router : Router  ) {
    this.loginUsuario = this.fb.group({
      email : ['', [Validators.required, Validators.email]],
      password : ['', Validators.required],
    });
  }

/**********************************************************************************
    *** Funciones mientras se crea el componente ***
**********************************************************************************/

  /** El "ngOnChanges" es la primera funcion que se ejecuta de nuestro componente.
   *  Se ejecuta cada vez que un valor de entrada se mofica o aparezca.
   *  Esta funcion se dispara cada vez que le llegue un valor al componente desde el exterior del componente.
   * */
  ngOnChanges(): void {
    console.log("2)ngOnChanges");
  }

  /** Se ejecuta después del "ngOnChanges".
   *  Es el constructor interno del componente.
   * Se ejecuta una sola vez y nunca mas. 
   * */
  ngOnInit(): void {
    console.log("3) ngOnInit - Constructor del componente");
  }

  /** Se ejecuta despues del ngOnInit, y vuelve a ejecutarse cada vez que se realicen cambios en el componente.
   *  Esta funcion sirve para comprobar constantemente si algo de nuestro componente ha cambiado.
   * */ 
  ngDoCheck(): void {
    console.log("4) ngDoCheck");
  }



/**********************************************************************************
    *** Funciones después de que se cree el componente ***
    -> Estas funciones forman parte el componente, porque afecta al template del componente.
    -> No sirven en un servicio ni con directivas.
**********************************************************************************/

  /** Se carga cuando todo el contenido del HTML este cargado. */
  ngAfterContentInit(): void {
    console.log("5) ngAfterContentInit");
  }

  /** se ejecuta cuando haya algo modificado despues de terminar de cargar el HTML  */
  ngAfterContentChecked(): void {
    console.log("6) ngAfterContentChecked");
  }

  /** Para comprobar que los Views y ViewChild estan cargados. 
   *  La funcion que mas se utiliza del grupo de funciones "ngAfter...".
  */
  ngAfterViewInit(): void {
    console.log("7) ngAfterViewInit");
  }

  /** Se ejecuta cada vez que un View o ViewChild se modifica.
   * 
   */
  ngAfterViewChecked(): void {
    console.log("8) ngAfterViewChecked");
  }


/**********************************************************************************
    *** Funcion para destruir el componente ***
**********************************************************************************/

  /** ngOnDestroy: si tenemos muchos componentes activos y dejamos de usar alguno, es aconsejable destruirlo.
   * En los componentes se pueden hacer muchas operaciones: listener, observables, llamadas, etc.
   * Con ngOnDestroy, se cierran los listener, observables, llamadas, etc. 
   * Luego se destruye el componente.
   * */ 
  ngOnDestroy(): void {
    console.log("9) ngOnDestroy");
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

