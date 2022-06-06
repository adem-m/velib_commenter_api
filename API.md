# API Documentation

## Authentication

### POST /login

##### Request

```json
{
  "email": "string",
  "password": "string"
}
```

##### Response

Code 201

```json
{
  "token": "string"
}
```

### POST /users

##### Request

```json
{
  "email": "string",
  "password": "string",
  "firstName": "string",
  "lastName": "string"
}
```

##### Response

Code 201 - Empty body

## Comments

### GET /comments/{stationId}

##### Response

Code 200

```json
[
  {
    "id": "string",
    "stationId": "string",
    "userId": "string",
    "content": "string",
    "createdAt": "string",
    "image": "string (base64) | null"
  }
]
```

### POST /comments

##### Request (form-data)

Header: Authorization: Bearer {token}

```
  "image": "file | null",
  "content": "string",
  "stationId": "string"
```

##### Response

Code 201 - Empty body

### PUT /comments/{commentId}

##### Request

Header: Authorization: Bearer {token}

```json
{
  "content": "string"
}
```

##### Response

Code 200 - Empty body

### DELETE /comments/{commentId}

##### Request

Header: Authorization: Bearer {token}

##### Response

Code 204 - Empty body