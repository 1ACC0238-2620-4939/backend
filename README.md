# Trakto Route Backend

API REST de Trakto Route construida con Spring Boot y Java 25. Implementa los bounded contexts de viajes, seguimiento, flota y perfiles.

## Capacidades disponibles

- `/api/v1/trips`: creación, consulta y cambios de estado de viajes.
- `/api/v1/trackings`: inicio, posiciones, paradas y cierre del seguimiento.
- `/api/v1/vehicles`: registro, consulta, disponibilidad y actualización de vehículos.
- `/api/v1/drivers`: registro, consulta, disponibilidad y actualización de conductores.
- `/api/v1/profiles`: creación, consulta y actualización de perfiles.
- `/swagger-ui.html`: documentación interactiva OpenAPI.
- `/v3/api-docs`: contrato OpenAPI en JSON.

## Variables de entorno

| Variable | Descripción | Valor local predeterminado |
|---|---|---|
| `DATABASE_URL` | Host de MySQL | `localhost` |
| `DATABASE_PORT` | Puerto de MySQL | `3306` |
| `DATABASE_NAME` | Base de datos | `trakto-route-db` |
| `DATABASE_USER` | Usuario de MySQL | `root` |
| `DATABASE_PASSWORD` | Contraseña de MySQL | `12345678` |
| `PORT` | Puerto HTTP | `8080` |

## Validación local

El test usa H2 en memoria y no requiere una instancia local de MySQL.

```powershell
$env:JAVA_HOME='C:\Program Files\Android\Android Studio\jbr'
.\mvnw.cmd test
```

## Contenedor

```powershell
docker build -t trakto-route-backend:tb1 .
```

La imagen usa Java 25 tanto para compilar como para ejecutar. En producción se deben configurar las variables de base de datos antes de iniciar el contenedor.

## Repositorios relacionados

- [Aplicación móvil](https://github.com/1ACC0238-2620-4939/mobile-app)
- [Landing page](https://github.com/1ACC0238-2620-4939/landing-page)
- [Informe](https://github.com/1ACC0238-2620-4939/Report)
