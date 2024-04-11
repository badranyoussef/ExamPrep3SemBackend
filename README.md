### 1.5 creating a http file to test endpoints

POST http://localhost:7070/healthstore/api/healthproducts/initiate
Respons: Products initialized

GET http://localhost:7070/healthstore/api/healthproducts/
Respons: [
{
"id": 4,
"category": "Mineral",
"name": "Zink",
"price": 74.9,
"calories": 329,
"description": "Zink for bedre sundhed og velvære.",
"expireDate": [
2026,
4,
10
]
},
{
"id": 1,
"category": "Mineral",
"name": "Magnesium",
"price": 63.36,
"calories": 229,
"description": "Magnesium for bedre sundhed og velvære.",
"expireDate": [
2026,
4,
10
]
},
{
"id": 6,
"category": "Vitamin",
"name": "Vitamin C",
"price": 64.08,
"calories": 356,
"description": "Vitamin C for bedre sundhed og velvære.",
"expireDate": [
2026,
4,
10
]
},
{
"id": 9,
"category": "Fiber",
"name": "Psyllium Husk",
"price": 17.83,
"calories": 263,
"description": "Psyllium Husk for bedre sundhed og velvære.",
"expireDate": [
2026,
4,
10
]
},
{
"id": 5,
"category": "Mineral",
"name": "Magnesium",
"price": 8.36,
"calories": 65,
"description": "Magnesium for bedre sundhed og velvære.",
"expireDate": [
2026,
4,
10
]
},
{
"id": 7,
"category": "Omega-3",
"name": "Fiskeolie",
"price": 30.68,
"calories": 0,
"description": "Fiskeolie for bedre sundhed og velvære.",
"expireDate": [
2026,
4,
10
]
},
{
"id": 3,
"category": "Fiber",
"name": "Psyllium Husk",
"price": 64.59,
"calories": 91,
"description": "Psyllium Husk for bedre sundhed og velvære.",
"expireDate": [
2026,
4,
10
]
},
{
"id": 2,
"category": "Vitamin",
"name": "Multivitamin",
"price": 25.97,
"calories": 101,
"description": "Multivitamin for bedre sundhed og velvære.",
"expireDate": [
2026,
4,
10
]
},
{
"id": 8,
"category": "Vitamin",
"name": "Vitamin C",
"price": 34.0,
"calories": 456,
"description": "Vitamin C for bedre sundhed og velvære.",
"expireDate": [
2026,
4,
10
]
}
]

GET http://localhost:7070/healthstore/api/healthproducts/1
Respons:
{
"id": 1,
"category": "Mineral",
"name": "Magnesium",
"price": 63.36,
"calories": 229,
"description": "Magnesium for bedre sundhed og velvære.",
"expireDate": [
2026,
4,
10
]
}

POST http://localhost:7070/healthstore/api/healthproducts/
Respons:
{
"id": 10,
"category": "Vitamin",
"name": "Vitamin D3 5000 IU",
"price": 15.99,
"calories": 0,
"description": "Hjælper med at opretholde sunde knogler og tænder.",
"expireDate": [
2026,
4,
10
]
}

PUT http://localhost:7070/healthstore/api/healthproducts/10
Respons:
{
"id": 10,
"category": "testing cat change",
"name": "Vitamin D3 5000 IU",
"price": 15.99,
"calories": 0,
"description": "Hjælper med at opretholde sunde knogler og tænder.",
"expireDate": [
2024,
12,
31
]
}

DELETE http://localhost:7070/healthstore/api/healthproducts/
Respons: Product successfully deleted