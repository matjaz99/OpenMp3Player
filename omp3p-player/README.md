## OpenMp3Player v4




$ mvn install:install-file -Dfile=/Users/matjaz/Developer/git-workspace/OpenMp3Player/omp3p-v3/lib/jid3lib-0.5.4.jar -DgroupId=org.farng -DartifactId=jid3lib -Dversion=0.5.4 -Dpackaging=jar

$ mvn install:install-file -Dfile=/Users/matjaz/Developer/git-workspace/OpenMp3Player/omp3p-v3/lib/jlayer-1.0.1.jar -DgroupId=javazoom.jl -DartifactId=jlayer -Dversion=1.0.1 -Dpackaging=jar



Tutorial with Derby on Spring Boot

https://github.com/springframeworkguru/spring-boot-apache-derby-example
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

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



