import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'employee',
        loadChildren: () => import('./employee/employee.module').then(m => m.CcwApplicationEmployeeModule),
      },
      {
        path: 'collection-tracking',
        loadChildren: () => import('./collection-tracking/collection-tracking.module').then(m => m.CcwApplicationCollectionTrackingModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class CcwApplicationEntityModule {}
