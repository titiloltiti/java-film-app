import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RealisateurDetailComponent } from './realisateur-detail.component';

describe('RealisateurDetailComponent', () => {
  let component: RealisateurDetailComponent;
  let fixture: ComponentFixture<RealisateurDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RealisateurDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RealisateurDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
