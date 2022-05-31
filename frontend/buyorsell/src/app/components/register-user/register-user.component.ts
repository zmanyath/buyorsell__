import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { first } from 'rxjs/operators';

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
      password: ['', [Validators.required, Validators.minLength(8),
      Validators.maxLength(12)]],
      createdAt: [Date.now()]
    });

  }

  onSubmit() {
    console.log(this.registerForm.value)
    this.userService.save(this.registerForm.value)
    .pipe(first())
    .subscribe({
      next: () => {
        this.router.navigate(['../question'], { relativeTo: this.route });
      } 
  });
}


}
