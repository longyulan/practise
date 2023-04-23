FROM java:8
#前面是自己jar包名字，后面是你重命名的名字
COPY practise.jar app.jar
RUN bash -c "touch /app.jar"
#暴露的端口号
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
