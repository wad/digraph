# Directed Weighted Graph Experiment

This is a tool for playing with directed graphs.

## How to build

This was built with Java17 and maven3.

This will build an executable jar file in the target directory.

```shell
mvn clean install
```

## Input file format

A node is identified by a letter between A to Z.
A directed edge is the source and destination node, followed by the weight of that path, like "AB3".
The input data is a comma-delimited list of directed edges, such as the following:

```
AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
```

## How to run in interactive mode

Execute the jar file, providing the path and filename of a file containing the directed graph edges.

```shell
java -jar target/digraph.jar ~/repos/digraph/src/test/resources/given.csv
```
