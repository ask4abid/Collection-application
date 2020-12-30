import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICollectionTracking, CollectionTracking } from 'app/shared/model/collection-tracking.model';
import { CollectionTrackingService } from './collection-tracking.service';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee/employee.service';

@Component({
  selector: 'jhi-collection-tracking-update',
  templateUrl: './collection-tracking-update.component.html',
})
export class CollectionTrackingUpdateComponent implements OnInit {
  isSaving = false;
  employees: IEmployee[] = [];
  pptDateDp: any;

  editForm = this.fb.group({
    id: [],
    employeeID: [null, [Validators.required]],
    unitID: [null, [Validators.required]],
    employeeName: [null, [Validators.required]],
    businessProposal: [null, [Validators.required]],
    subProposal: [null, [Validators.required]],
    mobileNo: [null, [Validators.required]],
    relationId: [null, [Validators.required]],
    accountNo: [null, [Validators.required]],
    accountTitle: [null, [Validators.required]],
    noOfVisits: [null, [Validators.required]],
    osAmount: [null, [Validators.required]],
    osProfit: [null, [Validators.required]],
    odDays: [null, [Validators.required]],
    loanOfficer: [null, [Validators.required]],
    visitedBy: [null, [Validators.required]],
    pptDate: [null, [Validators.required]],
    remakrs: [null, [Validators.required]],
    employee: [],
  });

  constructor(
    protected collectionTrackingService: CollectionTrackingService,
    protected employeeService: EmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ collectionTracking }) => {
      this.updateForm(collectionTracking);

      this.employeeService.query().subscribe((res: HttpResponse<IEmployee[]>) => (this.employees = res.body || []));
    });
  }

  updateForm(collectionTracking: ICollectionTracking): void {
    this.editForm.patchValue({
      id: collectionTracking.id,
      employeeID: collectionTracking.employeeID,
      unitID: collectionTracking.unitID,
      employeeName: collectionTracking.employeeName,
      businessProposal: collectionTracking.businessProposal,
      subProposal: collectionTracking.subProposal,
      mobileNo: collectionTracking.mobileNo,
      relationId: collectionTracking.relationId,
      accountNo: collectionTracking.accountNo,
      accountTitle: collectionTracking.accountTitle,
      noOfVisits: collectionTracking.noOfVisits,
      osAmount: collectionTracking.osAmount,
      osProfit: collectionTracking.osProfit,
      odDays: collectionTracking.odDays,
      loanOfficer: collectionTracking.loanOfficer,
      visitedBy: collectionTracking.visitedBy,
      pptDate: collectionTracking.pptDate,
      remakrs: collectionTracking.remakrs,
      employee: collectionTracking.employee,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const collectionTracking = this.createFromForm();
    if (collectionTracking.id !== undefined) {
      this.subscribeToSaveResponse(this.collectionTrackingService.update(collectionTracking));
    } else {
      this.subscribeToSaveResponse(this.collectionTrackingService.create(collectionTracking));
    }
  }

  private createFromForm(): ICollectionTracking {
    return {
      ...new CollectionTracking(),
      id: this.editForm.get(['id'])!.value,
      employeeID: this.editForm.get(['employeeID'])!.value,
      unitID: this.editForm.get(['unitID'])!.value,
      employeeName: this.editForm.get(['employeeName'])!.value,
      businessProposal: this.editForm.get(['businessProposal'])!.value,
      subProposal: this.editForm.get(['subProposal'])!.value,
      mobileNo: this.editForm.get(['mobileNo'])!.value,
      relationId: this.editForm.get(['relationId'])!.value,
      accountNo: this.editForm.get(['accountNo'])!.value,
      accountTitle: this.editForm.get(['accountTitle'])!.value,
      noOfVisits: this.editForm.get(['noOfVisits'])!.value,
      osAmount: this.editForm.get(['osAmount'])!.value,
      osProfit: this.editForm.get(['osProfit'])!.value,
      odDays: this.editForm.get(['odDays'])!.value,
      loanOfficer: this.editForm.get(['loanOfficer'])!.value,
      visitedBy: this.editForm.get(['visitedBy'])!.value,
      pptDate: this.editForm.get(['pptDate'])!.value,
      remakrs: this.editForm.get(['remakrs'])!.value,
      employee: this.editForm.get(['employee'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICollectionTracking>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IEmployee): any {
    return item.id;
  }
}
