package utils;

/**
 * Created by nikita.kuzin on 10/10/16.
 */
public class Color4 {

    private final float RGB_TO_GLRGB = 0.3921f;
    private int m_r;
    private int m_g;
    private int m_b;
    private float m_a;

    public Color4(int r, int g, int b, float a) {
        m_r = (int) normalize(0, 255, r);
        m_g = (int) normalize(0, 255, g);
        m_b = (int) normalize(0, 255, b);
        m_a = normalize(0, 1, a);
    }

    private float normalize(int min, int max, float color) {
        if (color > max) {
            return max;
        } else if (color < min) {
            return min;
        }
        return color;
    }

    public float getGLRed() {
        return m_r / 255.f;
    }

    public float getGLRGreen() {
        return m_g / 255.f;
    }

    public float getGLBlue() {
        return m_b / 255.f;
    }

    public float getGLAlpha() {
        return m_a;
    }
}
