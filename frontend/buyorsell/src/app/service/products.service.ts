import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../models/product';
import { BehaviorSubject, map, Observable, catchError,throwError, of } from 'rxjs';
import { promises } from 'dns';
// import { environment } from '@environments/environment';

@Injectable()
export class ProductService {
    private productBehavior!: BehaviorSubject<Product>;
    private url: string;

    constructor(private http: HttpClient) {
        this.url = 'http://localhost:8080/product';
    }

    public getProductById(): Observable<number | null> {
        const prod = this.productBehavior.value;
        return of(prod && prod.id ? prod.id : null);
    }

    public addProduct(product: Product) {
        return this.http.post<Product>(`${this.url}` + '/add-product/', product);
    }
}
