FROM openjdk:21

WORKDIR /usrapp/bin

ENV PORT 8080
ENV SPRING_DATASOURCE_URL=jdbc:mysql://98.82.9.114:3306/properties?createDatabaseIfNotExist=true
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=my-secret-pw

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","escuelaing.edu.arep.bonoParcial.BonoParcialApplication"]