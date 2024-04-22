# NAME OF PROJECT

Link til at gøre links kortere: https://free-url-shortener.rb.gy/

### Udarbejdet af

Youssef Badran

### User stories & status

**Storage Stories**  
US1: As a user, I want to see all available cars on the website to get an overview
**STATUS:** *DONE*

US2: As a user, I want to see details of a specific car by providing its ID
**STATUS:** *DONE*

US3: As a user, I want to see all cars for sale by a specific seller by providing the seller's ID
**STATUS:** *DONE*

US4: As a user, I want to add a new car to the listing to register it for sale
**STATUS:** *DONE*

US5: As a user, I want to update the information of an existing car by providing its ID.
**STATUS:** *DONE*

US6: As a user, I want to assign an existing car to a specific seller by providing both seller's and car's IDs.
**STATUS:** *DONE*

US7:  Delete a car: As a user, I want to delete a car from the listing by providing its ID.
**STATUS:** *DONE*


### API Documentation

### Product
| HTTP Method | Rest Ressource | Response body                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | Exception & status | Comment             |
|-------------|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------|---------------------|
| GET         | api/events     | [ { "id": 12, "brand": "NEW BRAND", "model": "VIVO2000", "make": "Denmark", "firstRegistrationDate": [ 2024, 4, 4 ], "price": 20000.0, "year": 2024 }, { "id": 5, "brand": "Kia", "model": "Optima", "make": "South Korea", "firstRegistrationDate": [ 2011, 11, 1 ], "price": 16000.0, "year": 2011 }, { "id": 13, "brand": "NEW BRAND", "model": "VIVO", "make": "Denmark", "firstRegistrationDate": [ 2024, 4, 4 ], "price": 20000.0, "year": 2024 }, { "id": 10, "brand": "BMW", "model": "3 Series", "make": "Germany", "firstRegistrationDate": [ 2015, 5, 1 ], "price": 30000.0, "year": 2015 }, { "id": 9, "brand": "Hyundai", "model": "Elantra", "make": "South Korea", "firstRegistrationDate": [ 2012, 7, 1 ], "price": 17000.0, "year": 2012 }, { "id": 8, "brand": "Volkswagen", "model": "Golf", "make": "Germany", "firstRegistrationDate": [ 2016, 12, 1 ], "price": 19000.0, "year": 2016 }, { "id": 3, "brand": "Honda", "model": "Civic", "make": "Japan", "firstRegistrationDate": [ 2019, 6, 1 ], "price": 25000.0, "year": 2019 }, { "id": 2, "brand": "Ford", "model": "Focus", "make": "USA", "firstRegistrationDate": [ 2018, 3, 1 ], "price": 18000.0, "year": 2018 }, { "id": 6, "brand": "Mercedes-Benz", "model": "C-Class", "make": "Germany", "firstRegistrationDate": [ 2014, 8, 1 ], "price": 35000.0, "year": 2014 }, { "id": 7, "brand": "Audi", "model": "A4", "make": "Germany", "firstRegistrationDate": [ 2013, 2, 1 ], "price": 28000.0, "year": 2013 }, { "id": 1, "brand": "Chevrolet", "model": "Cruze", "make": "USA", "firstRegistrationDate": [ 2017, 9, 1 ], "price": 22000.0, "year": 2017 }, { "id": 4, "brand": "Toyota", "model": "Corolla", "make": "Japan", "firstRegistrationDate": [ 2020, 1, 1 ], "price": 20000.0, "year": 2020 } ] | PIException & 404  | Retrieve all events |
| POST        | api/events     |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |                    |                     |

### Storage
| HTTP Method | Rest Ressource | Response body                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | Exception & status | Comment             |
|-------------|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------|---------------------|
| GET         | api/events     | response: [{"id":600,"title":"Advanced Pottery","category":"course","description":"Sculpt and mold your way to mastery.","date":[2024,4,4],"time":[17,0],"duration":2.0,"capacity":15,"location":"Art Studio","instructor":"Jane Smith","price":20.0,"image":"pottery.jpg","status":"active","createdAt":[2024,4,5],"updatedAt":[2024,4,5],"deletedAt":null},{"id":601,"title":"Beginner's Guitar","category":"talk","description":"Strum the strings with ease.","date":[2024,4,5],"time":[19,0],"duration":1.0,"capacity":10,"location":"Music Hall","instructor":"Alex Johnson","price":15.0,"image":"guitar.jpg","status":"active","createdAt":[2024,4,5],"updatedAt":[2024,4,5],"deletedAt":null}] | PIException & 404  | Retrieve all events |
| POST        | api/events     |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |                    |                     |


### Test

Tests of daos: https://rb.gy/f8jk0z  
Tests of endpoints: https://rb.gy/qgesnr

