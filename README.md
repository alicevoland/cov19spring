# Covid 19 API

L'objectif est de créer une petite API pour faire une interface à data.gouv.fr

<https://cov19api.herokuapp.com>

## Documentation de l'API

<https://cov19api.herokuapp.com/docs/index.html>

## Dev

Exemple base de données Postgres via docker en développement :

```bash
docker run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=dev -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql postgres
```

