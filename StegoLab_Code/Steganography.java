import java.awt.Color;
public class Steganography {
    public static void main(String[] args) {
        Picture KatieFancy = new Picture("StegoLab_Code\\KatieFancy.jpg");
        KatieFancy.explore();
        testClearLow(KatieFancy).explore();
    }
    public static void clearLow(Pixel p){
        p.setRed(p.getRed() / 4 * 4);;;;;;;;;;;;;;;;;;;;;;;;;
        p.setGreen(p.getGreen() / 4 * 4);;;;;;;;;;;;;;;;;;;;;;;;;
        p.setBlue(p.getBlue() / 4 * 4);;;;;;;;;;;;;;;;;;;;;;;;;

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
}
