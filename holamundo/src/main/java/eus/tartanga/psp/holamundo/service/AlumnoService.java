package eus.tartanga.psp.holamundo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import eus.tartanga.psp.holamundo.model.Alumno;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlumnoService {

	private List<Alumno> alumnos = new ArrayList<>();
	private final ObjectMapper objectMapper = new ObjectMapper();

	@PostConstruct
	public void init() {
		cargarAlumnosDesdeJSON();
	}

	private void cargarAlumnosDesdeJSON() {
		try {
			File file = ResourceUtils.getFile(getClass().getResource("/alumnos.json"));
			alumnos = objectMapper.readValue(file, new TypeReference<List<Alumno>>() {});
			System.out.println("JSON cargado correctamente. Total alumnos: " + alumnos.size());
		} catch (IOException e) {
			System.err.println("Error al cargar el archivo JSON: " + e.getMessage());
			alumnos = new ArrayList<>();
		}
	}

	// 1. Obtener todos los alumnos
	public List<Alumno> obtenerTodosLosAlumnos() {
		return new ArrayList<>(alumnos);
	}

	// 2. Buscar alumno por ID
	public Alumno obtenerAlumnoPorId(Integer id) {
		for (Alumno alumno : alumnos) {
			if (alumno.getId().equals(id)) {
				return alumno;
			}
		}

		return null;
	}

	// 3. Buscar alumnos por curso
	public List<Alumno> obtenerAlumnosPorCurso(String curso) {
		List<Alumno> resultado = new ArrayList<>();

		for (Alumno alumno : alumnos) {
			if (alumno.getCurso().equalsIgnoreCase(curso)) {
				resultado.add(alumno);
			}
		}

		return resultado;
	}

	// 4. Contar total de alumnos
	public Integer contarAlumnos() {
		return alumnos.size();
	}

	// 5. Buscar por nombre parcial (case-insensitive)
	public List<Alumno> buscarAlumnosPorNombre(String nombre) {
		List<Alumno> resultado = new ArrayList<>();
		String nombreBuscado = nombre.toLowerCase();

		for (Alumno alumno : alumnos) {
			String nombreAlumno = alumno.getNombre().toLowerCase();
			if (nombreAlumno.contains(nombreBuscado)) {
				resultado.add(alumno);
			}
		}

		return resultado;
	}

	//crear alumno
	public boolean crearAlumno(Alumno alumno) {

		try {
			int id = alumnos.size()+1;
			alumno.setId(id);

			alumnos.add(alumno);
			actualizarJson();
			return true;

		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//actualizar
	public boolean actualizarAlumno(Alumno alumnoModificado) {

		try {
			int posicionArray = alumnoModificado.getId()-1;

			alumnos.set(posicionArray, alumnoModificado);
			actualizarJson();
			return true;

		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	//eliminar alumno
		public boolean eliminarAlumno(int id) {
			try {
				Alumno alumno = obtenerAlumnoPorId(id);

				alumnos.remove(alumno);
				actualizarJson();
				System.out.println("Eliminado");
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		

	private void actualizarJson() {

		try {
			//sacar el json
			/*File file = ResourceUtils.getFile(getClass().getResource("/apk.json"));
			System.out.println("original: " + file);*/

			//convertir la listaApk a formato json
			String jsonActualizado = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(alumnos);

			System.out.println("actualizado: " + jsonActualizado);

			FileWriter writer = new FileWriter("src/main/resources/alumnos.json");
			writer.write(jsonActualizado);
			writer.close();

			System.out.println("json actualizado");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}