package ma.sqli.test;

public class Bomberman {

	// Bomberman's name
	String name;
	//  Bomberman's current  position
	Position currentPosition;
//  Bomberman's direction it can be top ,down ,forward or back
	String direction; 
//  Bomberman's map
	Map bombermanMap;
// number of Step of bomberman	
	int numStep;
	
	
	public Bomberman(String name) {
		
		this.name=name;
		this.currentPosition=new Position(0,0);
		this.direction="forward";
		this.numStep=1;
	}

	public void startIn(Map map) {
		
		this.bombermanMap=map;
		
		bombermanMap.mapChar[currentPosition.xPosition][currentPosition.yPosition]='h';
		
	}

	public Bomberman forward() {
		
	if(bombermanMap.mapChar[currentPosition.xPosition][currentPosition.yPosition]=='h')	
		bombermanMap.mapChar[currentPosition.xPosition][currentPosition.yPosition]=' ';
		
		
		
		if(this.direction.equals("forward") ) currentPosition.yPosition=currentPosition.yPosition+1;
		else if(this.direction.equals("down") )  currentPosition.xPosition=currentPosition.xPosition+1;
		else if(this.direction.equals("back") )  currentPosition.yPosition=currentPosition.yPosition-1;
		else if(this.direction.equals("top") )  currentPosition.xPosition=currentPosition.xPosition-1;
		
		
		bombermanMap.mapChar[currentPosition.xPosition][currentPosition.yPosition]='h';
		
		return this;
	}

	public Bomberman right() {
		
		if(this.direction.equals("forward") )	this.direction="down";
		else if(this.direction.equals("down") ) this.direction="back";
		else if(this.direction.equals("back") ) this.direction="top";
		else if(this.direction.equals("top") ) this.direction="forward";
		
		return this;
	}

	public Bomberman bomb() {
		
		Bomb bomb=new Bomb(new Position(this.currentPosition.xPosition,this.currentPosition.yPosition));
		
	
	
		
		bombermanMap.allBomb.add(bomb.bombPosition);
		
		bombermanMap.mapChar[bomb.bombPosition.xPosition][bomb.bombPosition.yPosition]='o';
		
		
		
		
		
		return this;
	}

	public Bomberman left() {
		if(this.direction.equals("forward") )	this.direction="top";
		else if(this.direction.equals("down") ) this.direction="forward";
		else if(this.direction.equals("back") ) this.direction="down";
		else if(this.direction.equals("top") ) this.direction="back";
		return this;
	}

}
