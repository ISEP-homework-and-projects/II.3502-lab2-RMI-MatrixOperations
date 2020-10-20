public class ThreadedMatrixMultiplication implements Runnable
{
    private Matrix _result;

    private Matrix _a;
    private Matrix _b;

    public ThreadedMatrixMultiplication(Matrix a, Matrix b)
    {
        _a = a;
        _b = b;
    }

    @Override
    public void run()
    {
        System.out.println("Starting a thread");
        try
        {
            _result = _a.dotProduct(_b);
        }
        catch (Exception e)
        {
            System.out.println("Error while trying to compute");
        }
    }

    public Matrix getResult()
    {
        return _result;
    }
}
