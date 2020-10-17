import java.util.Vector;

public class VectorExtended
{

    public static Double dot(Vector<Double> a, Vector<Double> b)
    {
        if(a.size() != b.size()) return 0d;

        Double res = 0d;

        for(int i = 0; i < a.size(); i++)
        {
            res += a.get(i) * b.get(i);
        }

        return res;
    }
}
