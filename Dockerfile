FROM openjdk:latest
EXPOSE 8080
ADD build/libs/todo-list-app.jar todo-list-app.jar
ENTRYPOINT ["java","-jar","/todo-list-app.jar"]