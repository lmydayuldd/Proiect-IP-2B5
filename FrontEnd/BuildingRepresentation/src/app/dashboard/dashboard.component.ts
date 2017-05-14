import {Component, OnInit} from '@angular/core';
import {MdSnackBar} from "@angular/material";
import {DashboardService} from "./dashboard.service";
import {MdDialog} from "@angular/material"
import {PopupComponent} from "../popup/popup.component";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css'],
  providers: [DashboardService]
})
export class DashboardComponent {
  answer: string;

  constructor(private _dashboardService: DashboardService, public snackBar: MdSnackBar, public dialog: MdDialog) {
  }

  onTemporarySaveDataClick() {
    this.dialog.open(PopupComponent, {width: '500px', height: '500px'});
  }

  // button element
  onSaveElementClick(element: any, x1: any, y1: any, x2: any, y2: any, floor: any, room: any, isExitWay: any, isExterior: any) {
    if (this.validateElements(element, x1, y1, x2, y2, floor, room, isExitWay, isExterior))
      this.onSaveElementEvent(element, x1, y1, x2, y2, floor, room, isExitWay, isExterior);
  }

  validateElements(element: any, x1: any, y1: any, x2: any, y2: any, floor: any, room: any, isExitWay: any, isExterior: any) {
    if (element == "0") {
      this.snackBar.open("You must choose an element to add.", "", {duration: 2000});
      return false;
    }
    if (floor == "-1") {
      this.snackBar.open("You must choose a floor.", "", {duration: 2000});
      return false;
    }
    if (room == "") {
      this.snackBar.open("You must give a name to a room.", "", {duration: 2000});
      return false;
    }
    if (x1 == "") {
      this.snackBar.open("X1 value must be filled out.", "", {duration: 2000});
      return false;
    }
    if (y1 == "") {
      this.snackBar.open("Y1 value must be filled out.", "", {duration: 2000});
      return false;
    }
    if (x2 == "") {
      this.snackBar.open("X2 value must be filled out.", "", {duration: 2000});
      return false;
    }
    if (y2 == "") {
      this.snackBar.open("Y2 value must be filled out.", "", {duration: 2000});
      return false;
    }
    if (isExitWay == "") {
      this.snackBar.open("IsExitWay must be filled out.", "", {duration: 2000});
      return false;
    }
    if (isExterior == "") {
      this.snackBar.open("IsExterior must be filled out.", "", {duration: 2000});
      return false;
    }
    return true;
  }

  onSaveElementEvent(element: any, x1: any, y1: any, x2: any, y2: any, floor: any, room: any, isExitWay: any, isExterior: any) {
    this._dashboardService.sendElement(element, x1, y1, x2, y2, floor, room, isExitWay, isExterior)
      .subscribe(
        data => this.answer = JSON.stringify(data).replace(/\"/g, ""),
        error => alert(error),
        () => this.snackBar.open(this.answer, "", {
          duration: 2000,
        })
      );
  }
}
