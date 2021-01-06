import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  filmsCount: number = 3;
  realsCount: number = 3;
  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.getFilmsCount().subscribe(
      data => { this.filmsCount = data }
    )
    this.getRealsCount().subscribe(
      data => { this.realsCount = data }
    )
  }

  /**
   * Récupère le compte des films
   */
  getFilmsCount(): Observable<number> {
    return this.http.get<number>('http://localhost:8080/film/count')
  }

  /**
   * Récupère le compte des réalisateurs
   */
  getRealsCount(): Observable<number> {
    return this.http.get<number>('http://localhost:8080/realisateur/count')
  }

  clickFilms(): void {
    this.router.navigateByUrl('/film');
  }
  clickReals(): void {
    this.router.navigateByUrl('/realisateur');
  }
}
