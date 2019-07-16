import { TestBed } from '@angular/core/testing';

import { SimilarAlbumsServiceService } from './similar-albums-service.service';

describe('SimilarAlbumsServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SimilarAlbumsServiceService = TestBed.get(SimilarAlbumsServiceService);
    expect(service).toBeTruthy();
  });
});
