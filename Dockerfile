FROM tomcat:9.0.86-jdk17-corretto-al2

COPY target/text-quest.war /usr/local/tomcat/webapps

EXPOSE 8080

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]