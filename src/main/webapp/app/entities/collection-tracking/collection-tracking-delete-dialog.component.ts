import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICollectionTracking } from 'app/shared/model/collection-tracking.model';
import { CollectionTrackingService } from './collection-tracking.service';

@Component({
  templateUrl: './collection-tracking-delete-dialog.component.html',
})
export class CollectionTrackingDeleteDialogComponent {
  collectionTracking?: ICollectionTracking;

  constructor(
    protected collectionTrackingService: CollectionTrackingService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.collectionTrackingService.delete(id).subscribe(() => {
      this.eventManager.broadcast('collectionTrackingListModification');
      this.activeModal.close();
    });
  }
}
