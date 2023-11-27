import { Component, Input } from '@angular/core';
import { Actor } from 'src/app/model/Actor';

@Component({
  selector: 'app-multiple-dropdown',
  templateUrl: './multiple-dropdown.component.html',
  styleUrls: ['./multiple-dropdown.component.css']
})
export class MultipleDropdownComponent {
  @Input() listDropDown!: Actor[]

  filteredList: Actor[] | undefined

  ngOnInit() {
    this.filteredList = this.listDropDown;
  }

  ngOnChanges() {
    this.filteredList = this.listDropDown;
  }
}
