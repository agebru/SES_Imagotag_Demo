# SES_Imagotag_Demo

API's

1-  POST
    http://localhost:8080/api/v1/items
[
{"id":"aaa1", "price":4.54, "name":"5234"},
{"id":"aaa2", "price":2.54, "name":"3235"},
{"id":"aaa3", "price":1.54, "name":"1236"},
{"id":"aaa4", "price":3.54, "name":"5237"},
{"id":"aaa5", "price":5.66, "name":"2456"},
{"id":"aaa6", "price":5.66, "name":"1234"}
]

2- GET 
   http://localhost:8080/api/v1/items/{itemId}


3- GET
  http://localhost:8080/api/v1/items/{itemId}/reverse

4- GET
   http://localhost:8080/api/v1/items/sort

5- POST
   http://localhost:8080/api/v1/items/iterate
      QueryParams:
         page : int
         pageSize : int
         sort : boolean,
         reverseName : boolean

6- GET
    http://localhost:8080/api/v1/throwException

7- POST
   http://localhost:8080/api/v1/incInt/{intId}

8- GET
   http://localhost:8080/api/api/v1/keys