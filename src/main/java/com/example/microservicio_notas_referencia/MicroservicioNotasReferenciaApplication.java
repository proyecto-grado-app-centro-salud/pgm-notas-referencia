package com.example.microservicio_notas_referencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MicroservicioNotasReferenciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioNotasReferenciaApplication.class, args);
	}

}
