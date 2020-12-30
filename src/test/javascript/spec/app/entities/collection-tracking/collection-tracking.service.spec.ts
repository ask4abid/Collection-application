import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CollectionTrackingService } from 'app/entities/collection-tracking/collection-tracking.service';
import { ICollectionTracking, CollectionTracking } from 'app/shared/model/collection-tracking.model';

describe('Service Tests', () => {
  describe('CollectionTracking Service', () => {
    let injector: TestBed;
    let service: CollectionTrackingService;
    let httpMock: HttpTestingController;
    let elemDefault: ICollectionTracking;
    let expectedResult: ICollectionTracking | ICollectionTracking[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CollectionTrackingService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CollectionTracking(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            pptDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CollectionTracking', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            pptDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            pptDate: currentDate,
          },
          returnedFromService
        );

        service.create(new CollectionTracking()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CollectionTracking', () => {
        const returnedFromService = Object.assign(
          {
            employeeID: 'BBBBBB',
            unitID: 'BBBBBB',
            employeeName: 'BBBBBB',
            businessProposal: 'BBBBBB',
            subProposal: 'BBBBBB',
            mobileNo: 'BBBBBB',
            relationId: 'BBBBBB',
            accountNo: 'BBBBBB',
            accountTitle: 'BBBBBB',
            noOfVisits: 1,
            osAmount: 1,
            osProfit: 1,
            odDays: 'BBBBBB',
            loanOfficer: 'BBBBBB',
            visitedBy: 'BBBBBB',
            pptDate: currentDate.format(DATE_FORMAT),
            remakrs: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            pptDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CollectionTracking', () => {
        const returnedFromService = Object.assign(
          {
            employeeID: 'BBBBBB',
            unitID: 'BBBBBB',
            employeeName: 'BBBBBB',
            businessProposal: 'BBBBBB',
            subProposal: 'BBBBBB',
            mobileNo: 'BBBBBB',
            relationId: 'BBBBBB',
            accountNo: 'BBBBBB',
            accountTitle: 'BBBBBB',
            noOfVisits: 1,
            osAmount: 1,
            osProfit: 1,
            odDays: 'BBBBBB',
            loanOfficer: 'BBBBBB',
            visitedBy: 'BBBBBB',
            pptDate: currentDate.format(DATE_FORMAT),
            remakrs: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            pptDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CollectionTracking', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
