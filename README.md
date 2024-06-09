# Currency_Tracker

![Static Badge](https://img.shields.io/badge/java-v17.0.10-green)
![NPM Version](https://img.shields.io/npm/v/angular)
![Static Badge](https://img.shields.io/badge/docker-v26.1.0-purple)

# Project Description

A web application that allows users to check currency values by code from the NBP server. The application also saves and displays query history.

<p align="center">
  <img src="https://github.com/maciej-MKan/Currency_Tracker/blob/main/assets/tracker.png" alt="logo" width="460"/>
</p>

## Installation

1. Clone the repository to your computer (git is required):
   ```git clone https://github.com/maciej-MKan/Currency_Tracker```
2. Navigate to the project directory:
   ```cd Currency_Tracker```
3. Create an `.env` configuration file in the root directory and fill it as shown in `.env.example`
4. Run Docker Compose to start the application (docker compose is required):
   ```docker compose up```
5. Open your browser and navigate to localhost:3000:


## API Endpoints
### POST /currencies/get-current-currency-value-command

Description: Retrieves the current value of a specified currency.

Request Body:
```json
{
  "currency": "EUR",
  "name": "Jan Nowak"
}
```

Response:

```json
{
  "value": 4.2954
}
```
### GET /currencies/requests

Description: Retrieves the history of currency requests.

Response:

```json
[
  {
    "currency": "EUR",
    "name": "Jan Nowak",
    "date": "2022-01-01T10:00:00.000Z",
    "value": 4.2954
  }
]
```


## Technologies

- Spring Boot - Java framework for building web backends.
- Angular - JavaScript framework for building web frontends.
- Docker - Platform for developing, shipping, and running applications in containers.
- REST - Architectural style for building distributed systems based on HTTP.

## Authors

- [maciej-MKan](https://github.com/maciej-MKan)

## License

This project is licensed under the MIT License. For more information, see the [LICENSE](https://github.com/maciej-MKan/chess/blob/main/LICENSE) file.