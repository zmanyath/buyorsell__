import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  currentUser: any;
  constructor(
    private userService: UserService
    ) { }
  ngOnInit(): void {
    this.currentUser = this.userService.getUser(1);
  }
}
