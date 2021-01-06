import { Component, OnInit } from '@angular/core';
import { Realisateur } from './realisateur.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-realisateurs',
  templateUrl: './realisateurs.component.html',
  styleUrls: ['./realisateurs.component.css']
})
export class RealisateursComponent implements OnInit {

  realisateurs: Realisateur[];
  added_realisateur: Realisateur;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.getReals().subscribe(
      data => { this.realisateurs = data; }
    )
  }

  /**
   * Récupère la liste des réalisateurs depuis l'API
   */
  getReals(): Observable<Realisateur[]> {
    return this.http.get<Realisateur[]>('http://localhost:8080/realisateur');
  }

  /**
   * Ajoute un réalisateur à la base de données à partir du formulaire d'ajout rempli par l'utilisateur
   * @param form le formulaire rempli
   */
  processAddingForm(form): Observable<Realisateur> {
    if (form.invalid) {
      alert("The form is incorrect")
      return;
    }
    this.added_realisateur = new Realisateur(form.value);
    this.http.post<Realisateur>('http://localhost:8080/realisateur', this.added_realisateur).subscribe();
    location.reload();
  }

  /**
   * Bouton détails d'un réalisateur
   * @param id l'id du réalisateur
   */
  btnClick(id: number): void {
    this.router.navigateByUrl('/realisateur/' + id.toString());
  }
}
