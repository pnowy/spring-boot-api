package com.github.pnowy.spring.api;

import static com.google.common.base.MoreObjects.firstNonNull;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class Index {

	private static final String SPRING_APPLICATION_NAME_PROPERTY = "spring.application.name";

	private final Environment environment;
	private final RestTemplate restTemplate;

	@GetMapping("/")
	public Map<String, String> index(HttpServletRequest request) {
		// @formatter:off
        return Map.of(
                SPRING_APPLICATION_NAME_PROPERTY, environment.getProperty(SPRING_APPLICATION_NAME_PROPERTY, "?"),
                "request.remoteAddr", firstNonNull(request.getRemoteAddr(), "?"),
                "request.remoteHost", firstNonNull(request.getRemoteHost(), "?"),
                "request.remotePort", firstNonNull(String.valueOf(request.getRemotePort()), "?")
        );
        // @formatter:on
	}

	@GetMapping("/ip/public")
	public String publicIp() {
		return restTemplate.getForObject("http://ifconfig.co", String.class);
	}

	@GetMapping("/ip")
	public String publicIp(@RequestParam String address) {
		return restTemplate.getForObject(address, String.class);
	}

}