# weatherchannel
api service for weather channel application

Weather Channel is a small application that simulates the Solar System for 3 planets generating a forecast considering astronomical events based on planet distribution every day.
Astronomical events:
- Drought: Sun and planets are align (fig. 1).
- Rain: Planets form a triangle and the sun is inside the planet (fig. 2).
- Optimal conditions: Planets are align without the sun (fig. 3).
- Normal: Any other planet distribution.
- 
![image](https://user-images.githubusercontent.com/58280145/133074974-6729d271-349f-47fe-90f5-e6936b2bde42.png)
![image](https://user-images.githubusercontent.com/58280145/133075037-112928ff-eeeb-4c73-8983-5f7296f7dc5d.png)
![image](https://user-images.githubusercontent.com/58280145/133075058-d817d270-7413-4cf6-aa9f-eb9e7b50a55b.png)



Technologies:
- Spring boot.
- Lombok for builder pattern.
- MySql as database
- JPA for persistance model.
- Google cloud dependencies for host application on Google cloud engine.
- Junit and Mockito core for test cases.

Web API:
* GET /forecast/day/{day} Retrieves forecast for an specific day.
* GET /forecast/generateForecast/{years} Generates forecast for a given amount of years and persist the information on the database.

Pending taskst
- Swagger for documentation.
- Finish Junit cases.
- Generate report base on scheduler or cron annotations.
