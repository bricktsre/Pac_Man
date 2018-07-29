import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MapReader {
	File f;
	
	public MapReader(String mapname) {
		f= new File(mapname);
	}
	
	/*
	 * Returns a 2D array of the map where 1 is a wall and 0 is a point space
	 */
	public int[][] getMap(){
		int [][] wh = new int[33][28];
		try {
			Scanner read = new Scanner(f);
			for(int i = 0;i<33;i++) {
				for(int j =0;j<28;j++) {
					if(read.hasNextInt())
						wh[i][j]=read.nextInt();
				}
			}
			read.close();
		}catch(IOException e1) {
			System.out.println("Problem reading file");;
		}
		return wh;
	}
}
