import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { take } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';
 
@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  private code: string = '' ;

  constructor(private authService: AuthService, private activateRoute: ActivatedRoute) {
    this.getAuthorizationCode();
  }

  ngOnInit(): void {
    console.log("get a token");
    
      this.authService.getToken().pipe(take(1)).subscribe(() => {

      })
  }

  getAuthorizationCode() {
    this.activateRoute.queryParams.subscribe((params) => {
      console.log("here");
      
      if (params?.['code']) {
        this.code = params['code'];
        console.log('code ====== ', this.code);
        
      }
      
    })
  }

  // ngOnDestroy() {
  //   this.subscription.unsubscribe();
  // }
}
