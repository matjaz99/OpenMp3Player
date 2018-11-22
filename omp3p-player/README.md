## OpenMp3Player v4


Import external libraries into local maven repository:

$ mvn install:install-file -Dfile=./external_libs/jid3lib-0.5.4.jar -DgroupId=org.farng -DartifactId=jid3lib -Dversion=0.5.4 -Dpackaging=jar

$ mvn install:install-file -Dfile=./external_libs/jl1.0.1.jar -DgroupId=javazoom.jl -DartifactId=jlayer -Dversion=1.0.1 -Dpackaging=jar


#### Endpoints

Service endpoint: [http://localhost:8083/openmp3player/rest](http://localhost:8083/openmp3player/rest)


Support for Swager is based on tutorial:
https://www.vojtechruzicka.com/documenting-spring-boot-rest-api-swagger-springfox/

Swager API spec: [http://localhost:8083/v2/api-docs](http://localhost:8083/v2/api-docs)

Swager-UI: [http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)


#### Tutorials

Tutorial with Derby on Spring Boot

https://github.com/springframeworkguru/spring-boot-apache-derby-example
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

This tutorial explains OneToMany mapping great. But I still cannot figure out why bidirectional relationship in embedded Derby doesn't work.
https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/

https://github.com/mkyong/spring-embedded-database

Check this for mp3 player implementations in Java:

https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
https://www.youtube.com/watch?v=LavMuqK5Is0


Mp3 player from:

https://sourceforge.net/projects/javalayer/



DB

$ java -jar /Users/matjaz/Developer/db/db-derby-10.14.1.0-bin/lib/derbyrun.jar ij

ij> connect 'jdbc:derby:omp3pdb;';

ij(CONNECTION1)> show tables;

ij(CONNECTION1)> select * from mp3file;

ij(CONNECTION1)> exit;



