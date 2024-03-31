FROM tomcat:10.1.18

COPY /target/Examen_JEE-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh","run"]