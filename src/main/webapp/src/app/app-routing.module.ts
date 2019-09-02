import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DriversComponent } from './drivers/drivers.component';
import { TaxisComponent } from './taxis/taxis.component';


const routes: Routes = [
  {path : 'drivers', component: DriversComponent},
  {path : 'taxis', component: TaxisComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
