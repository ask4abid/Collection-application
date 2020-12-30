import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CcwApplicationTestModule } from '../../../test.module';
import { CollectionTrackingDetailComponent } from 'app/entities/collection-tracking/collection-tracking-detail.component';
import { CollectionTracking } from 'app/shared/model/collection-tracking.model';

describe('Component Tests', () => {
  describe('CollectionTracking Management Detail Component', () => {
    let comp: CollectionTrackingDetailComponent;
    let fixture: ComponentFixture<CollectionTrackingDetailComponent>;
    const route = ({ data: of({ collectionTracking: new CollectionTracking(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwApplicationTestModule],
        declarations: [CollectionTrackingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CollectionTrackingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CollectionTrackingDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load collectionTracking on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.collectionTracking).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
