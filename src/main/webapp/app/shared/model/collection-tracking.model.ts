import { Moment } from 'moment';
import { IEmployee } from 'app/shared/model/employee.model';

export interface ICollectionTracking {
  id?: number;
  employeeID?: string;
  unitID?: string;
  employeeName?: string;
  businessProposal?: string;
  subProposal?: string;
  mobileNo?: string;
  relationId?: string;
  accountNo?: string;
  accountTitle?: string;
  noOfVisits?: number;
  osAmount?: number;
  osProfit?: number;
  odDays?: string;
  loanOfficer?: string;
  visitedBy?: string;
  pptDate?: Moment;
  remakrs?: string;
  employee?: IEmployee;
}

export class CollectionTracking implements ICollectionTracking {
  constructor(
    public id?: number,
    public employeeID?: string,
    public unitID?: string,
    public employeeName?: string,
    public businessProposal?: string,
    public subProposal?: string,
    public mobileNo?: string,
    public relationId?: string,
    public accountNo?: string,
    public accountTitle?: string,
    public noOfVisits?: number,
    public osAmount?: number,
    public osProfit?: number,
    public odDays?: string,
    public loanOfficer?: string,
    public visitedBy?: string,
    public pptDate?: Moment,
    public remakrs?: string,
    public employee?: IEmployee
  ) {}
}
