import { TestBed } from '@angular/core/testing';

import { Top15TracksServiceService } from './top-15-tracks-service.service';

describe('Top15TracksServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: Top15TracksServiceService = TestBed.get(Top15TracksServiceService);
    expect(service).toBeTruthy();
  });
});
