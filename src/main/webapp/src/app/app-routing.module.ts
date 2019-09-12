import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DriversComponent } from './drivers/drivers.component';
import { VehicleComponent } from './vehicle/vehicle.component';


const routes: Routes = [
  {path : 'drivers', component: DriversComponent},
  {path : 'vehicles', component: VehicleComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
