import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DomainServiceService {

  static DOMAIN = new URL(window.location.href).origin;

  constructor() { }
}
