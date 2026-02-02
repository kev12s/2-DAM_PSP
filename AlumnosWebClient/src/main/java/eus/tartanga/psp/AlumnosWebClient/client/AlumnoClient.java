package eus.tartanga.psp.AlumnosWebClient.client;

import eus.tartanga.psp.AlumnosWebClient.model.Alumno;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.List;

public class AlumnoClient {
    
    private final WebClient webClient;
    private final String baseUrl;
    
    // Constructor con URL base
    public AlumnoClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    
    // Obtener todos los alumnos
    public List<Alumno> getAllAlumnos() {
        try {
            Alumno[] alumnosArray = webClient.get()
                    .uri("/alumnos")
                    .retrieve()
                    .bodyToMono(Alumno[].class)
                    .block();
            
            return Arrays.asList(alumnosArray != null ? alumnosArray : new Alumno[0]);
        } catch (WebClientResponseException e) {
            System.err.println(e.getStatusCode());
            return List.of();
        }
    }
    
    // Obtener alumno por ID
    public Alumno getAlumnoById(int id) {
        try {
            return webClient.get()
                    .uri("/alumnos/{id}", id)
                    .retrieve()
                    .bodyToMono(Alumno.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            System.err.println("Alumno con ID " + id + " no encontrado");
            return null;
        } catch (WebClientResponseException e) {
            System.err.println(e.getStatusCode());
            return null;
        }
    }
    
    // Obtener alumnos por curso
    public List<Alumno> getAlumnosByCurso(String curso) {
        try {
            Alumno[] alumnosArray = webClient.get()
                    .uri("/alumnos/curso/{curso}", curso)
                    .retrieve()
                    .bodyToMono(Alumno[].class)
                    .block();
            
            return Arrays.asList(alumnosArray != null ? alumnosArray : new Alumno[0]);
        } catch (WebClientResponseException e) {
            System.err.println(e.getStatusCode());
            return List.of();
        }
    }
    
    // Obtener total de alumnos
    public Integer getTotalAlumnos() {
        try {
            return webClient.get()
                    .uri("/alumnos/count")
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .block();
        } catch (WebClientResponseException e) {
            System.err.println(e.getStatusCode());
            return null;
        }
    }
    
    // Crear nuevo alumno
    public boolean createAlumno(Alumno alumno) {
        try {
            return webClient.post()
                    .uri("/alumnos/crear")
                    .bodyValue(alumno)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException e) {
            System.err.println("Error al crear alumno: " + e.getStatusCode());
            return false;
        }
    }
    
    // Actualizar alumno 
    public Alumno updateAlumno(Alumno alumno) {
        try {
            return webClient.put()
                    .uri("/alumnos/actualizar")
                    .bodyValue(alumno)
                    .retrieve()
                    .bodyToMono(Alumno.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            System.err.println("Alumno con ID no encontrado para actualizar");
            return alumno;
        } catch (WebClientResponseException e) {
            System.err.println(e.getStatusCode());
            return null;
        }
    }
    
    // Eliminar alumno
    public boolean deleteAlumno(int id) {
        try {
            webClient.delete()
                    .uri("/alumnos/eliminar/{id}", id)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            System.err.println("Alumno con ID no encontrado para eliminar");
            return false;
        } catch (WebClientResponseException e) {
            System.err.println(e.getStatusCode());
            return false;
        }
    }
}