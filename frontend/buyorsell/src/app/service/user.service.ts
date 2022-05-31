import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { UserLogin } from '../models/userLogin';
import { BehaviorSubject, map, Observable } from 'rxjs';
// import { environment } from '@environments/environment';


@Injectable()
export class UserService {

  private userBehavior!: BehaviorSubject<User>;
  private user: Observable<User>;

  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/user';
    this.userBehavior = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')!));
    this.user = this.userBehavior.asObservable();
  }

  public get userValue(): User {
    return this.userBehavior.value;
}

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(`${this.url}`+  '/all-users');
  }

  public getUser(id: number) {
    return this.http.get(`${this.url}/${id}`);
  }

  public login(userLogin: UserLogin) {
    return this.http.post<User>(`${this.url}` + '/login', userLogin)
    .pipe(map(user => {
      localStorage.setItem('user', JSON.stringify(user));
      this.userBehavior.next(user);
      return user;
  }));
  }

  public save(user: User) {
    return this.http.post<User>(`${this.url}` + '/register', user);
  }
}


