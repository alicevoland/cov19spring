# Covid 19 API

This project aims at providing a REST API for some covid statistics from the french government open data platform [data.gouv.fr](https://www.data.gouv.fr/en/pages/donnees-coronavirus)

More information and API documentation can be found here: [Covid 19 API docs](https://cov19api.herokuapp.com) (hosted on a free heroku dyno, can take a few seconds to wake up)

## Frontend

A REACT frontend is available here: [Covid 19 UI](https://cov19ui.herokuapp.com) (hosted on a free heroku dyno, can take a few seconds to wake up)

([GitHub project](https://github.com/mvoland/cov19ui))

## Development

This project is using the spring boot framework, and a postgresql database.

```bash
docker run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=dev -d -p 5432:5432 postgres
```

