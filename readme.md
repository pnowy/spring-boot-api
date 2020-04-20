# Spring Boot API - unofficial HELM chart

Starter Spring Boot API project with docker image (JIB or Dockerfile) and prepared kubernetes deployment (Native, Kustomize, Helm Chart).

Easy and quick deployment to kubernetes cluster with all good practices applied.

## Docker image

[The docker image is available on DockerHub account.](https://hub.docker.com/r/pnowy/spring-boot-api)

[You will find available the tags here.](https://hub.docker.com/r/pnowy/spring-boot-api/tags)

Tags description:
  - `X.Y.X` - semver release
  - `release` - 'latest' for any stable released version (tagged)
  - `snapshot` - 'latest' for each version available on develop branch, generated after every commit
  - `latest` - 'latest' - any latest build available, can be `snapshot`, can be `release`

## Kubernetes deployment

The suggested way to deploy on Kubernetes cluster is a Helm chart available with full of the options.

The details about chart you will find [TODO - add reference](add reference to released helm chart).

## Available endpoints

- `/` - returns you ip address
- `/ip/public` - returns the public address of the instance base on the call to `http://ifconfig.co`
- `/ip?address=XXX` - return the response returned by URL `address` request param defined (as text)  