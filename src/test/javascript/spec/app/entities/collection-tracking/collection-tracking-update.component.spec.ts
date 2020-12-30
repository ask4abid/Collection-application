import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CcwApplicationTestModule } from '../../../test.module';
import { CollectionTrackingUpdateComponent } from 'app/entities/collection-tracking/collection-tracking-update.component';
import { CollectionTrackingService } from 'app/entities/collection-tracking/collection-tracking.service';
import { CollectionTracking } from 'app/shared/model/collection-tracking.model';

describe('Component Tests', () => {
  describe('CollectionTracking Management Update Component', () => {
    let comp: CollectionTrackingUpdateComponent;
    let fixture: ComponentFixture<CollectionTrackingUpdateComponent>;
    let service: CollectionTrackingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwApplicationTestModule],
        declarations: [CollectionTrackingUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CollectionTrackingUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CollectionTrackingUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CollectionTrackingService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CollectionTracking(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CollectionTracking();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
