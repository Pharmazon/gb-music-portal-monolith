import { Injectable } from '@angular/core';
import {of} from "rxjs";

const TOKEN_KEY = "AuthToken";
const VISITOR_ID_KEY = "AuthVisitorId"
const USERNAME_KEY = "AuthLogin";
const AUTHORITIES_KEY = "AuthAuthorities";


@Injectable({
  providedIn: 'root'
})
export class TokenStorageServiceService {

  private roles: Array<string> = [];


  constructor() {
  }

  signOut(isRememberMeAuthModeOn: boolean){
    if (isRememberMeAuthModeOn == true){
      window.localStorage.clear();
    }else {
      window.sessionStorage.clear();
    }
  }

  saveVisitorId(isRememberMeAuthModeOn: boolean, visitorId: string){
    if (isRememberMeAuthModeOn == true){
      window.localStorage.removeItem(VISITOR_ID_KEY);
      window.localStorage.setItem(VISITOR_ID_KEY, visitorId);
    } else {
      window.sessionStorage.removeItem(VISITOR_ID_KEY);
      window.sessionStorage.setItem(VISITOR_ID_KEY, visitorId);
    }
  }

  getVisitorId(isRememberMeAuthModeOn: boolean){
    if (isRememberMeAuthModeOn == true){
      window.localStorage.getItem(VISITOR_ID_KEY);
    }else {
      window.sessionStorage.getItem(VISITOR_ID_KEY);
    }
  }


  saveToken(isRememberMeAuthModeOn: boolean, token: string){
    if (isRememberMeAuthModeOn == true){
      window.localStorage.removeItem(TOKEN_KEY);
      window.localStorage.setItem(TOKEN_KEY, token);
    } else {
      window.sessionStorage.removeItem(TOKEN_KEY);
      window.sessionStorage.setItem(TOKEN_KEY, token);
    }
  }

  getToken(isRememberMeAuthModeOn: boolean, ): string{
    if (isRememberMeAuthModeOn == true){
        return window.localStorage.getItem(TOKEN_KEY);
    } else {
        return window.sessionStorage.getItem(TOKEN_KEY);
    }
  }

  saveLogin(isRememberMeAuthModeOn: boolean, login: string){
    if (isRememberMeAuthModeOn == true){
      window.localStorage.removeItem(USERNAME_KEY);
      window.localStorage.setItem(USERNAME_KEY, login);
    }else {
      window.sessionStorage.removeItem(USERNAME_KEY);
      window.sessionStorage.setItem(USERNAME_KEY, login);
    }
  }

  getLogin(isRememberMeAuthModeOn: boolean): string{
    if (isRememberMeAuthModeOn == true){
      return window.localStorage.getItem(USERNAME_KEY);
    } else {
      return window.sessionStorage.getItem(USERNAME_KEY);
    }
  }

  saveAuthorities(isRememberMeAuthModeOn: boolean, authorities: string[]){
      if (isRememberMeAuthModeOn == true){
        window.localStorage.removeItem(AUTHORITIES_KEY);
        window.localStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
      }else {
        window.sessionStorage.removeItem(AUTHORITIES_KEY);
        window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
      }
  }

  getAuthorities(isRememberMeAuthModeOn: boolean): string[]{
    this.roles = [];
    if (isRememberMeAuthModeOn == true){
      if (window.sessionStorage.getItem(TOKEN_KEY)){
        JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)).forEach(authority => {
          this.roles.push(authority.authority);
        });
      }
    } else {
      if (window.localStorage.getItem(TOKEN_KEY)){
        JSON.parse(localStorage.getItem(AUTHORITIES_KEY)).forEach(authority => {
          this.roles.push(authority.authority);
        });
      }
    }

    return this.roles;
  }

}



