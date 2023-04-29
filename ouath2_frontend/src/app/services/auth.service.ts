import { Injectable } from "@angular/core";

import { HttpHeaders } from '@angular/common/http';
import { HttpService } from './http.service';
import redirectUrl from '../constants/redirect';
import tokenUrl from '../constants/token';
import { Buffer } from 'buffer';
import { of } from "rxjs";

@Injectable({ providedIn: 'root' })
export class AuthService {

	public code: string = '';
	constructor(private httpService: HttpService) {}


	getToken() {
		
	
		const demoClientId = 'client';
        const demoClientSecret = 'secret';

        const basicAuth = `Basic ` + Buffer.from(`${demoClientId}:${demoClientSecret}`).toString('base64');
        const headers = new HttpHeaders({
          'content-type': `application/json`,
          'Authorization': basicAuth
        })
        const options = {
          headers: headers   
        }
	// 	console.log("Basic  = " + basicAuth);
	// 	console.log("headers  = " + headers);
		
	// 	console.log("tokeUrl = " + tokenUrl(this.code));
		
		
		return this.httpService.doPost(tokenUrl(this.code), null, options);
	}
}