import { TestBed } from '@angular/core/testing';
import {ChessBoardService} from './chessboard.service';


describe('ChessboardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ChessBoardService = TestBed.get(ChessBoardService);
    expect(service).toBeTruthy();
  });
});
