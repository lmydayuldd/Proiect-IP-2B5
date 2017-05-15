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
    this.dialog.open(PopupComponent, {width: '400px', height: '200px'});
  }

  // button element
  onSaveElementClick(element: any, x1: any, y1: any, x2: any, y2: any, floor: any, room: any, isExitWay: any, isExterior: any) {
    if (this.validateElements(element, x1, y1, x2, y2, floor, room, isExitWay, isExterior))
      this.onSaveElementEvent(element, x1, y1, x2, y2, floor, room, isExitWay, isExterior);
  }

  validateElements(element: any, x1: any, y1: any, x2: any, y2: any, floor: any, room: any, isExitWay: any, isExterior: any) {
    if (room == "") {
      this.snackBar.open("A room must be named.", "", {duration: 2000});
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
    return true;
  }

  onSaveElementEvent(element: any, x1: any, y1: any, x2: any, y2: any, floor: any, room: any, isExitWay: any, isExterior: any) {
    this._dashboardService.sendElement(element, x1, y1, x2, y2, floor, room, isExitWay, isExterior)
      .subscribe(
        data => this.answer = JSON.parse(JSON.stringify(data)).message,
        error => alert(error),
        () => this.snackBar.open(this.answer, "", {
          duration: 2000,
        })
      );
  }
}
