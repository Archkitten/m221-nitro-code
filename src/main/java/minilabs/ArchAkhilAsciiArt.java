package minilabs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ArchAkhilAsciiArt {
    private String[] chars = {" ", ".", ":", "-", "=", "+", "*", "#", "%", "@"};
    public float brightness = 0.0f;

    public static void main(String[] args) {
        ArchAkhilAsciiArt stupid = new ArchAkhilAsciiArt();
        try {
            stupid.ArchAkhilAsciiArt();
        } catch(IOException e) {
            System.out.println(e);
        }

    }

    public void ArchAkhilAsciiArt() throws IOException {
        BufferedImage image = ImageIO.read(new File("put an image here"));
        int width = image.getWidth();
        int height = image.getHeight();
        String output = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                brightness = toGrayScale(image.getRGB(x, y))/255.0f;
                output += chars[(int) Math.floor(brightness * (chars.length-1))];
        }
            output += "\n";
    }
        System.out.println(output);
}

    public int toGrayScale(int RGB) {
        float red = ((RGB >> 16) & 0xFF) * 0.2126f;
        float green = ((RGB >> 8) & 0xFF) * 0.7152f;
        float blue = ((RGB) & 0xFF) * 0.0722f;
        int linear = Math.round(red + green + blue);
        return linear;
    }
}
