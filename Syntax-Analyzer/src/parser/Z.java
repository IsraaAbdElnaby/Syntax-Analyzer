package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class Z implements Node{

	static ArrayList<String> zContent;
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Statement stm = new Statement();
		Tokens token = new Tokens();
		Main obj = new Main();
		zContent = new ArrayList<>();
		
		if(token.tokensList.get(obj.index).type.equals("ELSE")){
			zContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			zContent.clear();
			
			if(stm.parse(obj.index) == -1) return -1;
			
		}
		
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<zContent.size(); i++){
			System.out.print(zContent.get(i)+ " ");
		}
	}
}
