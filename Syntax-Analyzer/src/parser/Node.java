package parser;

import java.io.FileNotFoundException;


public interface Node {
     public int parse(int ind) throws FileNotFoundException;
     public void printNode() throws FileNotFoundException;
}
