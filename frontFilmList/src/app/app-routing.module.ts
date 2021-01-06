import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FilmDetailComponent } from './film-detail/film-detail.component';
import { FilmsComponent } from './films/films.component';
import { RealisateurDetailComponent } from './realisateur-detail/realisateur-detail.component';
import { RealisateursComponent } from './realisateurs/realisateurs.component';


const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'film', component: FilmsComponent },
  { path: 'film/:id', component: FilmDetailComponent },
  { path: 'realisateur', component: RealisateursComponent },
  { path: 'realisateur/:id', component: RealisateurDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
