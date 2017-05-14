import {Routes, RouterModule} from "@angular/router";
import {ModuleWithProviders} from "@angular/core";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {ErrorComponent} from "./error/error.component";
/**
 * Created by BlackDeathM8 on 05-May-17.
 */

export const router: Routes = [
  {path: '', component: DashboardComponent},
  {path: '404NotFound', component: ErrorComponent},
  {path: '**', redirectTo: '404NotFound'}
];

export const mainRouter: ModuleWithProviders = RouterModule.forRoot(router);
