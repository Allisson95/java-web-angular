import { Component } from '@angular/core';
import { first } from 'rxjs';
import { ProductsService } from './services/products.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'java-web-angular';

  constructor(private _productService: ProductsService) { }

  findAll() {
    this._productService.findAll().pipe(first()).subscribe({ next: console.log, error: console.error });
  }

}
