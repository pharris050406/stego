public class PixelPlayground {

    public static Picture zeroBlue(Picture p ){
        Picture newPic = new Picture(p);
        Pixel pixels[][] = newPic.getPixels2D();
        for(Pixel row[] : pixels){
            for(Pixel pix : row){
                pix.setBlue(0);
            }
        }
        return newPic;
    }
    public static Picture blueify(Picture p ){
        Picture newPic = new Picture(p);
        Pixel pixels[][] = newPic.getPixels2D();
        for(Pixel row[] : pixels){
            for(Pixel pix : row){
                pix.setRed(0);
                pix.setGreen(0);
            }
        }
        return newPic;
    }

    public static Picture negate(Picture p ){
        Picture newPic = new Picture(p);
        Pixel pixels[][] = newPic.getPixels2D();
        for(Pixel row[] : pixels){
            for(Pixel pix : row){
                pix.setRed(255 - pix.getRed());
                pix.setGreen(255 - pix.getGreen());
                pix.setBlue(255 - pix.getBlue());                
            }
        }
        return newPic;
    }


    public static Picture grayscale(Picture p ){
        Picture newPic = new Picture(p);
        Pixel pixels[][] = newPic.getPixels2D();
        for(Pixel row[] : pixels){
            for(Pixel pix : row){
                int avg = (pix.getRed() + pix.getGreen() + pix.getBlue())/3;
                pix.setRed(avg);
                pix.setGreen(avg);
                pix.setBlue(avg);                
            }
        }
        return newPic;
    }

    public static Picture fixUnderwater(Picture p ){
        Picture newPic = new Picture(p);
        Pixel pixels[][] = newPic.getPixels2D();
        for(int i = 0; i < pixels.length; i++){
            for(int j = 0; j < pixels[0].length; j++){
                if(pixels[i][j].getRed() < 25 && pixels[i][j].getGreen() < 170 && pixels[i][j].getGreen() > 155){
                    pixels[i][j].setRed(pixels[i][j].getRed() + 100);
                }
            }
        }
        return newPic;
    }
    public static void main(String[] args) {
    //   Picture beachpic = new Picture("Beach.jpg");
    //   beachpic.explore();

    //   grayscale(beachpic).explore();
    
    Picture water = new Picture("water.jpg");
    water.explore();
    fixUnderwater(water).explore();
    }
}
