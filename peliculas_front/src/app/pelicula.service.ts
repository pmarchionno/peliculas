import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Actor } from './model/Actor';
import { Pelicula } from './model/Pelicula';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PeliculaService {
  private apiServerUrl = environment.apiUrl + "/peliculas";
  private apiServerUrlActores = environment.apiUrl + "/actores";

  constructor(private http: HttpClient) { }

  public getPeliculas(): Observable<Pelicula[]> {
    return this.http.get<Pelicula[]>(`${this.apiServerUrl}/all`);
  }

  public getPeliculaById(peliculaId: number): Observable<Pelicula> {
    return this.http.get<Pelicula>(`${this.apiServerUrl}/find/${peliculaId}`);
  }

  public addPelicula(pelicula: Pelicula): Observable<Pelicula> {
    return this.http.post<Pelicula>(`${this.apiServerUrl}/add`, pelicula);
  }

  public updatePelicula(pelicula: Pelicula): Observable<Pelicula> {
    return this.http.put<Pelicula>(`${this.apiServerUrl}/update`, pelicula);
  }

  public deletePelicula(peliculaId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/delete/${peliculaId}`);
  }

  resetDataFormPelicula(): Pelicula {
    return new Pelicula;
  }

  //------------ Actor ----------------------------------
  public getActores(): Observable<Actor[]> {
    return this.http.get<Actor[]>(`${this.apiServerUrlActores}/all`)
  }
}
