import java.rmi.Naming;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.Vector;

public class Client
{
    private static Random _randomInstance;

    public static void main(String args[])
    {
        try
        {
            _randomInstance = new Random();
            IMatrixOperator matrixOperator = (IMatrixOperator) Naming.lookup("rmi://localhost:12345/MatrixMultiplicationServer");
            Vector<Vector<Double>> a = generateMatrix(10,10);
            Vector<Vector<Double>> b = generateMatrix(10,10);

            System.out.println("Matrix a:");
            //System.out.println(matrixOperator.matrixSerialization(a));

            System.out.println("Matrix b:");
            //System.out.println(matrixOperator.matrixSerialization(b));

            long start = System.currentTimeMillis();

            Vector<Vector<Double>> res = matrixOperator.matrixMultiplication(a,b);

            if(res != null)
            {
                //System.out.println(matrixOperator.matrixSerialization(res));

                long end = System.currentTimeMillis();
                NumberFormat formatter = new DecimalFormat("#0.00000");
                System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
            }
            else
            {
                System.out.println("Computation failed, check your matrixes");
            }

        }
        catch (Exception e)
        {
            System.out.println("An error occured while trying to connect: "+e.getMessage() );
        }
    }

    private static Vector<Vector<Double>> generateMatrix(int colLength, int rowLength)
    {
        Vector<Vector<Double>> matrix = new Vector<Vector<Double>>();

        for(int row = 0; row < rowLength ; row ++)
        {
            Vector<Double> temp = new Vector<>();

            for(int col = 0 ; col < colLength ; col++ )
            {
                temp.add((double)_randomInstance.nextInt(5));
            }
            matrix.add(temp);
        }

        return matrix;
    }
}
