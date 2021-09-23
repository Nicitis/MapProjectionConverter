import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;

public class DrawImage extends JFrame {
    int frameWidth = 300;
    int frameHeight = 300;
    static Image image;

    DrawImage(){
        // The image URL - change to where your image file is located!
        String imageURL = "resources.Mercator Map.png";

        try {
            image = ImageIO.read(this.getClass().getResource("Mercator Map.png"));
        } catch (IOException e) {
            System.out.println("에러 발생 - IO Exception");
            e.printStackTrace();
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("에러 발생 - Illegal Argument Exception : 이미지 경로가 잘못 설정되었습니다.");
            e.printStackTrace();
            return;
        }

        // Add a component with a custom paint method
        this.add(new CustomPaintComponent());
        Toolkit toolkit = this.getToolkit();
        toolkit.prepareImage(image, -1, -1, this);

        // Display the frame
        this.setTitle("drawImage");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(frameWidth, frameHeight);
        this.setVisible(true);
    }

    static class CustomPaintComponent extends Component {
        public void paint(Graphics g) {
            // Retrieve the graphics context; this object is used to paint shapes
            Graphics2D g2d = (Graphics2D)g;

            /**
             * Draw an Image object
             * The coordinate system of a graphics context is such that the origin is at the
             * northwest corner and x-axis increases toward the right while the y-axis increases
             * toward the bottom.
             */
            int x = 0;
            int y = 0;

            g2d.drawImage(image, x, y, this);
        }
    }
}
