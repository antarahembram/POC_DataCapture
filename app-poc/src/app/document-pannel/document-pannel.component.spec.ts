import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentPannelComponent } from './document-pannel.component';

describe('DocumentPannelComponent', () => {
  let component: DocumentPannelComponent;
  let fixture: ComponentFixture<DocumentPannelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DocumentPannelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentPannelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
