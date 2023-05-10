import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContenidoSeleccionadoComponent } from './contenido-seleccionado.component';

describe('ContenidoSeleccionadoComponent', () => {
  let component: ContenidoSeleccionadoComponent;
  let fixture: ComponentFixture<ContenidoSeleccionadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContenidoSeleccionadoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContenidoSeleccionadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
