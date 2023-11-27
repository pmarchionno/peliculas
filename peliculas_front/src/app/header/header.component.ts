import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  boton: number = 1;

  constructor(
    private router: Router,
  ) { }

  addPelicula() {
    this.router.navigate(['/addPelicula'])
  }

  // prueba(nn: number) {
  //   console.log(nn)
  // }
}
