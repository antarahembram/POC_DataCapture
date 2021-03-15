import { TestBed } from '@angular/core/testing';

import { DatacaptureService } from './datacapture.service';

describe('DatacaptureService', () => {
  let service: DatacaptureService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DatacaptureService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
