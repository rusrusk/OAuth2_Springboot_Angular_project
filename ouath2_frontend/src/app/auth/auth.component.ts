import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { take } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
 
@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
}) 
export class AuthComponent implements OnInit {

    
  constructor(private authService: AuthService, private activateRoute: ActivatedRoute, private router: Router) {
    this.getAuthorizationCode();
  }

  ngOnInit(): void {

    this.authService.getToken().pipe(take(1)).subscribe((tokens) => {
      console.log("tokens ========= " + tokens);
      
      if ((tokens as any)?.id_token) {
          sessionStorage.setItem('id_token', (tokens as any).id_token); 
          this.router.navigate(["/home"]);
        }
    });
  }

  getAuthorizationCode() {
    this.activateRoute.queryParams.subscribe((params) => {
      console.log("here");
      
      if (params?.['code']) {
        this.authService.code = params['code'];
        console.log('code ====== ', this.authService.code);
        
      }
      
    })
  }
  
  // ngOnDestroy() {
  //   this.subscription.unsubscribe();
  // }
}
