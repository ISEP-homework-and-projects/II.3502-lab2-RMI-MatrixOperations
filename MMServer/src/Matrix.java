import java.util.Vector;

public class Matrix implements java.io.Serializable
{
    private Vector<Vector<Double>> _matrix;

    public Matrix(int colLength, int rowLength)
    {
        _matrix = new Vector<Vector<Double>>();

        for(int row = 0; row < rowLength ; row ++)
        {
            Vector<Double> temp = new Vector<>();

            for(int col = 0 ; col < colLength ; col++ )
            {
                temp.add(0d);
            }
            _matrix.add(temp);
        }
    }

    public Matrix(Vector<Vector<Double>> matrix)
    {
        _matrix = matrix;
    }

    public int nCols()
    {
        return _matrix.get(0).size();
    }

    public int nRows()
    {
        return _matrix.size();
    }

    public Vector<Double> getRow(int rowIdx)
    {
        return _matrix.get(rowIdx);
    }

    public Vector<Double> getCol(int colIdx)
    {
        Vector<Double> res = new Vector<>();

        for(int i = 0; i < nRows(); i++ )
        {
            res.add(_matrix.get(i).get(colIdx));
        }
        return res;
    }

    public Double get(int colIdx, int rowIdx)
    {
        return getCol(colIdx).get(rowIdx);
    }

    public void set(int colIdx, int rowIdx, Double val)
    {
        _matrix.get(rowIdx).set(colIdx,val);
    }

    public String Serialize()
    {
        StringBuilder builder = new StringBuilder();

        for(int row = 0; row < nRows() ; row ++)
        {
            for(int col = 0 ; col < nCols(); col++ )
            {
                builder.append(get(col,row)+" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public Matrix dotProduct(Matrix b)
    {
        // dot product between this and a matrix b

        if(nCols() != b.nRows())
        {
            System.out.println("Can't compute matrix, size differs");
            return null; // If number of cols != number of row, product can't be computed
        }

        Matrix matrix = new Matrix(b.nCols(), nRows());

        for(int col = 0; col < matrix.nCols() ; col++)
        {
            for(int row = 0; row < matrix.nRows() ; row++)
            {
                Double sum = VectorExtended.dot(getRow(row),b.getCol(col));
                matrix.set(col,row,sum);
            }
        }
        return matrix;
    }

    public Vector<Vector<Double>> toVector()
    {
        return _matrix;
    }
}
