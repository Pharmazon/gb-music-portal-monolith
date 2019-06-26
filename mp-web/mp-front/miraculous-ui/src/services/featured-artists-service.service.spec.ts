import { TestBed } from '@angular/core/testing';

import { FeaturedArtistsServiceService } from './featured-artists-service.service';

describe('FeaturedArtistsServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FeaturedArtistsServiceService = TestBed.get(FeaturedArtistsServiceService);
    expect(service).toBeTruthy();
  });
});
