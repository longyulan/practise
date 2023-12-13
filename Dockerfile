FROM java
#前面是自己jar包名字，后面是你重命名的名字
COPY practise-bootstrap-1.0-SNAPSHOT.jar practise-bootstrap-1.0-SNAPSHOT.jar
RUN bash -c "touch /practise-bootstrap-1.0-SNAPSHOT.jar"
#暴露的端口号
EXPOSE 8066
ENTRYPOINT ["java", "-jar", "practise-bootstrap-1.0-SNAPSHOT.jar"]
