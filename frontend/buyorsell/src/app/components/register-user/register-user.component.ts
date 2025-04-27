import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { first } from 'rxjs/operators';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  
  constructor(

    private route: ActivatedRoute, 
      private router: Router, 
      private formBuilder: FormBuilder,
      private userService: UserService) {

  }

  registerForm!: FormGroup;

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      id: [],
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required]],
      phoneNumber: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8),
      Validators.maxLength(8)]],
      createdAt: [Date.now()]
    });

  }

  onSubmit() {
    this.userService.save(this.registerForm.value)
      .pipe(first())
      .subscribe({
        next: (user) => {
          Swal.fire({
            title: 'Success!',
            text: 'Your registration was successful.',
            icon: 'success',
            confirmButtonText: 'OK'
          }).then(() => {
            this.router.navigate(['../profile', user.id], { relativeTo: this.route });
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