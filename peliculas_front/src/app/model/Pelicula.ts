import { Actor } from "./Actor";

export class Pelicula {
  id!: number;
  titulo!: string;
  anio!: number;
  trama!: string;
  presupuesto!: number;
  actores!: Array<Actor>;

  Pelicula() { }
}
