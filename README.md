# Project Title
Courier tracking system.

## Description
This project process gps signals of the couriers.

## Getting Started
* Install the docker desktop app.
* Install the postman app.
* We are ready!

### Dependencies
* Basically, only docker desktop and postman app.
* If you want to track the persisted data on database, you can use any database connection tool you fell comfortable.

### Installing
* Clone this repo and swithc to dev branch.
* Go to main project path. You should see pom.xml, DockerFile, docker-compose, ....
* Open terminal on this path.
* Run 'docker-compose up -d --build'.
* This command create 3 container: app, postgres, redis
* If there is any error on postgres, you can check postgis and enable: https://postgis.net/documentation/getting_started
* Your app will be hosted on localhost:8080
* Check the app if it is run: http://localhost:8080/stores
* If you have any problem? -> contact to me: tarikcoskun@hotmail.com.tr

### Executing program
* Go main project path.
* You can see '/postman' directory.
* These 3 collection should be imported to postman app.
* Main collection: courier-geo
* courier-geo apis are generate GPS signal request: start-drive, geo-signal (signal generation) and finish-drive
* Every courier must start driving to generate signal. First signal generates while starting.
* If driving has been started? So, you can generate signal via geo-signal.
* You can finish the driving by call finish-drive api.

### Postman Simulation
1- start-driving: Select just courierId (setted time too early date: 1900-01-01T00:00:00.000000Z)
  - Every courier that started driving saving in global variable.

2- geo-signal: postman iteration... Ready to start... Pre-request script solves everting.
  ![image](https://github.com/user-attachments/assets/d0d8f5b4-1c65-492f-9f48-153f75f18765)
  - This script simulates the courier motions randomly and logically.
  - Last location and courier id save to global variable to generate signal logically.

3- finish-drive: Select just courierId (setted time too late date: 2100-12-31T23:59:59.000000Z)
  - Every courier that finish driving remove from global variable.

4- Some global variable that you might find useful.
  ![image](https://github.com/user-attachments/assets/68172030-e300-4fd6-8e7f-075d2705b9e0)
  - base-url: http://localhost:8080
  - courier-base-url: {{base-url}}/couriers
  - store-base-url: {{base-url}}/stores
  - storeLocations: [
                      {
                        "lat": 40.9923307,
                        "lng": 29.1244229
                      },
                      {
                        "lat": 40.986106,
                        "lng": 29.1161293
                      },
                      {
                        "lat": 41.0066851,
                        "lng": 28.6552262
                      },
                      {
                        "lat": 41.055783,
                        "lng": 29.0210292
                      },
                      {
                        "lat": 40.9632463,
                        "lng": 29.0630908
                      }
                    ]

### Simple Architecture
* start-drive: first signal -> starting driving...
* geo-signal: Every signal handle on a Message Queue.
* Periodically and in batch, signals persists on DB.
* finish-drive: last signal -> finishing driving.
* This story means "1 driving".
* When receive last signal, couirer signals on MQ are persists if available.
* Calculate travel distance of "1 driving" and add on total distance.
* Enterance in range of stores (100m) was managed on simple Redis cache. !!! Only 1 enterance in 1 minute !!!
* Enterances log to file.
* Geographical calculations are performing on PostgreSQL.
