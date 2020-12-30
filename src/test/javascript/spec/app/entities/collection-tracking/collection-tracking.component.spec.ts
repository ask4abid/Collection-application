import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CcwApplicationTestModule } from '../../../test.module';
import { CollectionTrackingComponent } from 'app/entities/collection-tracking/collection-tracking.component';
import { CollectionTrackingService } from 'app/entities/collection-tracking/collection-tracking.service';
import { CollectionTracking } from 'app/shared/model/collection-tracking.model';

describe('Component Tests', () => {
  describe('CollectionTracking Management Component', () => {
    let comp: CollectionTrackingComponent;
    let fixture: ComponentFixture<CollectionTrackingComponent>;
    let service: CollectionTrackingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwApplicationTestModule],
        declarations: [CollectionTrackingComponent],
      })
        .overrideTemplate(CollectionTrackingComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CollectionTrackingComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CollectionTrackingService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CollectionTracking(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.collectionTrackings && comp.collectionTrackings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
