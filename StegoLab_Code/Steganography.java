import java.awt.Color;
import java.util.ArrayList;

import org.w3c.dom.css.RGBColor;

import jdk.jshell.SourceCodeAnalysis;

import java.awt.Point;
public class Steganography {
    public static void main(String[] args) {
        // Picture KatieFancy = new Picture("StegoLab_Code\\beach.jpg");
        // KatieFancy.explore();
        //testClearLow(KatieFancy).explore();
        // Picture beach2 = new Picture("StegoLab_Code\\beach.jpg");
        // beach2.explore(); 
        // Picture copy2 = testSetLow(KatieFancy, Color.PINK);
        // copy2.explore(); 
        // Picture copy3 = revealPicture(copy2);
        // copy3.explore(); 

        // Picture moon = new Picture("StegoLab_Code\\moon-surface.jpg");
        // Picture msg = new Picture("StegoLab_Code\\msg.jpg");
        // moon.explore();
        // Picture nnnnn = new Picture(hidePicture(moon, msg));
        // nnnnn.explore();
        // revealPicture(msg).explore();

        // Picture beach = new Picture("StegoLab_Code\\beach.jpg"); 
        // Picture robot = new Picture("StegoLab_Code\\robot.jpg"); 
        // Picture flower1 = new Picture("StegoLab_Code\\flower1.jpg");
        // beach.explore(); 
        
        // Picture hidden1 = hidePicture(beach, robot, 65, 208);
        // Picture hidden2 = hidePicture(hidden1, flower1, 280, 110);
        // hidden2.explore(); 
        // Picture unhidden = revealPicture(hidden2);
        // unhidden.explore(); 
        

        // Picture swan = new Picture("StegoLab_Code\\swan.jpg"); 
        // Picture swan2 = new Picture("StegoLab_Code\\swan.jpg");

        // System.out.println("swan and swan2 are the same: " + isSame(swan, swan2));

        // swan = testClearLow(swan);
        // System.out.println("swan and swan2 are the same (after clearLow run on swan): "+ isSame(swan, swan2)); 

        // Picture arch = new Picture("StegoLab_Code\\arch.jpg");
        // Picture arch2 = new Picture("StegoLab_Code\\arch.jpg");
        // Picture koala = new Picture("StegoLab_Code\\koala.jpg");
        // Picture robot1 = new Picture("StegoLab_Code\\robot.jpg");
                
        // ArrayList<Point> pointList = findDifferences(arch, arch2); 
        // System.out.println("PointList after comparing two identical pictures has a size of " + pointList.size());
        
        // pointList = findDifferences(arch, koala); 
        // System.out.println("PointList after comparing two different sized pictures has a size of " + pointList.size());
        
        // Picture arch3 = hidePicture(arch, robot1, 65, 102);
        // pointList = findDifferences (arch, arch3);
        // System.out.println("Pointlist after hiding a picture has a size of " + pointList.size());
        
        // arch.show();
        // arch3.show(); 
        
        Picture hall = new Picture("StegoLab_Code\\femaleLionAndHall.jpg");
        Picture robot2 = new Picture("StegoLab_Code\\robot.jpg");
        Picture flower2 = new Picture("StegoLab_Code\\flower1.jpg"); 
        
        // hide pictures
        Picture hall2 = hidePicture(hall, robot2, 50, 300);
        Picture hall3 = hidePicture(hall2, flower2, 115, 275);
        hall3.explore();
        if(!isSame (hall, hall3))
        {
        Picture hall4 = rectangling(hall, findDifferences(hall, hall3));
        hall4.explore();
        Picture unhiddenHall3 = revealPicture(hall3);
        unhiddenHall3.explore();
        }
        
        
    }
    public static void clearLow(Pixel p){
        p.setRed(p.getRed() / 4 * 4);                               
        p.setGreen(p.getGreen() / 4 * 4);
        p.setBlue(p.getBlue() / 4 * 4);

    }
    public static Picture testClearLow(Picture p){
        Picture temp = new Picture(p);
        for(Pixel pix[]: temp.getPixels2D()){
            for(Pixel pi: pix){
                clearLow(pi);
            }
        }
        return temp;
    }
    public static void setLow (Pixel p, Color c) {
        clearLow(p);
        int red = c.getRed()/64;
        int green = c.getGreen()/64;
        int blue = c.getBlue()/64;
        p.setRed(p.getRed()+red);
        p.setGreen(p.getGreen()+green);
        p.setBlue(p.getBlue()+blue);

    }

