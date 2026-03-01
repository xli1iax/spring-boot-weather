This is a small Spring Boot application created as a test assignment.
It provides current weather information for a given city using the OpenWeather API.
The application accepts a city name via a REST endpoint and returns:
weather condition (description)
temperature in Celsius
wind speed in km/h

Example request:
GET /weather?city=Bratislava

The project has a simple layered structure (controller → service → DTOs) and includes basic validation and error handling.
The OpenWeather API key is loaded from an environment variable and is not stored in the repository.
