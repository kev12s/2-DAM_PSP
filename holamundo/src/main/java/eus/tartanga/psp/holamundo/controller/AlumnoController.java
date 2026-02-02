package eus.tartanga.psp.holamundo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import eus.tartanga.psp.holamundo.model.Alumno;
import eus.tartanga.psp.holamundo.service.AlumnoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

	private final AlumnoService alumnoService;

	public AlumnoController(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}

	// 🔹 1. Obtener todos los alumnos
	@GetMapping
	public ResponseEntity<List<Alumno>> obtenerTodosLosAlumnos() {
		List<Alumno> alumnos = alumnoService.obtenerTodosLosAlumnos();
		return ResponseEntity.ok(alumnos);
	}

	// 🔹 2. Buscar alumno por ID
	@GetMapping("/{id}")
	public ResponseEntity<Alumno> obtenerAlumnoPorId(@PathVariable Integer id) {
		Alumno alumno = alumnoService.obtenerAlumnoPorId(id);

		if (alumno!=null) {
			return ResponseEntity.ok(alumno);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// 🔹 3. Buscar alumnos por curso
	@GetMapping("/curso/{curso}")
	public ResponseEntity<List<Alumno>> obtenerAlumnosPorCurso(@PathVariable String curso) {
		List<Alumno> alumnos = alumnoService.obtenerAlumnosPorCurso(curso);

		if (alumnos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(alumnos);
		}
	}

	// 🔹 4. Devolver cuántos alumnos hay
	@GetMapping("/count")
	public ResponseEntity<Integer> contarAlumnos() {
		Integer total = alumnoService.contarAlumnos();

		return ResponseEntity.ok(total);
	}

	// 🔹 5. Buscar por nombre parcial
	@GetMapping("/buscar")
	public ResponseEntity<List<Alumno>> buscarAlumnosPorNombre(@RequestParam String nombre) {
		List<Alumno> alumnos = alumnoService.buscarAlumnosPorNombre(nombre);

		if (alumnos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(alumnos);
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<Boolean> crearAlumno(@RequestBody Alumno alumno){

		if(alumno.getNombre() == null || alumno.getNombre().isEmpty() || 
				alumno.getCurso() == null || alumno.getCurso().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		boolean creado = alumnoService.crearAlumno(alumno);

		if (creado) {
			return ResponseEntity.status(201).body(true);
		}

		return ResponseEntity.status(500).body(false);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<Alumno> modificarAlumno(@RequestBody Alumno alumno){

		if(alumno.getNombre() == null || alumno.getNombre().isEmpty() || 
				alumno.getCurso() == null || alumno.getCurso().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		boolean creado = alumnoService.actualizarAlumno(alumno);

		if (creado) {
			return ResponseEntity.status(200).body(alumno);
		}

		return ResponseEntity.status(500).body(null);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Boolean> eliminarAlumno(@PathVariable int id){

		//si id es null
		if(id<=0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		//si el alumno no existe
		Alumno alumno = alumnoService.obtenerAlumnoPorId(id);

		if (alumno==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
		}

		//se ha eliminado
		boolean eliminado = alumnoService.eliminarAlumno(id);

		if (eliminado) {
			return ResponseEntity.status(200).build();
		}

		return ResponseEntity.status(500).body(null);
	}
}