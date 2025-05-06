import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { ProductService } from 'src/app/service/products.service';
import { first } from 'rxjs/operators';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-products',
  templateUrl: './new-products.component.html',
  styleUrls: ['./new-products.component.css']
})
export class NewProductsComponent implements OnInit {

  constructor(
    private route: ActivatedRoute, 
    private router: Router, 
    private formBuilder: FormBuilder,
    private productService: ProductService
  ) { }

  productForm!: FormGroup;

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      id: [],
      name: ['',],
      price: [''],
      description: [''],
      product_image: [''],
      sku: [''],
      createdAt: [Date.now()]
    })
  }

  onSubmit() {
    this.productService.addProduct(this.productForm.value)
      .pipe(first())
      .subscribe({
        next: (product) => {
          Swal.fire({
            title: 'Success!',
            text: 'Your registration was successful.',
            icon: 'success',
            confirmButtonText: 'OK'
          }).then(() => {
            this.router.navigate(['../profile', product.id], { relativeTo: this.route });
          });
        },
        error: (error) => {
          Swal.fire({
            title: 'Error!',
            text: 'There was a problem with your registration.',
            icon: 'error',
            confirmButtonText: 'Try Again'
          });
        }
    });
  }

}
