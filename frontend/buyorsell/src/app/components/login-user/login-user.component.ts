import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/service/user.service';
import { first } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-root',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css'],
})
export class LoginUserComponent implements OnInit {
  isLoggedin?: boolean;
  returnUrl!: string;
  constructor(

    private route: ActivatedRoute, 
    private router: Router, 
    private formBuilder: FormBuilder,
    private userService: UserService,

    ) {}

  loginForm!: FormGroup;

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
    };


    onSubmit(): void {
      this.userService.login(this.loginForm.value)
        .pipe(first())
        .subscribe({
          next: (user: User) => { // Ensure user is defined

            console.log('Logged in user:', user);
            if (user && user.id) { // Check if user and user.id are not null
              Swal.fire({
                title: 'Login Successful',
                text: 'You have been logged in successfully.',
                icon: 'success',
                confirmButtonText: 'OK'
              }).then(() => {
                // Navigate to the profile page with the user ID
                this.router.navigate(['/profile', user.id]); // Pass the user ID as a route parameter
              });
            } else {
              console.error('User object is null or does not contain an ID');
            }
          },
          error: (error) => {
            Swal.fire({
              title: 'Login Failed',
              text: 'Invalid email or password.',
              icon: 'error',
              confirmButtonText: 'Try Again'
            });
          }
        });
    }
    

}