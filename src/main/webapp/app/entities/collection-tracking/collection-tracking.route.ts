import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICollectionTracking, CollectionTracking } from 'app/shared/model/collection-tracking.model';
import { CollectionTrackingService } from './collection-tracking.service';
import { CollectionTrackingComponent } from './collection-tracking.component';
import { CollectionTrackingDetailComponent } from './collection-tracking-detail.component';
import { CollectionTrackingUpdateComponent } from './collection-tracking-update.component';

@Injectable({ providedIn: 'root' })
export class CollectionTrackingResolve implements Resolve<ICollectionTracking> {
  constructor(private service: CollectionTrackingService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICollectionTracking> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((collectionTracking: HttpResponse<CollectionTracking>) => {
          if (collectionTracking.body) {
            return of(collectionTracking.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CollectionTracking());
  }
}

export const collectionTrackingRoute: Routes = [
  {
    path: '',
    component: CollectionTrackingComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApplicationApp.collectionTracking.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CollectionTrackingDetailComponent,
    resolve: {
      collectionTracking: CollectionTrackingResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApplicationApp.collectionTracking.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CollectionTrackingUpdateComponent,
    resolve: {
      collectionTracking: CollectionTrackingResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApplicationApp.collectionTracking.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CollectionTrackingUpdateComponent,
    resolve: {
      collectionTracking: CollectionTrackingResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApplicationApp.collectionTracking.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
