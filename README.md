
# Cov19Spring: Spring Boot Backend

## Cov19Toyprojects

This project is part of [Cov19Toyprojects](https://github.com/mvoland/cov19toyprojects). These are small toy projects using Covid open data, for learning purposes. The projects are:

* [Cov19Spring](https://github.com/mvoland/cov19spring), a Spring Boot backend (this project)
* [Cov19React](https://github.com/mvoland/cov19react), a Reactfrontend
* [Cov19Django](https://github.com/mvoland/cov19django), a Django backend

## Presentation

This project aims at providing a REST API for some covid statistics from the french government open data platform [data.gouv.fr](https://www.data.gouv.fr/en/pages/donnees-coronavirus)

More information and API documentation can be found here: [Covid 19 API docs](https://cov19spring.herokuapp.com) (hosted on a free heroku dyno, can take a few seconds to wake up)

## Frontend

A REACT frontend is available here: [Covid 19 React](https://cov19react.herokuapp.com) (hosted on a free heroku dyno, can take a few seconds to wake up)

([GitHub project](https://github.com/mvoland/cov19react))

## Development

This project is using the spring boot framework, and a postgresql database.

```bash
docker run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=dev -d -p 5432:5432 postgres
```

