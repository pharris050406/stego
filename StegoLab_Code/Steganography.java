import java.awt.Color;
public class Steganography {
    public static void main(String[] args) {
        Picture KatieFancy = new Picture("StegoLab_Code\\beach.jpg");
        KatieFancy.explore();
        //testClearLow(KatieFancy).explore();
        // Picture beach2 = new Picture("StegoLab_Code\\beach.jpg");
        // beach2.explore(); 
        Picture copy2 = testSetLow(KatieFancy, Color.PINK);
        copy2.explore(); 
        Picture copy3 = revealPicture(copy2);
        copy3.explore(); 
        
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


}
