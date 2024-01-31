make: common/ controller/ model/ view/
	javac common/*.java
	javac controller/*.java
	javac model/*.java
	javac view/*.java


clean:
	rm controller/*.class
	rm view/*.class
	rm common/*.class
	rm model/*.class

setup: 
	rmiregistry 1234 &

runServer: 
	java model.RemoteStoreServer

runClient: 
	java view.StoreView
