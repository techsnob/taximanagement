import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DriversComponent } from './drivers/drivers.component';
import { VehicleComponent } from './vehicle/vehicle.component';

@NgModule({
  declarations: [
    AppComponent,
    DriversComponent,
    VehicleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
