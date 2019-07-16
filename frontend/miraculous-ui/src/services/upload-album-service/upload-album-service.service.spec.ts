import { TestBed } from '@angular/core/testing';

import { UploadAlbumServiceService } from './upload-album-service.service';

describe('UploadAlbumServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UploadAlbumServiceService = TestBed.get(UploadAlbumServiceService);
    expect(service).toBeTruthy();
  });
});
