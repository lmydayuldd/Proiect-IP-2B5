import {Component, OnInit} from '@angular/core';
import {MdDialog, MdSnackBar} from "@angular/material";
import {PopupService} from "./popup.service";

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css'],
  providers: [PopupService]
})
export class PopupComponent {
  answer: string;

  constructor(private dialog: MdDialog, private _popupService: PopupService, private snackBar: MdSnackBar) {
  }

  onNoSelect() {
    this.dialog.closeAll();
  }

  onYesSelect() {
    this._popupService.temporarySave()
      .subscribe(
        data => this.answer = JSON.stringify(data).replace(/\"/g, ""),
        error => alert(error),
        () => this.snackBar.open(this.answer, "", {
          duration: 2000,
        })
      );
  }
}
