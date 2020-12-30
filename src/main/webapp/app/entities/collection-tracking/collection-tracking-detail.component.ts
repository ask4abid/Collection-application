import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICollectionTracking } from 'app/shared/model/collection-tracking.model';

@Component({
  selector: 'jhi-collection-tracking-detail',
  templateUrl: './collection-tracking-detail.component.html',
})
export class CollectionTrackingDetailComponent implements OnInit {
  collectionTracking: ICollectionTracking | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ collectionTracking }) => (this.collectionTracking = collectionTracking));
  }

  previousState(): void {
    window.history.back();
  }
}
