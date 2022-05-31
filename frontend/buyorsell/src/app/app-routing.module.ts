import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { LoginUserComponent } from './components/login-user/login-user.component';
import { HomeComponent } from './components/home/home.component';
import { QuestionComponent } from './components/question/question.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  {path: 'home', component: HomeComponent},
  {path: 'register', component: RegisterUserComponent},
  {path: 'profile', component: UserProfileComponent},
  {path: 'login', component: LoginUserComponent},
  {path: 'question', component: QuestionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
