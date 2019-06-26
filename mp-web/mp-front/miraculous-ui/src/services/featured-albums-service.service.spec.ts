import { TestBed } from '@angular/core/testing';

import { FeaturedAlbumsServiceService } from './featured-albums-service.service';

describe('FeaturedAlbumsServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FeaturedAlbumsServiceService = TestBed.get(FeaturedAlbumsServiceService);
    expect(service).toBeTruthy();
  });
});
