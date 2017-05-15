/**
 * Created by BlackDeathM8 on 14-May-17.
 */

import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
@Injectable()
export class DashboardService {
  constructor(private _http: Http) {
  }

  sendElement(element: String, x1: number, y1: number, x2: number, y2: number, floor: String, room: String, isExitWay: number, isExterior: number) {
    var body = {
      elementType: element,
      room: room,
      x1: x1,
      y1: y1,
      x2: x2,
      y2: y2,
      floor: floor,
      isExitWay: isExitWay,
      isExterior: isExterior
    };
    console.log(JSON.stringify(body));
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this._http.post('http://localhost:4500/add/', JSON.stringify(body), {headers: headers})
      .map(res => res.json());
  }
}
