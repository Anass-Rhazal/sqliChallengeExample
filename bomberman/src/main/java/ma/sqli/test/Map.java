package ma.sqli.test;

import java.util.LinkedList;
import java.util.List;

public class Map {

	char [][] mapChar;
	
	// give us the position of the all bombs in the map
	List<Position> allBomb;
	
	
	public Map(char [][]mapChar){
		this.mapChar=mapChar;
		allBomb=new LinkedList<>();
	}
	
	public Map(){}
	
	public String draw() {
		
		
		String drawMap="";
		for(int i=0;i<mapChar.length;i++){
			   for(int j=0;j<mapChar.length;j++)
				    drawMap+=""+mapChar[i][j];
			if(i!=(mapChar.length-1))   drawMap+="\n";
		            }
		
		return drawMap;
	}

	public void trigger() {
		for(Position p:this.allBomb){
		
		
			
			this.mapChar[p.xPosition][p.yPosition]=' ';
		
	if(p.xPosition+1<this.mapChar.length) {		
		if(	this.mapChar[p.xPosition+1][p.yPosition]=='h') 	this.mapChar[p.xPosition+1][p.yPosition]='D';
		 if(	this.mapChar[p.xPosition+1][p.yPosition]=='X') 	this.mapChar[p.xPosition+1][p.yPosition]=' ';
	}	
	 if(p.xPosition-1>=0) {		
		if(	this.mapChar[p.xPosition-1][p.yPosition]=='h') 	this.mapChar[p.xPosition-1][p.yPosition]='D';
		if(	this.mapChar[p.xPosition-1][p.yPosition]=='X') 	this.mapChar[p.xPosition-1][p.yPosition]=' ';
	}
   if(p.yPosition+1<this.mapChar.length){		
		if(	this.mapChar[p.xPosition][p.yPosition+1]=='h') 	this.mapChar[p.xPosition][p.yPosition+1]='D';
		 if(	this.mapChar[p.xPosition][p.yPosition+1]=='X') 	this.mapChar[p.xPosition][p.yPosition+1]=' ';
   }
    if(p.yPosition-1>=0){		
		if(	this.mapChar[p.xPosition][p.yPosition-1]=='h') 	this.mapChar[p.xPosition][p.yPosition-1]='D';
	 	if(	this.mapChar[p.xPosition][p.yPosition-1]=='X') 	this.mapChar[p.xPosition][p.yPosition-1]=' ';
		
    }			
		
			
			
		}
		
		
		
			this.allBomb.clear();
		
	}

}
