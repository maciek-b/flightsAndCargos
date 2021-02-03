# FlightsAndCargos

Java application with MongoDB with simple calculations 

## Running:

0. Docker installed needed (or running mongo on localhost:27017)
1. `docker-compose -f docker-compose/mongo/docker-compose.yml up -d`
2. `mvn clean install`
3. start `FlightsAndCargosApplication.java`

### Using:

- `/weight`

with query parameters: `flightNumber` and `date` (departure date)

i.e: `http://localhost:8080/weight?flightNumber=9202&date=2015-05-04`

- `/flight`

with query parameters: `code` (airport code) and `date` (departure date)
  
i.e:`http://localhost:8080/flight?code=YYZ&date=2016-04-07`