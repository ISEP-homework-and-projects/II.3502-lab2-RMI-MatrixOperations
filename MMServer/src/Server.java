import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

public class Server
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            java.rmi.registry.LocateRegistry.createRegistry(12345);

            MatrixOperator operator = new MatrixOperator();

            Naming.rebind("rmi://localhost:12345/MatrixMultiplicationServer",operator);

            System.out.println("MatrixMultiplicationServer bound in registry");
        }
        catch (Exception e)
        {
            System.out.println("Error while creating the server "+e.getMessage());
        }


    }
}
