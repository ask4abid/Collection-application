import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CcwApplicationSharedModule } from 'app/shared/shared.module';
import { CollectionTrackingComponent } from './collection-tracking.component';
import { CollectionTrackingDetailComponent } from './collection-tracking-detail.component';
import { CollectionTrackingUpdateComponent } from './collection-tracking-update.component';
import { CollectionTrackingDeleteDialogComponent } from './collection-tracking-delete-dialog.component';
import { collectionTrackingRoute } from './collection-tracking.route';

@NgModule({
  imports: [CcwApplicationSharedModule, RouterModule.forChild(collectionTrackingRoute)],
  declarations: [
    CollectionTrackingComponent,
    CollectionTrackingDetailComponent,
    CollectionTrackingUpdateComponent,
    CollectionTrackingDeleteDialogComponent,
  ],
  entryComponents: [CollectionTrackingDeleteDialogComponent],
})
export class CcwApplicationCollectionTrackingModule {}
