import {Directive, ElementRef, HostListener} from '@angular/core';
import {FileChangeEvent} from "@angular/compiler-cli/src/perform_watch";

@Directive({
  selector: '[fileInput]'
})
export class FileInputDirective {

  constructor(private elementRef:ElementRef) { }


}
