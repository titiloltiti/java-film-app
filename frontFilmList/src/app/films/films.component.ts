import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Film } from './film.model';
import { Realisateur } from '../realisateurs/realisateur.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { FilmPage } from './filmpage.modele';

@Component({
  selector: 'app-films',
  templateUrl: './films.component.html',
  styleUrls: ['./films.component.css']
})
export class FilmsComponent implements OnInit {

  films: Film[];
  realisateurs: Realisateur[];
  added_film: Film = new Film();
  selected_real: Realisateur;
  order: string = 'id';
  reverse: boolean = false;
  number: number = 1;
  size: number = 10;
  total: number = 1;
  realFilter: string = '';
  titreFilter: string = '';

  constructor(private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    this.getFilms().subscribe(
      data => { this.films = data.data; this.number = data.number; this.size = data.size; this.total = data.total }
    )
    this.getReals().subscribe(
      data => { this.realisateurs = data; }
    )
  }

  /**
   * Récupère la liste des films depuis l'API avec les paramètres de page
   */
  getFilms(): Observable<FilmPage> {
    return this.http.get<FilmPage>('http://localhost:8080/film?number=' + this.number + '&size=' + this.size);
  }

  /**
   * Récupère la liste des films depuis l'API avec les paramètres de page et les filtres
   */
  getFilmsFiltered(): Observable<FilmPage> {
    return this.http.get<FilmPage>('http://localhost:8080/film/{titre}/{real}?titre=' + this.titreFilter + '&real=' + this.realFilter + '&number=' + this.number + '&size=' + this.size);
  }
  /**
   * Récupère la liste des realisateurs depuis l'API
   */
  getReals(): Observable<Realisateur[]> {
    return this.http.get<Realisateur[]>('http://localhost:8080/realisateur');
  }

  /**
   * Bouton permettant de voir les détails d'un film
   * @param id l'id d'un des films de la liste de films
   */
  btnClick(id: number): void {
    this.router.navigateByUrl('/film/' + id.toString());
  }

  /**
   * Ajoute un film à la base de données à partir du formulaire rempli par l'utilisateur
   * @param form le formulaire rempli
   */
  processAddingForm(form): Observable<Film> {
    if (form.invalid) {
      alert("The form is incorrect")
      return;
    }
    this.added_film.realisateurID = +this.selected_real.id;
    this.added_film.titre = form.value.titre;
    this.added_film.realisateurName = this.selected_real.prenom.concat(" ", this.selected_real.nom.toString());
    this.added_film.duration = +form.value.duration;
    this.http.post<Film>('http://localhost:8080/film', this.added_film).subscribe(data => (this.films.push(data)));
    this.showAllFilms();
  }

  /**
   * Filtre les films en fonction du titre et/ou du réalisateur renseigné, en utilisant l'API
   * @param form le formulaire de filtrage rempli
   */
  processFilterForm(form): Observable<Film[]> {
    if (form.invalid) {
      alert("The form is incorrect")
      return;
    }
    this.number = 1;
    this.realFilter = form.value.realFilter;
    this.titreFilter = form.value.titreFilter;
    this.showFilms();

  }


  showFilms(): void {
    if (this.realFilter == '' && this.titreFilter == '') {
      this.getFilms().subscribe(
        data => { this.films = data.data; this.number = data.number; this.size = data.size; this.total = data.total }
      )
    }
    else {

      this.getFilmsFiltered().subscribe(
        data => { this.films = data.data; this.number = data.number; this.size = data.size; this.total = data.total }
      )
    }
  }


  setNumber(number: number) {
    this.number = number;
    this.showFilms();
  }

  nextPage() {
    if (this.number < this.total) {
      this.number += 1;
      this.showFilms();

    }
  }
  prevPage() {
    if (this.number > 1) {
      this.number -= 1;
      this.showFilms();
    }
  }
  /**
   * Bouton de retour à la liste complète de tous films
   */
  showAllFilms(): void {
    this.realFilter = '';
    this.titreFilter = '';
    this.setNumber(1);
  }

  /**
   * Setter pour la valeur de tri (id, durée ou titre), la valeur reverse correspond au sens de tri
   * @param value ('id','duree','titre'), la valeur du tri
   */
  setOrder(value: string) {
    if (this.order === value) {
      this.reverse = !this.reverse;
    }
    this.order = value;
  }

  setSize(size: number) {
    this.size = size
  }

}
