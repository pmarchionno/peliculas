import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Actor } from 'src/app/model/Actor';
import { Pelicula } from 'src/app/model/Pelicula';
import { PeliculaService } from 'src/app/pelicula.service';

@Component({
  selector: 'app-add-pelicula',
  templateUrl: './add-pelicula.component.html',
  styleUrls: ['./add-pelicula.component.css']
})
export class AddPeliculaComponent implements OnInit {
  pelicula = new Pelicula();
  actoresList!: Actor[];
  isSubmitted = false;


  actorSelected!: Number | null;
  titulo: any;

  constructor(private peliculaService: PeliculaService, public fb: FormBuilder, private router: Router = new Router) { }

  ngOnInit(): void {
    this.getActores();
  }

  onSubmit() {
    console.log(this.myForm);
    this.isSubmitted = true;
    if (!this.myForm.valid) {
      false;
    } else {
      console.log(JSON.stringify(this.myForm.value));
      
      this.peliculaService.addPelicula(this.pelicula).subscribe(
        (response: Pelicula) => {
          console.log(response);
          this.myForm.reset();
          this.pelicula = this.peliculaService.resetDataFormPelicula();
          this.goBackToHome();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
          this.myForm.reset();
        }
      );
    }
  }

  goBackToHome() {
    this.router.navigate(['']);
  }

  public getActores(): void {
    this.peliculaService.getActores().subscribe((dato) => {
      this.actoresList = dato;
    })
  }

  changeActor(e: any) {
    if (e.target.value) {
      this.actorSelected = e.target.value;
      const mySet = new Array<Actor>();
      let myActor: Actor = new Actor;
      myActor.setId(e.target.value);
      mySet.push(myActor);
      this.pelicula.actores = mySet;
    }
    else {
      this.actorSelected = null;
      this.pelicula.actores = new Array<any>();
    }
  }

  myForm = this.fb.group({
    titulo: ['', [Validators.required]],
    anio: ['', [Validators.required, Validators.minLength(1), Validators.min(1)]],
    trama: ['', [Validators.required]],
    presupuesto: ['', [Validators.required]],
    actores: ['', [Validators.required]]
  });
}
