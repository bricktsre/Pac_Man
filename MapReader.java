import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MapReader {
	File f;
	
	public MapReader(String mapname) {
		f= new File(mapname);
	}
	
	/*reads in the first two integers in the map file
	 * the first is the width of the map
	 * the second is the height of the image
	 */
	public int[] getHeightWidth() {
		int [] widthheight = new int[2];
		try {
			Scanner read = new Scanner(f);
			if(read.hasNextInt())
				widthheight[0]= read.nextInt();
			if(read.hasNextInt())
				widthheight[1]= read.nextInt();	
			read.close();
		}catch(IOException e1) {
			System.out.println("Problem reading file");
		}
		return widthheight;
	}
	
	/*
	 * 
	 */
	public int[] getStartCoordinates() {
		int[]a = new int[2];
		int i=0;
		try{
			Scanner read = new Scanner(f);
			while(i<2) {
				read.nextInt();
				i++;
			}
			a[0]=read.nextInt();
			a[1]=read.nextInt();
			read.close();
		}catch(IOException e1){
			System.out.println("Problem reading file");
		}
		return a;
	}
	
	/*
	 * Returns a 2D array of the map where 1 is a wall and 0 is a point space
	 */
	public int[][] getMap(int w, int h){
		int [][] wh = new int[h][w];
		try {
			Scanner read = new Scanner(f);
			for(int i=0;i<4;i++)
				read.nextInt();
			for(int i = 0;i<h;i++) {
				for(int j =0;j<w;j++) {
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
