import { TestBed } from '@angular/core/testing';

import { SimilarTracksServiceService } from './similar-tracks-service.service';

describe('SimilarTracksServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SimilarTracksServiceService = TestBed.get(SimilarTracksServiceService);
    expect(service).toBeTruthy();
  });
});
