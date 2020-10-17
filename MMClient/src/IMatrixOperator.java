import java.util.Vector;

public interface IMatrixOperator
{
    public Vector<Vector<Double>> matrixMultiplication(Vector<Vector<Double>> a, Vector<Vector<Double>> b);

    public String matrixSerialization(Vector<Vector<Double>> m1);
}
