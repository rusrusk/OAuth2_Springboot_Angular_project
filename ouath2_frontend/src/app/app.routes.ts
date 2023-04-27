import { Route } from "@angular/router";
import { AuthComponent } from "./auth/auth.component";
import { HomeComponent } from "./home/home.component";

const redirectToUrl = '';

const routes: Route[] = [
	{path: "home", component: HomeComponent, pathMatch: 'full'},
	{path: "auth", component: AuthComponent, pathMatch: 'full'},
	{path: "", redirectTo: "auth", pathMatch: 'full'},
	{path: "authorized", redirectTo: "auth", pathMatch: 'full'},
	{path: "login", redirectTo: redirectToUrl, pathMatch: 'full'}

];

export default routes;