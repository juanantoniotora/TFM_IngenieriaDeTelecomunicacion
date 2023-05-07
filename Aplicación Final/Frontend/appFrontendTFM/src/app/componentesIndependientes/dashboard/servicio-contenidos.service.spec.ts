import { TestBed } from '@angular/core/testing';

import { ServicioContenidosService } from './servicio-contenidos.service';

describe('ServicioContenidosService', () => {
  let service: ServicioContenidosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioContenidosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
