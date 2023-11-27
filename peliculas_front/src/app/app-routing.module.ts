import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AddPeliculaComponent } from './forms/add-pelicula/add-pelicula.component';

const routes: Routes = [
  {path: '', component:HomeComponent},
  {path: 'addPelicula', component:AddPeliculaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
