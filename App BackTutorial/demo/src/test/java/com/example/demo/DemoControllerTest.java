package com.example.demo;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.example.demo.controller.DemoController;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Usuario;
import com.example.demo.service.DemoService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class DemoControllerTest {

    @InjectMocks
    DemoController demoController;

	@Mock
	Logger logger;

	@Mock
	DemoService miServicio;
	
	@Spy
    UsuarioMapper usuarioMapper;
	
	UsuarioDTO usuarioDTO = new UsuarioDTO();

	Usuario usuario = new Usuario();

	String detalleUsuario = "Detalle usuario";

	String ordenadosPor = "nombre";

	String idUsuario = "1";

	String nuevoDetalleUsuario = "Nuevo detalle usuario";

	@BeforeEach
	void setUp() throws Exception {
		this.logger = Mockito.mock(Logger.class);
		final Field loggerField = DemoController.class.getDeclaredField("logger");
		loggerField.setAccessible(true);
		loggerField.set(this.demoController, this.logger);

    	this.usuarioDTO.setId(1L); // Ejemplo de ID
		this.usuarioDTO.setNombre("Juan");
		this.usuarioDTO.setApellidos("Pérez");
		this.usuarioDTO.setEdad(30);
		this.usuarioDTO.setActivado(true);
		this.usuarioDTO.setTelefono(123456789l);
		this.usuarioDTO.setGenero(1);
		this.usuarioDTO.setAficion("Fútbol");

		this.usuario.setId(1L); // Ejemplo de ID
		this.usuario.setNombre("Juan");
		this.usuario.setApellidos("Pérez");
		this.usuario.setEdad(30);
		this.usuario.setActivado(true);
		this.usuario.setTelefono(123456789l);
		this.usuario.setGenero(1);
		this.usuario.setAficion("Fútbol");
	}

	/**
	 * Creation of DTO and Mapper Tests
	 */
	
	@Test
	void getters_UsuarioDTO() {
		Assertions.assertEquals(1L, this.usuarioDTO.getId());
		Assertions.assertEquals("Juan", this.usuarioDTO.getNombre());
		Assertions.assertEquals("Pérez", this.usuarioDTO.getApellidos());
		Assertions.assertEquals(30, this.usuarioDTO.getEdad());
		Assertions.assertEquals(true, this.usuarioDTO.getActivado());
		Assertions.assertEquals(123456789l, this.usuarioDTO.getTelefono());
		Assertions.assertEquals(1, this.usuarioDTO.getGenero());
		Assertions.assertEquals("Fútbol", this.usuarioDTO.getAficion());
	}


	@Test
	void comoUsuario_UsuarioMapper() {
		this.usuarioMapper.comoUsuario(this.usuarioDTO, this.detalleUsuario);
		Assertions.assertNotNull(this.usuarioMapper);
	}

	/**
	 * Creation of Controller Tests
	 */
	@Test
	void crearUsuario_DemoController_OK_Test() {
		Mockito.when(this.usuarioMapper.comoUsuario(usuarioDTO, detalleUsuario)).thenReturn(this.usuario);
		Mockito.when(this.miServicio.crearUsuario(usuario)).thenReturn(1L);

		ResponseEntity<Long> result =  this.demoController.crearUsuario(detalleUsuario, usuarioDTO);

		Assertions.assertEquals(200, result.getStatusCode().value());
		Assertions.assertEquals(1L, result.getBody());
		Mockito.verify(this.logger).info("FIN [DemoController.crearUsuario] Creando usuario.");
	}

	@Test
	void mostrarTodosLosUsuarios_DemoController_OK_Test() {
		Mockito.when(miServicio.devolverTodosLosUsuarios()).thenReturn(List.of(this.usuario));

		ResponseEntity<List<Usuario>> result =  this.demoController.mostrarTodosLosUsuarios(ordenadosPor);

		Assertions.assertEquals(200, result.getStatusCode().value());
		Assertions.assertEquals(List.of(this.usuario).size(), result.getBody().size());
	}


	@Test
	void modificarDetalleDeUsuario_DemoController_OK_Test() {
		Mockito.when(this.miServicio.modificarUsuario(Long.valueOf(idUsuario), this.nuevoDetalleUsuario)).thenReturn(this.usuario);

		ResponseEntity<Void> result = this.demoController.modificarDetalleDeUsuario(idUsuario, nuevoDetalleUsuario, this.usuarioDTO);

		Mockito.verify(miServicio).modificarUsuario(Long.valueOf(idUsuario), this.nuevoDetalleUsuario);
		Assertions.assertEquals(204, result.getStatusCode().value());
	}

	@Test
	void eliminarUsuario_DemoController_OK_Test() {
		String id = "1";
		Mockito.when(miServicio.eliminarUsuarioPorId(Long.valueOf(id))).thenReturn(1L);

		ResponseEntity<Void> result = this.demoController.eliminarUsuario(id);

		Assertions.assertEquals(404, result.getStatusCode().value());
	}

}
