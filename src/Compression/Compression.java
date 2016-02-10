package Compression;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.jtransforms.dct.DoubleDCT_2D;

import Main.Main;

public class Compression {
	private BufferedImage img;
	private Main main;
	LabClass labClass;
	int quality;
	
	int sizeOfChunk = 8;
    int rowsChunks = 0;
    int colsChunks = 0;
    int chunksSize;
	
	double[][][] chunksL, chunksA, chunksB;
	int[][][] chunksIntL, chunksIntA, chunksIntB;
	
	int[][] zigzag = makeZigZagArr(8);
	
	int[][] chunksZigL, chunksZigA,chunksZigB;
	
	public Compression(Main main) {
		this.main = main;
		img = main.getImg();
		labClass = new LabClass(img);
		quality = 31;
		engine();
	}
//	public static void main(String[]args) {
//		Compression com = new Compression(new Main());
//		double[][] d = {
//				{0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,24,5,6,7,8,9},
//				{0,1,2,4,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9},
//				{0,1,2,5,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9},
//				{0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9},
//				{0,1,5,9,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9},
//				{0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,25,5,6,7,8,9},
//				{0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9},
//				{0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9},
//				{0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,29},
//			};
////		double [][] temp = com.get8x8block(d,5, 24);
////		com.print(temp);
////		int[][] temp2 = com.zigZag(8);
//		com.print(temp2);
//	}
	private void print(double[][] temp) {
		for(int y = 0;y < temp.length; y++){
	    	 for(int x = 0;x < temp[y].length; x++){
	    		 System.out.print(temp[y][x] + ", ");
	    	 }
	    	 System.out.println();
		}
		System.out.println();
	}
	private void print(int[][] temp) {
		for(int y = 0;y < temp.length; y++){
	    	 for(int x = 0;x < temp[y].length; x++){
	    		 System.out.print(temp[y][x] + ", ");
	    	 }
	    	 System.out.println();
		}
		System.out.println();
	}
	void engine(){
		separateTo8x8blocks();
		calculateDCT();
		quantization();
		calculateZigZagForChunk();
		try {
			compression();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		BufferedImage imgTemp = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB );
	   
	}
	
	synchronized void separateTo8x8blocks(){
		int height = img.getHeight();
	    int width = img.getWidth();
    
	    if(height%sizeOfChunk > 0)
	    	rowsChunks = height/sizeOfChunk + 1;
	    else rowsChunks = height/sizeOfChunk;
	    
	    if(width % sizeOfChunk > 0)
	    	colsChunks = width/sizeOfChunk + 1;
	    else colsChunks = width/sizeOfChunk;
	    
	    chunksSize = rowsChunks * colsChunks;  
	    
	    
	    chunksL = new double[chunksSize][8][8];
	    chunksA = new double[chunksSize][8][8];
	    chunksB = new double[chunksSize][8][8];
	    int count = 0;
	    for(int y = 0;y < rowsChunks; y++){
	    	 for(int x = 0;x < colsChunks; x++){
	    		 chunksL[count] = get8x8block(labClass.getLabL(),y*sizeOfChunk,x*sizeOfChunk);  
	    		 chunksA[count] = get8x8block(labClass.getLabA(),y*sizeOfChunk,x*sizeOfChunk);
	    		 chunksB[count] = get8x8block(labClass.getLabB(),y*sizeOfChunk,x*sizeOfChunk);
	    		 count++;
	    	 }
	    }
	}
	
	double[][] get8x8block(double[][] arr,int y, int x){
		double[][] chunk8x8 = new double[8][8]; 
		for(int i = y,t = 0;i < y+8; i++,t++){
			for(int j = x,k = 0;j < x+8; j++,k++){
				if(i < arr.length && j < arr[0].length){
					chunk8x8[t][k] = arr[i][j];
				}
				else chunk8x8[t][k] = 0;
			}
		}
		return chunk8x8;
	}
	
	synchronized void calculateDCT(){
		double[][] test = new double[8][8];
		for(int y = 0;y < 8; y++){
	    	 for(int x = 0;x < 8; x++){
	    		 test[y][x] = chunksL[1][y][x];
	    	 }
		}	
		
		DoubleDCT_2D dct = new DoubleDCT_2D(8, 8);
		for(int i = 0;i < chunksL.length; i++){    	
	    		 dct.forward(chunksL[i], true);
	    		 dct.forward(chunksA[i], false);
	    		 dct.forward(chunksB[i], false);
		}
	}
	
