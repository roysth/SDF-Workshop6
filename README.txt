## Server 

mvn compile exec:java -Dexec.mainClass="visa.workshop6.server.ServerApp" -Dexec.args="12345 \Users\Roy Soon\desktop\visa-workshop6\cookie_file.txt"




## Client


mvn compile exec:java -Dexec.mainClass="visa.workshop6.client.ClientApp" -Dexec.args="0.0.0.0:12345"