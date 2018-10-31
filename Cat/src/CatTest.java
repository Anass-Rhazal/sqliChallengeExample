
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CatTest {
	
	 /**
	    * Each time the cat leaps, the leap lenght is longer by one. But the leap lenght maximum is 4.
	    * The path method displays the leaps done by the cat.
	    */
       @Test
       public void test() {
              Cat cat = new Cat();
              assertEquals("*", cat.path());
              cat.leap();
              assertEquals("*.*", cat.path());
              cat.leap();
              assertEquals("*.*..*", cat.path());
              cat.leap();
              assertEquals("*.*..*...*", cat.path());
              cat.leap();
              assertEquals("*.*..*...*....*", cat.path());
              cat.leap();
              assertEquals("*.*..*...*....*....*", cat.path());
       }
	
}
