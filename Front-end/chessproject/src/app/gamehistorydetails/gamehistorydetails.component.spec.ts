import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GamehistorydetailsComponent } from './gamehistorydetails.component';

describe('GamehistorydetailsComponent', () => {
  let component: GamehistorydetailsComponent;
  let fixture: ComponentFixture<GamehistorydetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GamehistorydetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GamehistorydetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
