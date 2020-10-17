import java.util.Vector;

public interface IMatrixOperator extends java.rmi.Remote
{
    public Vector<Vector<Double>> matrixMultiplication(Vector<Vector<Double>> m1, Vector<Vector<Double>> m2) throws java.rmi.RemoteException;

    public String matrixSerialization(Vector<Vector<Double>> m1) throws java.rmi.RemoteException;
}
