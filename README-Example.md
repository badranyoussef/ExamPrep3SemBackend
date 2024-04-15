# NAME OF PROJECT

Link til at gøre links kortere: https://free-url-shortener.rb.gy/

### Udarbejdet af

Youssef Badran

### User stories & status

**Storage Stories**  
US1: enter user story her
**STATUS:** *DONE*

**Product Stories**  
US1: enter user story her  
**STATUS:** *DONE*

### API Documentation

### Product
| HTTP Method | Rest Ressource | Response body                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | Exception & status | Comment             |
|-------------|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------|---------------------|
| GET         | api/events     | response: [{"id":600,"title":"Advanced Pottery","category":"course","description":"Sculpt and mold your way to mastery.","date":[2024,4,4],"time":[17,0],"duration":2.0,"capacity":15,"location":"Art Studio","instructor":"Jane Smith","price":20.0,"image":"pottery.jpg","status":"active","createdAt":[2024,4,5],"updatedAt":[2024,4,5],"deletedAt":null},{"id":601,"title":"Beginner's Guitar","category":"talk","description":"Strum the strings with ease.","date":[2024,4,5],"time":[19,0],"duration":1.0,"capacity":10,"location":"Music Hall","instructor":"Alex Johnson","price":15.0,"image":"guitar.jpg","status":"active","createdAt":[2024,4,5],"updatedAt":[2024,4,5],"deletedAt":null}] | PIException & 404  | Retrieve all events |
| POST        | api/events     |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |                    |                     |

### Storage
| HTTP Method | Rest Ressource | Response body                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | Exception & status | Comment             |
|-------------|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------|---------------------|
| GET         | api/events     | response: [{"id":600,"title":"Advanced Pottery","category":"course","description":"Sculpt and mold your way to mastery.","date":[2024,4,4],"time":[17,0],"duration":2.0,"capacity":15,"location":"Art Studio","instructor":"Jane Smith","price":20.0,"image":"pottery.jpg","status":"active","createdAt":[2024,4,5],"updatedAt":[2024,4,5],"deletedAt":null},{"id":601,"title":"Beginner's Guitar","category":"talk","description":"Strum the strings with ease.","date":[2024,4,5],"time":[19,0],"duration":1.0,"capacity":10,"location":"Music Hall","instructor":"Alex Johnson","price":15.0,"image":"guitar.jpg","status":"active","createdAt":[2024,4,5],"updatedAt":[2024,4,5],"deletedAt":null}] | PIException & 404  | Retrieve all events |
| POST        | api/events     |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |                    |                     |


### Test

Tests of daos: https://rb.gy/f8jk0z  
Tests of endpoints: https://rb.gy/qgesnr

