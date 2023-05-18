![https://clevertec.ru/](/clevertec-img.png)


# final task

## Description
>Разработать RESTful web-service, реализующей функционал для работы с системой управления новостями.
Основные сущности:
>-	news (новость) содержит поля: id, time, title, text и comments (list).
>-	comment содержит поля: id, time, text, username и news_id.

**Technology stack:**
- Java 17
- Spring Boot 3
- LRU/LFU cache implementation
- Gradle 7.5
- PostgreSQL
- Liquibase Migration
- Junit 5
- Mockito
- TestContainers
- Docker


To start Spring Boot app with postgres DB in Docker container:
- gradle clean build -x test
- docker-compose up


  <h1>API Endpoints:</h1>

*News*
- *GET*  `/news/api/v1/news` *Find all News (with filter or without*
- *POST* `/news/api/v1/news` *Save new News*
- *GET* `/news/api/v1/news/{id}` *Find News by ID*
- *PUT* `/news/api/v1/news/{id}` *Update News by ID*
- *DELETE*  `/news/api/v1/news/{id}` *Delete News by ID*

*Comments*
- *GET*  `/news/api/v1/Comments` *Find all Comments (with filter or without)*
- *POST* `/news/api/v1/Comments` *Save new Comment*
- *GET* `/news/api/v1/Comments/{id}` *Find Comment by ID*
- *PUT* `/news/api/v1/Comments/{id}` *Update Comment by ID*
- *DELETE*  `/news/api/v1/Comments/{id}` *Delete Comment by ID*

[Spring REST Docs url (swagger)](http://localhost:8080/swagger-ui/index.html)
