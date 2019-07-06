import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LastUserLikedTracksComponent } from './last-user-liked-tracks.component';

describe('LastUserLikedTracksComponent', () => {
  let component: LastUserLikedTracksComponent;
  let fixture: ComponentFixture<LastUserLikedTracksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LastUserLikedTracksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LastUserLikedTracksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
