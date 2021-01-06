import { Component, OnInit } from '@angular/core';
import { Film } from '../films/film.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Realisateur } from '../realisateurs/realisateur.model';


@Component({
  selector: 'app-film-detail',
  templateUrl: './film-detail.component.html',
  styleUrls: ['./film-detail.component.css']
})
export class FilmDetailComponent implements OnInit {
  id: number;
  film: Film = new Film();
  updated_film: Film = new Film();
  showForm: Boolean;
  realisateurs: Realisateur[];
  selected_real: Realisateur;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(parameter => {
      this.id = +parameter.id;
    })
    this.getFilm().subscribe(
      data => { this.film = data; }
    )
    this.showForm = false;
  }

  /**
   * Récupère le film corrspondant à l'id de cette page
   */
  getFilm(): Observable<Film> {
    return this.http.get<Film>('http://localhost:8080/film/{id}?id=' + this.id.toString());
  }

  /**
   * Supprime le film 
   */
  deleteFilm(): void {
    if (confirm("Êtes-vous sur de vouloir supprimer ce film : " + this.film.titre)) {
      console.log(this.film);
      let params = new HttpParams();
      params = params.append('id', this.id.toString());
      this.http.delete<Film>('http://localhost:8080/film', { params: params }).subscribe()
      this.router.navigateByUrl('/film')
    }
  }

  /**
   * Bouton retour à la liste des films
   */
  btnClick(): void {
    this.router.navigateByUrl('/film');
  }

  /**
   * Récupère la liste des réalisateurs depuis l'API
   */
  getReals(): Observable<Realisateur[]> {
    return this.http.get<Realisateur[]>('http://localhost:8080/realisateur');
  }

  /**
   * Montre le formulaire de mise à jour et récupère la liste des réalisateurs pour pouvoir le remplir (économie de mémoire)
   */
  clickShowForm(): void {
    this.showForm = true;
    this.getReals().subscribe(
      data => { this.realisateurs = data; }
    )
  }

  /**
   * Cache le formulaire de mise à jour 
   */
  clickHideForm(): void {
    this.showForm = false;
  }

  /**
   * Met à jour le film à partir du formulaire de mise à jour rempli par l'utilisateur
   * @param form le formulaire récupéré au submit
   */
  processUpdateForm(form): Observable<Film> {
    if (form.invalid) {
      alert("The form is incorrect")
      return;
    }
    this.updated_film.id = this.film.id;
    this.updated_film.realisateurID = +this.selected_real.id;
    this.updated_film.titre = form.value.titre;
    this.updated_film.realisateurName = this.selected_real.prenom.concat(" ", this.selected_real.nom.toString());
    this.updated_film.duration = +form.value.duration;
    this.http.put<Film>('http://localhost:8080/film', this.updated_film).subscribe();
    location.reload();
  }
}
