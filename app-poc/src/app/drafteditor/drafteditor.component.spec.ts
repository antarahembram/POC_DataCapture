import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrafteditorComponent } from './drafteditor.component';

describe('DrafteditorComponent', () => {
  let component: DrafteditorComponent;
  let fixture: ComponentFixture<DrafteditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrafteditorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrafteditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
