import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Product {
  id: string;
  name: string;
  price: number;
}

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private _http: HttpClient) { }

  findAll(): Observable<Product[]> {
    return this._http.get<Product[]>('/java-web-angular/api/products');
  }

  findById(id: string): Observable<Product> {
    return this._http.get<Product>(`/java-web-angular/api/products/${id}`);
  }

}
