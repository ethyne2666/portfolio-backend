
Each of these is a feature module — a self-contained slice of your app that owns one piece of portfolio content. Instead of organizing by "type" (all controllers together, all entities together), you organize by "what it's about." This is called package-by-feature, and it's why each of these folders internally repeats the same controller/service/repository/entity/dto pattern.


project/ — Your project case studies. Backs the /projects/:slug page on your frontend. Holds things like title, summary, tech stack, architecture notes, GitHub link. This is the first one we're building.

snippet/ — Your reusable code snippets (Java, Spring, React, C++, DevOps). Backs the /snippets page. Much simpler than project/ — just title, code, language, description.

now/ — A single "what I'm currently building/learning" entry. Backs the /now page. The simplest module — usually just one active row in the database at a time.

contact/ — Handles your portfolio's contact form submissions. When someone fills out "Contact Me," this module saves their message to the database (later, you could add emailing yourself when a message arrives — but that's optional, not needed to start).

common/ — Not a feature — it's shared infrastructure every other module reuses. Right now it holds two things:

ApiResponse.java — a consistent wrapper so every endpoint returns JSON in the same shape ({ success, data, message }), instead of every controller inventing its own response format.
GlobalExceptionHandler.java — one central place that catches errors (like "project not found") and turns them into clean JSON error responses, instead of scattering try/catch everywhere.

> http://localhost:8080/actuator
```
{
  "_links": {
    "self": {
      "href": "http://localhost:8080/actuator",
      "templated": false
    },
    "info": {
      "href": "http://localhost:8080/actuator/info",
      "templated": false
    },
    "health": {
      "href": "http://localhost:8080/actuator/health",
      "templated": false
    },
    "health-path": {
      "href": "http://localhost:8080/actuator/health/{*path}",
      "templated": true
    }
  }
}
```

1)✅ Project module (done)
2)✅ Snippet module (next - same pattern, quick)
3)✅ Now module (same pattern, even quicker)
4)✅ Contact module (first one with a tiny bit of extra logic — validating and saving a message)
5)Then Lab - Mini Postman first (safest, no security needed), then JWT Playground (introduces Spring Security properly, scoped down), then System Design Lab last (most involved, benefits from everything before it)

> http://localhost:8080/api/snippets
```
{
  "data": [
    {
      "id": 1,
      "language": "JAVA",
      "title": "Hello World",
      "code": "System.out.println(\"Hello\");",
      "description": "Classic first program."
    }
  ],
  "message": null,
  "success": true
}
```

> http://localhost:8080/api/projects
```
{
  "data": [
    {
      "slug": "portfolio-backend",
      "title": "Portfolio Backend",
      "summary": "A Spring Boot backend for my portfolio.",
      "coverImageUrl": null,
      "technologies": []
    }
  ],
  "message": null,
  "success": true
}
```

> http://localhost:8080/api/now
```
{
  "data": {
    "content": "Building the backend for my portfolio — currently on the Now module.",
    "updatedAt": null
  },
  "message": null,
  "success": true
}
```

