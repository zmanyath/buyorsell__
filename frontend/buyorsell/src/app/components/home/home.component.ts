import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/service/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  users: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loadUsers();
  }
  

  loadUsers(): void {
    this.userService.findAll().subscribe({
      next: (users: User[]) => {
        this.users = users;
        // Optional: show success alert
       
      },
      error: (error) => {
        // Handle error
        Swal.fire({
          title: 'Error!',
          text: 'Failed to load users.',
          icon: 'error',
          confirmButtonText: 'Try Again'
        });
      }
    });
  }
}


