package ma.sqli.test;

public class MapBuilder {

	// 
	char [][] mapChar;
	
	public MapBuilder(int size) {
			mapChar=new char [size][size];
			
			// create empty map
	 for(int i=0;i<size;i++)
		  for(int j=0;j<size;j++)
			  mapChar[i][j]=' '; 
	}

	public MapBuilder addRock(int line, int column) {
		
		   mapChar[line][column]='X'; 
		
		
		return this;
	}

	public Map build() {
		
		return new Map(this.mapChar);
	}

	public MapBuilder addStarRock(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	public MapBuilder addEnhancerRock(int line, int column, int enhancer) {
		// TODO Auto-generated method stub
		return null;
	}

}
