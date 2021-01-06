import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Realisateur } from '../realisateurs/realisateur.model';

@Component({
  selector: 'app-realisateur-detail',
  templateUrl: './realisateur-detail.component.html',
  styleUrls: ['./realisateur-detail.component.css']
})
export class RealisateurDetailComponent implements OnInit {

  id: number;
  realisateur: Realisateur = new Realisateur();
  updated_realisateur: Realisateur = new Realisateur();
  showForm: Boolean;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(parameter => {
      this.id = +parameter.id;
    })
    this.getReal().subscribe(
      data => { this.realisateur = data }
    )
    this.showForm = false;
  }

  /**
   * Récupère le réalisateur de cette page à partir de l'id
   */
  getReal(): Observable<Realisateur> {
    return this.http.get<Realisateur>('http://localhost:8080/realisateur/{id}?id=' + this.id.toString());
  }

  /**
   * Supprime le réalisateur courant de la base de données
   */
  deleteRealisateur(): void {
    if (confirm("Assurez-vous d'avoir au préalable supprimé les films de ce réalisteur.\n \nÊtes-vous sur de vouloir supprimer ce realisateur : " + this.realisateur.prenom + " " + this.realisateur.nom)) {
      console.log(this.realisateur);
      let params = new HttpParams();
      params = params.append('id', this.id.toString());
      this.http.delete<Realisateur>('http://localhost:8080/realisateur', { params: params }).subscribe()
      this.router.navigateByUrl('/realisateur')
    }
  }

  /**
   * Montre le formulaire de mise à jour du réalisateur
   */
  clickShowForm(): void {
    this.showForm = true;
  }

  /**
   * Cache le formulaire de mise à jour du réalisateur
   */
  clickHideForm(): void {
    this.showForm = false;
  }

  /**
   * Bouton retour à la liste des réalisateurs
   */
  btnClick(): void {
    this.router.navigateByUrl('/realisateur')
  }

  /**
   * Mise à jour du réalisateur avec l'API 
   * @param form le formulaire de mise à jour rempli
   */
  processUpdateForm(form): Observable<Realisateur> {
    if (form.invalid) {
      alert("The form is incorrect")
      return;
    }
    this.updated_realisateur.id = this.realisateur.id;
    this.updated_realisateur.prenom = form.value.prenom;
    this.updated_realisateur.nom = form.value.nom;
    this.updated_realisateur.date = form.value.date;
    this.http.put<Realisateur>('http://localhost:8080/realisateur', this.updated_realisateur).subscribe();
    location.reload();
  }
}
