import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackPopupComponent } from './track-popup.component';

describe('TrackPopupComponent', () => {
  let component: TrackPopupComponent;
  let fixture: ComponentFixture<TrackPopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrackPopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrackPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
