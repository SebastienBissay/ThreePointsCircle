public class Matrix {
    private final float[][] values;

    public Matrix()
    {
        values = new float[3][3];
    }

    public void setValue(int i, int j, float value)
    {
        values[i][j] = value;
    }

    float getDeterminant()
    {
        return values[0][0] * values[1][1] * values[2][2]
                + values[0][1] * values[1][2] * values[2][0]
                + values[0][2] * values[1][0] * values[2][1]
                - values[0][2] * values[1][1] * values[2][0]
                - values[0][1] * values[1][0] * values[2][2]
                - values[0][0] * values[1][2] * values[2][1];
    }
}