    public static Picture testSetLow(Picture p, Color c){
        Picture temp = new Picture(p);
        for(Pixel pix[]: temp.getPixels2D()){
            for(Pixel pi: pix){
                setLow(pi, c);
            }
        }
        return temp;
    }

    public static Picture revealPicture(Picture hidden) { 
        Picture copy = new Picture(hidden); 
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] source = hidden.getPixels2D(); 
        for (int w = 0; w < pixels.length; w++){ 
            for (int c = 0; c < pixels[0].length; c++){ 	
                Color col = source[w][c].getColor();

                int r = col.getRed();
                int g = col.getBlue();
                int b = col.getGreen();

                r %= 4;
                g %= 4;
                b %= 4;
                
                pixels[w][c].setRed(r*64);
                pixels[w][c].setBlue(g*64);
                pixels[w][c].setGreen(b*64);
                    }
                }
        return copy; 
        }

        public static boolean canHide(Picture src, Picture p){
            return src.getPixels2D().length == p.getPixels2D().length && src.getPixels2D()[0].length == p.getPixels2D()[0].length;
        }

        public static Picture hidePicture(Picture source, Picture secret, int startX, int startY){
            Picture newPictureWithReallyLongName = new Picture(source);
                Pixel sources[][] = newPictureWithReallyLongName.getPixels2D();
                Pixel secrets[][] = secret.getPixels2D();
                for(int i = 0; i<secrets.length; i++){
                    for(int j = 0; j<secrets[i].length ; j++){
                        setLow(sources[startY+i][startX+j], secrets[i][j].getColor());
                    }
                }
                System.out.println("same size");
            return newPictureWithReallyLongName;
        }

        public static boolean isSame(Picture shmog, Picture schlog){
            Pixel srcPixels[][] = shmog.getPixels2D();
            Pixel secondPixels[][] = schlog.getPixels2D();
            int count = 0;
            for(int i = 0; i < srcPixels.length; i++){
                for(int j = 0; j < secondPixels[0].length; j++){
                    if(srcPixels[i][j].getRed() == secondPixels[i][j].getRed() && srcPixels[i][j].getBlue() == secondPixels[i][j].getBlue() && srcPixels[i][j].getGreen() == secondPixels[i][j].getGreen()){
                        count++;
                    }
                }
            }
            return count == (srcPixels.length * srcPixels[0].length);
        }

        public static ArrayList<Point> findDifferences(Picture shmog, Picture schlog){
            Pixel srcPixels[][] = shmog.getPixels2D();
            Pixel secondPixels[][] = schlog.getPixels2D();
            ArrayList<Point> temp = new ArrayList<Point>();
            if(!(shmog.getWidth() == schlog.getWidth() && shmog.getHeight() == schlog.getHeight())){
                return temp;
            }
            for(int i = 0; i < secondPixels.length; i++){
                for(int j = 0; j < secondPixels[0].length; j++){
                    if(!(srcPixels[i][j].getRed() == secondPixels[i][j].getRed() && srcPixels[i][j].getBlue() == secondPixels[i][j].getBlue() && srcPixels[i][j].getGreen() == secondPixels[i][j].getGreen())){
                        temp.add(new Point(j,i));
                    }
                }
            }
            return temp;
        }

        public static Picture rectangling(Picture shmog, ArrayList<Point> schlog){
            Picture clone = new Picture(shmog);
            int minX = (int)schlog.get(0).getX();
            int maxX = (int)schlog.get(0).getX();
            int minY = (int)schlog.get(0).getY();
            int maxY = (int)schlog.get(0).getX();
            Point temp;
            for(int i = 0; i<schlog.size(); i++) {
                temp = schlog.get(i);
                if(temp.getX() > maxX) {
                    maxX = (int)temp.getX();
                } else if(temp.getX() < minX) {
                    minX = (int)temp.getX();
                }
                if(temp.getY() > maxY) {
                    maxY = (int)temp.getY();
                } else if(temp.getY() < minY) {
                    maxX = (int)temp.getY();
                }
            }
            for(int i = minX; i < maxX; i++){
                clone.setBasicPixel(i, minY, 16580736);
                clone.setBasicPixel(i, maxY, 16580736);

            }
            for(int i = minY; i < maxY; i++){
                clone.setBasicPixel(minX, i, 16580736);
                clone.setBasicPixel(maxX, i, 16580736);
            }
            return clone;
        }


}
