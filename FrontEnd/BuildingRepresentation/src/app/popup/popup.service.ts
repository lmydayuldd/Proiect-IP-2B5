/**
 * Created by BlackDeathM8 on 14-May-17.
 */
import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
@Injectable()
export class PopupService {
  constructor(private _http: Http) {
  }

  temporarySave() {
    var body = {};
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return this._http.post('http://localhost:4500/finalSave', JSON.stringify(body), {headers: headers})
      .map(res => res.json());
  }
}
