
import { AuthComponent } from "./auth/auth.component";
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from './login/login.component';
import { Route } from '@angular/router';


const routes: Route[] = [
	{ path: 'home', component: HomeComponent, pathMatch: 'full'},
	{ path: 'auth', component: AuthComponent, pathMatch: 'full'},
	{ path: '', redirectTo: 'auth', pathMatch: 'full'},
	{ path: 'authorized', redirectTo: 'auth', pathMatch: 'full'},
	{ path: 'login', component: LoginComponent, pathMatch: 'full'}

];
 
export default routes;