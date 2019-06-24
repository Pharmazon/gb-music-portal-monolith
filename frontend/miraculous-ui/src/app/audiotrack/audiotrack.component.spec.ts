import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AudiotrackComponent } from './audiotrack.component';

describe('AudiotrackComponent', () => {
  let component: AudiotrackComponent;
  let fixture: ComponentFixture<AudiotrackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AudiotrackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AudiotrackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
