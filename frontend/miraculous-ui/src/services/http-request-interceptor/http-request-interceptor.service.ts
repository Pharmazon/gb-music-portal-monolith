import { Injectable } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageServiceService} from "../token-storage-service/token-storage-service.service";

const TOKEN_HEADER_KEY = "Authorization";

@Injectable({
  providedIn: 'root'
})
export class HttpRequestInterceptorService implements HttpInterceptor{

  isRememberMeAuthModeOn: boolean;

  constructor(private tokenStorageService: TokenStorageServiceService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authorizationRequest = req;
    const token = this.tokenStorageService.getToken(this.isRememberMeAuthModeOn);
    if (token != null){
      authorizationRequest = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, "Bearer" + token)});
    }
    return next.handle(authorizationRequest);
  }
}

export const httpInterceptorProviders = [{ provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptorService, multi: true
}];
