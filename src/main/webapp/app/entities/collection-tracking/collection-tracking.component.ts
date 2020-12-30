import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICollectionTracking } from 'app/shared/model/collection-tracking.model';
import { CollectionTrackingService } from './collection-tracking.service';
import { CollectionTrackingDeleteDialogComponent } from './collection-tracking-delete-dialog.component';

@Component({
  selector: 'jhi-collection-tracking',
  templateUrl: './collection-tracking.component.html',
})
export class CollectionTrackingComponent implements OnInit, OnDestroy {
  collectionTrackings?: ICollectionTracking[];
  eventSubscriber?: Subscription;

  constructor(
    protected collectionTrackingService: CollectionTrackingService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.collectionTrackingService
      .query()
      .subscribe((res: HttpResponse<ICollectionTracking[]>) => (this.collectionTrackings = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCollectionTrackings();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICollectionTracking): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCollectionTrackings(): void {
    this.eventSubscriber = this.eventManager.subscribe('collectionTrackingListModification', () => this.loadAll());
  }

  delete(collectionTracking: ICollectionTracking): void {
    const modalRef = this.modalService.open(CollectionTrackingDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.collectionTracking = collectionTracking;
  }
}
