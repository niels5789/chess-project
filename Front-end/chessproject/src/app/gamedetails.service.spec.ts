import { TestBed } from '@angular/core/testing';

import { GamedetailsService } from './gamedetails.service';

describe('GamedetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GamedetailsService = TestBed.get(GamedetailsService);
    expect(service).toBeTruthy();
  });
});
