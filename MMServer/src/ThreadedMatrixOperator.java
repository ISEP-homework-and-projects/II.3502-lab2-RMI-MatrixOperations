import java.net.ServerSocket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ThreadedMatrixOperator extends UnicastRemoteObject implements java.io.Serializable, IMatrixOperator
{
    private final static int THREADS = 4;

    public ThreadedMatrixOperator() throws java.rmi.RemoteException
    {

    }

    @Override
    public Vector<Vector<Double>> matrixMultiplication(Vector<Vector<Double>> a, Vector<Vector<Double>> b) throws java.rmi.RemoteException
    {
        Matrix mA = new Matrix(a);
        Matrix mB = new Matrix(b);

        System.out.println(mA.serialize());
        System.out.println(mB.serialize());

        System.out.println("Starting "+THREADS+" threads to solve matrix multiplication");

        //Matrix result = new Matrix(mB.nCols(), mA.nRows());

        int nCols = mB.nCols() / (THREADS / 2); // number of cols
        int nRows = mA.nRows() / (THREADS /2 ); // number of rows

        int colSize = mB.nCols() / nCols;
        int rowSize = mA.nRows() / nRows;

        System.out.println("Computing "+nCols+" cols and "+nRows+" rows per threads");

        List<ThreadedMatrixMultiplication> threads = new ArrayList<>();

        // Start threads for computation
        for (int row = 0; row < nRows; row++)
        {
            for (int col = 0; col < nCols; col++)
            {
                System.out.println("Taking from "+row+" to "+(row+rowSize-1));
                Matrix sliceA = mA.getSliceByRows(row,row+rowSize-2);
                Matrix sliceB = mB.getSliceByCols(col,col+colSize-2).transpose();

                System.out.println(sliceA.serialize());
                System.out.println(sliceB.serialize());

                ThreadedMatrixMultiplication operator = new ThreadedMatrixMultiplication(sliceA,sliceB);
                Thread thread = new Thread(operator);
                threads.add(operator);
                thread.start();
            }
        }
        int cursor = 0;
        List<List<Matrix>> childs = new ArrayList<>();

        System.out.println("Gathering all results");

        for (int row = 0; row < nRows; row++)
        {
            List<Matrix> temp = new ArrayList<>();

            for (int col = 0; col < nCols; col++)
            {
                temp.add(threads.get(cursor).getResult());
                cursor +=1;
            }
            childs.add(temp);
        }

        Matrix result = new Matrix(childs,mB.nCols(), mA.nRows());

        System.out.println(result.serialize());

        return mA.dotProduct(mB).toVector();
    }


    @Override
    public String matrixSerialization(Vector<Vector<Double>> matrix) throws java.rmi.RemoteException
    {
        return new Matrix(matrix).serialize();
    }
}
