import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class BasicImageOperations {
	
	public int clamp(int v, int min, int max){
	    if(v>max) return max;
	    if(v<min) return min;
	    return v;
	}
	
	public BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		}
	
	public BufferedImage createImageWithMirroredSides(BufferedImage oldImage, int radius) {
		int nHeight = oldImage.getHeight();
	    int nWidth = oldImage.getWidth();
		BufferedImage imgWithMirroredSides = new BufferedImage(
				nWidth + radius*2, nHeight + radius*2,
	    		BufferedImage.TYPE_INT_RGB );
		//create mirrored image on sides
	    //center
		for(int y = radius, oldY = 0;y < nHeight + radius; y++,oldY++){
	        for(int x = radius, oldX = 0;x < nWidth + radius; ++x,++oldX){
	        	imgWithMirroredSides.setRGB(x, y, oldImage.getRGB(oldX, oldY));
	        }
		}
		//left side
		for(int y = radius;y < nHeight + radius; y++){
	        for(int x = 0, oldX = radius-1;x < radius; ++x, oldX--){
	        	imgWithMirroredSides.setRGB(x, y, oldImage.getRGB(oldX, y - radius));
	        }
		}
		//right side
		for(int y = radius;y < nHeight + radius; y++){
	        for(int x = radius + nWidth, oldX = nWidth-1;x < radius*2 + nWidth; ++x, oldX--){
	        	imgWithMirroredSides.setRGB(x, y, oldImage.getRGB(oldX, y - radius));
	        }
		}
		//up side
		for(int y = 0, oldY = radius*2;y < radius; y++, oldY--){
	        for(int x = 0;x < nWidth + radius*2; ++x){
	        	imgWithMirroredSides.setRGB(x, y, imgWithMirroredSides.getRGB(x, oldY));
	        }
		}
		//bottom side
		for(int y = nHeight + radius, oldY = nHeight +radius -1;y < nHeight + radius*2; y++, oldY--){
	        for(int x = 0;x < nWidth + radius*2; ++x){
	        	imgWithMirroredSides.setRGB(x, y, imgWithMirroredSides.getRGB(x, oldY));
	        }
		}					   		
		return imgWithMirroredSides;
	}
}
