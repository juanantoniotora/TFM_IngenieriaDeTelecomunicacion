import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularFireModule } from '@angular/fire/compat'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { DashboardComponent } from './componentesIndependientes/dashboard/dashboard.component';
import { ErrorComponent } from './componentesIndependientes/error/error.component';
import { LoginComponent } from './componentesIndependientes/login/login.component';
import { RecuperarPasswordComponent } from './componentesIndependientes/recuperar-password/recuperar-password.component';
import { RegistrarUsuarioComponent } from './componentesIndependientes/registrar-usuario/registrar-usuario.component';
import { VerificarCorreoComponent } from './componentesIndependientes/verificar-correo/verificar-correo.component';
import { SpinnerComponent } from './shared/spinner/spinner.component';

import { environment } from 'src/environments/environment';

import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button'; 

import { CookieService } from 'ngx-cookie-service';
import { GuardianSiNoLogeado } from './componentesIndependientes/login/guardian-si-no-logeado';

import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ErrorComponent,
    LoginComponent,
    RecuperarPasswordComponent,
    RegistrarUsuarioComponent,
    VerificarCorreoComponent,
    SpinnerComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    BrowserAnimationsModule,
    MatMenuModule,
    MatButtonModule,
    HttpClientModule
  ],
  providers: [CookieService, GuardianSiNoLogeado],
  bootstrap: [AppComponent]
})
export class AppModule { }
