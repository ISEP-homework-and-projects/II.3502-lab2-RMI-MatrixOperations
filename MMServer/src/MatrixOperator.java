import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Vector;

public class MatrixOperator extends UnicastRemoteObject implements java.io.Serializable, IMatrixOperator
{
    public MatrixOperator() throws java.rmi.RemoteException
    {
    }

    @Override
    public Vector<Vector<Double>> matrixMultiplication(Vector<Vector<Double>> a, Vector<Vector<Double>> b) throws java.rmi.RemoteException
    {
        Matrix mA = new Matrix(a);
        Matrix mB = new Matrix(b);

        return mA.dotProduct(mB).toVector();
    }


    @Override
    public String matrixSerialization(Vector<Vector<Double>> matrix) throws java.rmi.RemoteException
    {
        return new Matrix(matrix).Serialize();
    }
}
