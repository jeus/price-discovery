FROM registry.becopay.com/devops/java-docker
MAINTAINER jeus
ADD target/*.jar price-discovery.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/price-discovery.jar"]
EXPOSE 9190