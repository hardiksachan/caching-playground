# Caching Playground
I'm creating this project to teach myself 
  - basics of [Ktor Server](https://ktor.io)
  - caching remote data in Android
  
This repository includes two projects, a server and a client.

# Server

The server exposes a REST endpoint which fetches a list of "fake" people data. Here's an example

### Request

```curlrc
GET https://yourdomain.com/people?q=4
```

### Response

```json
[
    {
        "name":"Raymonde Larson",
        "city":"Shuside, Massachusetts",
        "email":"fritz.cummings@yahoo.com"
    },
    {
        "name":"Dr. Devin Hilpert",
        "city":"West Edwin, Texas",
        "email":"lauralee.grimes@yahoo.com"
    },
    {
        "name":"Vasiliki Douglas",
        "city":"Nuland, New Jersey",
        "email":"modesto.fadel@yahoo.com"
    },
    {
        "name":"Marlin Lehner",
        "city":"Selenaberg, Ohio",
        "email":"ms.long.mertz@hotmail.com"
    }
]
```
