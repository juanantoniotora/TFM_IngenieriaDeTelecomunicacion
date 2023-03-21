import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PrimerComponenteFormularioLoginComponent } from './primer-componente-formulario-login/primer-componente-formulario-login.component';

import { FormsModule } from "@angular/forms"

import { SimulacionbbddService } from './simulacionbbdd.service';

import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module'; 

import { ReactiveFormsModule } from '@angular/forms';

import { AngularFireModule } from '@angular/fire/compat';
import { environment } from 'src/environments/environment';



@NgModule({
  declarations: [
    AppComponent,
    PrimerComponenteFormularioLoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    AngularFireModule.initializeApp(environment.firebaseConfig)
  ],
  providers: [SimulacionbbddService],
  bootstrap: [AppComponent]
})
export class AppModule { }



