all: run

clean:
	rm -f out/SubsetGenerator.jar
            
out/SubsetGenerator.jar: out/parcs.jar src/Main.java src/SubsetGenerator.java src/SerializableSet.java src/SerializableSubsets.java
	@javac -cp out/parcs.jar src/Main.java src/SubsetGenerator.java src/SerializableSet.java src/SerializableSubsets.java
	@jar cf out/SubsetGenerator.jar -C src Main.class -C src SubsetGenerator.class -C src SerializableSet.class -C src SerializableSubsets.class
	@rm -f src/Main.class src/SubsetGenerator.class src/SerializableSet.class src/SerializableSubsets.class

build: out/SubsetGenerator.jar

run: out/SubsetGenerator.jar
	@cd out && java -cp 'parcs.jar:SubsetGenerator.jar' Main
