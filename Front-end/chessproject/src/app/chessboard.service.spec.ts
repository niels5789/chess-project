import { TestBed } from '@angular/core/testing';

import { ChessboardService } from './chessboard.service';

describe('ChessboardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ChessboardService = TestBed.get(ChessboardService);
    expect(service).toBeTruthy();
  });
});
