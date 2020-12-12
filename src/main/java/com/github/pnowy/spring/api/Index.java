package com.github.pnowy.spring.api;

import static com.google.common.base.MoreObjects.firstNonNull;

import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class Index {

	private static final String SPRING_APPLICATION_NAME_PROPERTY = "spring.application.name";
	private static final String SPRING_APPLICATION_ENV_PROPERTY = "spring.application.env";

	private final Environment environment;
	private final RestTemplate restTemplate;

	@GetMapping("/")
	public Map<String, String> index(HttpServletRequest request) {
		// @formatter:off
        return Map.of(
                SPRING_APPLICATION_NAME_PROPERTY, environment.getProperty(SPRING_APPLICATION_NAME_PROPERTY, "?"),
				SPRING_APPLICATION_ENV_PROPERTY, environment.getProperty(SPRING_APPLICATION_ENV_PROPERTY, "?"),
                "request.remoteAddr", firstNonNull(request.getRemoteAddr(), "?"),
                "request.remoteHost", firstNonNull(request.getRemoteHost(), "?"),
                "request.remotePort", firstNonNull(String.valueOf(request.getRemotePort()), "?")
        );
        // @formatter:on
	}

	@GetMapping("/ip/public")
	public String publicIp() {
		return restTemplate.getForObject("https://api.ipify.org?format=json", String.class);
	}

	@GetMapping("/ip")
	public String publicIp(@RequestParam String address) {
		return restTemplate.getForObject(address, String.class);
	}

	@GetMapping("/exception")
	public String exception(@RequestParam(value = "message", required = false, defaultValue = "ExceptionLogged") String message) {
		try {
			if (1 == 1) {
				throw new IllegalArgumentException(message);
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return LocalDateTime.now() + " exception-logged";
	}

	@GetMapping("/log")
	public String log(@RequestParam(value = "message", required = false, defaultValue = "exampleMessage") String message) {
		log.info("MessageLogged: {}", message);
		return LocalDateTime.now() + " message-logged";
	}

}