	void quantization(){
		double[][] QL = {
				{16, 11, 10, 16, 24, 40, 51, 61},
				{12, 12, 14, 19, 26, 58, 60, 55},
				{14, 13, 16, 24, 40, 57, 69, 56},
				{14, 17, 22, 29 ,51 ,87, 80, 62},
				{18, 22, 37, 56, 68, 109, 103, 77},
				{24, 35, 55, 64, 81, 104, 113, 92},
				{49, 64, 78, 87, 103, 121, 120, 101},
				{72, 92, 95, 98, 112, 100, 103, 99}
		};
		double[][] QC = {
				{17, 18, 24, 47, 99, 99, 99, 99},
				{18, 21, 26, 66, 99, 99, 99, 99},
				{24, 26, 56, 99, 99, 99, 99, 99},
				{47, 66, 99, 99, 99, 99, 99, 99},
				{99, 99, 99, 99, 99, 99, 99, 99},
				{99, 99, 99, 99, 99, 99, 99, 99},
				{99, 99, 99, 99, 99, 99, 99, 99},
				{99, 99, 99, 99, 99, 99, 99, 99}
		};
		
		for(int y = 0;y < QL.length; y++){
			for(int x = 0;x < QL[y].length; x++){
				if(quality > 50){
					QL[y][x] = (QL[y][x]*(100 - quality))/50;
					QC[y][x] = (QC[y][x]*(100 - quality))/50;
					}
				else{
					QL[y][x] = (QL[y][x]*50)/quality;
					QC[y][x] = (QC[y][x]*50)/quality;
				}
					
			}
		}
		chunksIntL	= new int[chunksSize][8][8];
		chunksIntA	= new int[chunksSize][8][8];
		chunksIntB	= new int[chunksSize][8][8];
		for(int i = 0;i < chunksL.length; i++){
			for(int y = 0;y < sizeOfChunk; y++){
				for(int x = 0;x < sizeOfChunk; x++){
					chunksIntL[i][y][x] = (int) (chunksL[i][y][x]/QL[y][x]);  
					chunksIntA[i][y][x] = (int) (chunksL[i][y][x]/QC[y][x]);
					chunksIntB[i][y][x] = (int) (chunksL[i][y][x]/QC[y][x]);
				}
			}
		}
	}
	
	int[][] makeZigZagArr(int n){
	    int[][] result = new int[n][n];
	    int i = 0, j = 0;
	    int d = -1; // -1 for top-right move, +1 for bottom-left move
	    int start = 0, end = n * n - 1;
	    do{
	        result[i][j] = start++;
	        result[n - i - 1][n - j - 1] = end--;
	 
	        i += d; j -= d;
	        if (i < 0)
	        {
	            i++; d = -d; // top reached, reverse
	        }
	        else if (j < 0)
	        {
	            j++; d = -d; // left reached, reverse
	        }
	    } while (start < end);
	    if (start == end)
	        result[i][j] = start;
	    return result;
	}
	
	int[] calculateZigZagForChunk(){
		int size = chunksIntA.length;
		chunksZigL = new int[size][8*8];
		chunksZigA = new int[size][8*8];
		chunksZigB = new int[size][8*8];
		for(int k = 0;k < chunksZigL.length;k++){
			for(int i = 0;i < zigzag.length; i++){
				for(int y = 0;y < zigzag[0].length; y++){
					chunksZigL[k][zigzag[i][y]] = chunksIntL[k][i][y];
				}
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("resource")
	void compression() throws IOException{
		
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter("filename.txt"));
		for(int k = 0;k < chunksZigL.length;k++){
			for(int i = 0;i < chunksZigL[0].length; i++){
				// Maybe:
				outputWriter.write(chunksZigL[k][i]+" ");
			    // Or:
//				outputWriter.write(Integer.toString(chunksZigL[i]);
				
			}
			outputWriter.newLine();
		}
		outputWriter.flush();  
		outputWriter.close(); 
		System.out.println("end");
		try {
			int[][] arr = create2DIntMatrixFromFile("filename.txt");
			print(arr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		BufferedReader inputReader = null;
//		inputReader = new BufferedReader(new FileReader("filename.txt"));
//		for(int k = 0;k < inputReader.;k++){
//			for(int i = 0;i < chunksZigL[0].length; i++){
//				// Maybe:
//				outputWriter.write(chunksZigL[i]+" ");
//			    // Or:
////				outputWriter.write(Integer.toString(x[i]);
//				
//			}
//			outputWriter.newLine();
//		}
	}
	
	 public int[][] create2DIntMatrixFromFile(String filename) throws Exception {
	        int[] matrix = null;

	        // If included in an Eclipse project.
//	        InputStream stream = ClassLoader.getSystemResourceAsStream("filename.txt");
//	        System.out.println(stream);
//	        BufferedReader buffer = new BufferedReader(new FileReader("filename.txt"));

	        // If in the same directory - Probably in your case...
	        // Just comment out the 2 lines above this and uncomment the line
	        // that follows.
	        BufferedReader buffer = new BufferedReader(new FileReader(filename));
	        ArrayList<int[]> arr = new ArrayList<int[]>();
	        int size = 0;
	        Stream<String> str = buffer.lines();
	        Object[] arr2 = str.toArray();
	        for(int i = 0; i < arr2.length;i++){
	        	String[] vals = ((String) arr2[i]).trim().split("\\s+");
	        	size = vals.length;
                matrix = new int[size];
                for (int col = 0; col < size; col++) {
	                matrix[col] = Integer.parseInt(vals[col]);
	            }
	            arr.add(matrix);
//	        	System.out.println(i+ " " +  Arrays.toString(matrix));
			}
	        int [][] finalArr = new int[arr.size()][matrix.length];
	        for(int i = 0; i < finalArr.length;i++){
	        	finalArr[i] = arr.get(i);
	        }
//	        System.out.println(str.count());
//	        while ((line = buffer.readLine()) != null) {
//	            String[] vals = line.trim().split("\\s+");
//
//	            // Lazy instantiation.
//	            if (matrix == null) {
//	                size = vals.length;
//	                matrix = new int[size];
//	                log10 = (int) Math.floor(Math.log10(size * size)) + 1;
//	                numberFormat = String.format("%%%dd", log10);
//	            }
//
//	            for (int col = 0; col < size; col++) {
//	            	if(row<64)
//	                matrix[col] = Integer.parseInt(vals[col]);
//	            }
//	            arr.add(matrix);
////	            row++;
//	        }

	        return finalArr;
	    }
}
