import { ICollectionTracking } from 'app/shared/model/collection-tracking.model';

export interface IEmployee {
  id?: number;
  employeeID?: string;
  unitID?: string;
  firstName?: string;
  lastName?: string;
  emailAddress?: string;
  login?: string;
  collectionTrackings?: ICollectionTracking[];
}

export class Employee implements IEmployee {
  constructor(
    public id?: number,
    public employeeID?: string,
    public unitID?: string,
    public firstName?: string,
    public lastName?: string,
    public emailAddress?: string,
    public login?: string,
    public collectionTrackings?: ICollectionTracking[]
  ) {}
}
