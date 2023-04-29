import { Component, OnInit } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpService } from '../services/http.service';
import testUrl from '../constants/demo';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public testContent: string = '';

  constructor(private httpService: HttpService) {};

  ngOnInit(): void {
    this.getDemoPrivateInfo();
  }

  private getDemoPrivateInfo() {
    const token = sessionStorage.getItem('id_token');
    const bearerToken = `Bearer ${token}`;
    const options = { 
      headers: new HttpHeaders({
        'Authorization': bearerToken 
      }),
      responseType: 'text/plain'
    };
    this.httpService.doGet(testUrl(), options).pipe(take(1)).subscribe((content) => {
      (this.testContent as any) = content;
    }) 
  }
}
