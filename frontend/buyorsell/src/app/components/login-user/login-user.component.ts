import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/service/user.service';
import { first } from 'rxjs';

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


  onSubmit() {
    console.log(this.loginForm.value)
    this.userService.login(this.loginForm.value).
    pipe(first()).
    subscribe({
      next: () => {
      // get return url from query parameters or default to home page
      const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
      this.router.navigateByUrl(returnUrl);
  }

    
    });
  }

}