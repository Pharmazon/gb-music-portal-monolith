import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LastUserLikedAlbumsComponent } from './last-user-liked-albums.component';

describe('LastUserLikedAlbumsComponent', () => {
  let component: LastUserLikedAlbumsComponent;
  let fixture: ComponentFixture<LastUserLikedAlbumsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LastUserLikedAlbumsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LastUserLikedAlbumsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
