import { HttpErrorResponse } from '@angular/common/http';
import { Component, Optional } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Pelicula } from '../model/Pelicula';
import { PeliculaService } from '../pelicula.service';
import { Actor } from '../model/Actor';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  public peliculas!: Pelicula[];
  public peliculasList!: Pelicula[];
  public editPelicula!: Pelicula;
  public deletePelicula?: Pelicula | undefined;
  public nullPelicula!: Pelicula;

  constructor(private peliculaService: PeliculaService) { }

  ngOnInit() {
    this.getPeliculas();
  }

  public getPeliculas(): void {
    this.peliculaService.getPeliculas().subscribe((dato) => {
      this.peliculas = dato;
      this.peliculasList = [];
      // this.peliculasList = dato;
      dato.forEach(el => {
        this.getPeliculaById(el.id)
        console.log(this.peliculasList.length)
      })
    })
  }

  getPeliculaById(id: number): Pelicula {
    let myPelicula: Pelicula = new Pelicula;
    this.peliculaService.getPeliculaById(id).subscribe((dato) => {
      this.peliculasList.push(dato)
      myPelicula = dato;
    });
    return myPelicula;
  }

  public onAddEmloyee(addForm: NgForm): void {
    const container = document.getElementById('add-pelicula-form')?.click();
    this.peliculaService.addPelicula(addForm.value).subscribe(
      (response: Pelicula) => {
        console.log(response);
        this.getPeliculas();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateEmloyee(pelicula: Pelicula): void {
    this.peliculaService.updatePelicula(pelicula).subscribe(
      (response: Pelicula) => {
        console.log(response);
        this.getPeliculas();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteEmloyee(peliculaId: number): void {
    this.peliculaService.deletePelicula(peliculaId).subscribe(
      (response: void) => {
        console.log(response);
        this.getPeliculas();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchPeliculas(key: string): void {
    const results: Pelicula[] = [];
    for (const pelicula of this.peliculas) {
      if (pelicula.titulo.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || pelicula.anio.toString().indexOf(key.toLowerCase()) !== -1
        || pelicula.trama.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(pelicula);
      }
    }
    this.peliculasList = results;
    if (!key) {
      this.getPeliculas();
    }
  }

  public onOpenModal(pelicula: Pelicula, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPeliculaModal');
    }
    if (mode === 'edit') {
      this.editPelicula = pelicula;
      button.setAttribute('data-target', '#updatePeliculaModal');
    }
    if (mode === 'delete') {
      this.deletePelicula = pelicula;
      button.setAttribute('data-target', '#deletePeliculaModal');
    }
    container?.appendChild(button);
    button.click();
  }
}
