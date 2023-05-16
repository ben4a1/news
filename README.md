![https://clevertec.ru/](/clevertec-img.png)


# final task

## description
>Разработать RESTful web-service, реализующей функционал для работы с системой управления новостями.
Основные сущности:
>-	news (новость) содержит поля: id, time, title, text и comments (list).
>-	comment содержит поля: id, time, text, username и news_id.

[Spring REST Docs url](http://localhost:8080/swagger-ui/index.html)

To start Spring Boot app with postgres DB in Docker container (write in terminal):
* gradle clean build -x test
* docker-compose up --build
