mvn archetype:generate -DgroupId=com.sqli.framework -DartifactId=frame -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1 -DinteractiveMode=false
cd frame
mvn package
java -cp target/frame-1.jar com.sqli.framework.frame -hq
cd ..