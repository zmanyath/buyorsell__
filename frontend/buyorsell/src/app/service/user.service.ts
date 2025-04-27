import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { UserLogin } from '../models/userLogin';
import { Location } from '../models/location';
import { BehaviorSubject, map, Observable, catchError,throwError, of } from 'rxjs';
// import { environment } from '@environments/environment';


@Injectable()
export class UserService {
  private userBehavior: BehaviorSubject<User>;
  private url: string;

  constructor(private http: HttpClient) {
    const storedUser = localStorage.getItem('user');
    const initialUser = storedUser ? JSON.parse(storedUser) : {} as User;
    console.log('Stored user from local storage:', initialUser); // Debugging line
    this.userBehavior = new BehaviorSubject<User>(initialUser);
    this.url = 'http://localhost:8080/user';
  }

  public get userValue(): User {
    return this.userBehavior.value;
  }

  public getCurrentUserId(): Observable<number | null> {
    const user = this.userBehavior.value;
    console.log('Current user:', user); // Debugging line
    return of(user && user.id ? user.id : null);
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(`${this.url}`+  '/all-users');
  }

  public getUser(id: number): Observable<User> {
    return this.http.get<User>(`${this.url}/${id}`);
  }
  
  public deleteUser(id: number): Observable<User> {
    return this.http.delete<User>(`${this.url}/delete-user/${id}`);
  }

  public login(userLogin: UserLogin): Observable<User> {
    return this.http.post<User>(`${this.url}/login`, userLogin).pipe(
      map(user => {
        // Store user in local storage and update BehaviorSubject on successful login
        localStorage.setItem('user', JSON.stringify(user));
        this.userBehavior.next(user); // Update the BehaviorSubject
        return user;
      }),
      catchError(error => {
        console.error('Login error:', error);
        return throwError(() => new Error('Login failed'));
      })
    );
  }

  public save(user: User) {
    return this.http.post<User>(`${this.url}` + '/register', user);
  }

  public saveAddress(id: number, location: Location): Observable<Location> {
    return this.http.post<Location>(`${this.url}/${id}/save-address`, location).pipe(
      map(address => {
        console.log('Address saved successfully:', address);
        return address;
      }),
      catchError(error => {
        console.error('Address saving error:', error);
        return throwError(() => new Error('Failed to save address'));
      })
    );
  }
}