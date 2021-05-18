import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DecodeFileComponent } from './decode-file.component';

describe('DecodeFileComponent', () => {
  let component: DecodeFileComponent;
  let fixture: ComponentFixture<DecodeFileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DecodeFileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DecodeFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
