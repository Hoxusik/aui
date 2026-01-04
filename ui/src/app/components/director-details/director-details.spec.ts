import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectorDetails } from './director-details';

describe('DirectorDetails', () => {
  let component: DirectorDetails;
  let fixture: ComponentFixture<DirectorDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DirectorDetails]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DirectorDetails);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
