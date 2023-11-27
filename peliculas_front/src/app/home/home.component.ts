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
  actoresList!: Actor[];

  constructor(private peliculaService: PeliculaService) { }

  ngOnInit() {
    this.getPeliculas();
    this.getActores();
  }

  public getPeliculas(): void {
    this.peliculaService.getPeliculas().subscribe((dato) => {
      this.peliculas = dato;
      this.peliculasList = [];
      // this.peliculasList = dato;
      dato.forEach(el => {
        this.getPeliculaById(el.id)
        // this.peliculasList.push(this.getPeliculaById(el.id))
        console.log(this.peliculasList.length)
      })
      // this.peliculasList.forEach(el => el.actoresDetailList = this.getActoresPelicula(el.id))
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

  getActoresPelicula(id: number): Actor[] {
    let arr: Actor[];
    this.peliculaService.getActoresPelicula(id).subscribe((dato) => {
      return dato;
    })
    return [];
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

  public getActores(): void {
    this.peliculaService.getActores().subscribe((dato) => {
      this.actoresList = dato;
    })
  }

  public getActorName(actor: Actor): String {
    let name: String;
    if (actor.name) {
      return actor.name;
    } else {
      let actorId = this.actoresList.find(el => el == actor)?.name;
      name = "actorId";
      // this.actoresList = this.actoresList.find(el => el == actor);
    }
    if (name)
      return name;
    else
      return ""
  }
}
