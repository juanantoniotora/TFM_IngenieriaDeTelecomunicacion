import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './componentesIndependientes/dashboard/dashboard.component';
import { ErrorComponent } from './componentesIndependientes/error/error.component'
import { LoginComponent } from './componentesIndependientes/login/login.component';
import { RecuperarPasswordComponent } from './componentesIndependientes/recuperar-password/recuperar-password.component';
import { RegistrarUsuarioComponent } from './componentesIndependientes/registrar-usuario/registrar-usuario.component';
import { VerificarCorreoComponent } from './componentesIndependientes/verificar-correo/verificar-correo.component';

import { GuardianSiNoLogeado } from './componentesIndependientes/login/guardian-si-no-logeado';
import { AccionComponent } from './componentesIndependientes/dashboard/accion/accion.component';
import { CatalogoComponent } from './componentesIndependientes/dashboard/catalogo/catalogo.component';
import { ComediaComponent } from './componentesIndependientes/dashboard/comedia/comedia.component';
import { DramaComponent } from './componentesIndependientes/dashboard/drama/drama.component';
import { SeriesComponent } from './componentesIndependientes/dashboard/series/series.component';
import { TendenciasComponent } from './componentesIndependientes/dashboard/tendencias/tendencias.component';
import { ConfiguracionComponent } from './componentesIndependientes/dashboard/configuracion/configuracion.component';
import { ContenidoSeleccionadoComponent } from './componentesIndependientes/dashboard/contenido-seleccionado/contenido-seleccionado.component';

const routes: Routes = [
  {path:'login',              component : LoginComponent},
  {path:'signup',             component : RegistrarUsuarioComponent},
  {path:'recuperar-password', component: RecuperarPasswordComponent},
  {path:'verificar-correo',   component : VerificarCorreoComponent},
  {path:'accion',             component : AccionComponent, canActivate:[GuardianSiNoLogeado]},
  {path:'catalogo',           component : CatalogoComponent, canActivate:[GuardianSiNoLogeado]},
  {path:'comedia',            component : ComediaComponent, canActivate:[GuardianSiNoLogeado]},
  {path:'configuracion',      component : ConfiguracionComponent, canActivate:[GuardianSiNoLogeado]},
  {path:'drama',              component : DramaComponent, canActivate:[GuardianSiNoLogeado]},
  {path:'series',             component : SeriesComponent, canActivate:[GuardianSiNoLogeado]},
  {path:'tendencias',         component : TendenciasComponent, canActivate:[GuardianSiNoLogeado]},
  {path:'contenido-seleccionado', component : ContenidoSeleccionadoComponent, canActivate:[GuardianSiNoLogeado]},
  {path:'',                   redirectTo: '/catalogo', pathMatch: 'full'},
  {path :'**',                component : ErrorComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
