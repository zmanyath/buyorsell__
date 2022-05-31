import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { first } from 'rxjs';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})

export class QuestionComponent implements OnInit {
  user!: null;
  userData: Object | undefined;
  currentUser: Object | undefined;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    // private router: Route
    ) {
    // this.user = this.userService.userValue;
    
  }

  ngOnInit(){
    // this.getUser(this.route.snapshot.paramMap.get('id'));
    // this.userService.getUser(1)
    //         .pipe(first())
    //         .subscribe(user => this.user = user);
  }

  getUser(id: any) {
    
    this.userService.getUser(1)
      .subscribe(
        data => {
          this.currentUser = data;
          console.log(data);
          console.log(id);
        },
        error => {
          console.log(error);
        });
  }

}

