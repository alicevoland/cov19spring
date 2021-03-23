# Covid 19 API

L'objectif est de créer une petite API pour faire une interface à data.gouv.fr

<https://cov19api.herokuapp.com>

Documentation dans /doc

## Dev

Local database for dev:

    docker run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=dev -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql postgres

## Some endpoints

*Work In Progress*

    GET https://cov19api.herokuapp.com/api/v1/hospitalisations/regions
    GET https://cov19api.herokuapp.com/api/v1/hospitalisations/intensiveCareAdmissions
    GET https://cov19api.herokuapp.com/api/v1/hospitalisations/regionalHospitalisations
