import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'account-deletion-confirmation',
  templateUrl: './account-deletion-confirmation.component.html',
  styleUrls: ['./account-deletion-confirmation.component.css']
})
export class AccountDeletionConfirmationComponent implements OnInit {


  @Output() onClosedAccountDeletion = new EventEmitter<any>();
  constructor() { }

  ngOnInit() {
  }

  closeAccountDeletionConfirmation() {
    this.onClosedAccountDeletion.emit();
  }
}
