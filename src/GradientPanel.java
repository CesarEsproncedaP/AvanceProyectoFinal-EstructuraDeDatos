import javax.swing.*;
import java.awt.*;

class GradientPanel extends JPanel {
    private Color color1;
    private Color color2;

    // En este constructor inicializa los dos colores para el dardient.
    public GradientPanel(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    // Se sobre escribe el paintComponent para dibujar un gardiant vertical desde color1 a color2 en el panel.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2); 
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}