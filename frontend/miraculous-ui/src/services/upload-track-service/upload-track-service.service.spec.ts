import { TestBed } from '@angular/core/testing';

import { UploadTrackServiceService } from './upload-track-service.service';

describe('UploadTrackServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UploadTrackServiceService = TestBed.get(UploadTrackServiceService);
    expect(service).toBeTruthy();
  });
});
