# Spring Boot API

The simple Spring Boot API with no dependencies with docker image and prepared
kubernetes deployment.

Easy and quick deployment to kubernetes cluster with actuator endpoints enabled.

## Docker image

[The docker image is available on my DockerHub account.](https://hub.docker.com/repository/docker/pnowy/spring-boot-api/general)

[You will find the tags here.](https://hub.docker.com/repository/docker/pnowy/spring-boot-api/tags)

## Kubernetes deployment

## Additional endpoints

- `/` - returns you ip address
- `/ip/public` - returns the public address of the instance base on the call to `https://ipecho.net/plain`
- `/ip?address=XXX` - return the response returned by URL `address` request param defined (as text)  