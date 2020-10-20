import java.util.List;
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

    public Matrix(List<List<Matrix>> childs, int colLength, int rowLength)
    {
        this(colLength, rowLength);

        int colsPadding = childs.get(0).get(0).nCols();
        int rowsPadding = childs.get(0).get(0).nRows();

        int colCursor = 0;
        int rowCursor = 0;

        for(List<Matrix> rows : childs)
        {
            colCursor = 0;
            for(Matrix m : rows)
            {
                setPart(m,rowCursor,colCursor);
                colCursor += colsPadding;
            }
            rowCursor += rowsPadding;
        }
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

    public String serialize()
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

    public void setPart(Matrix matrix, int colIdx, int rowIdx)
    {
        // Replace a matrix part by another matrix
        // Used to recreate a matrix from sub matrix

        for(int row = rowIdx; row < rowIdx+matrix.nRows() ; row ++)
        {
            for(int col = colIdx ; col < colIdx + matrix.nCols(); col++ )
            {
                set(row,col,matrix.get(row - rowIdx,col - colIdx));
            }
        }
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

    public Matrix getSliceByRows(int startRow, int endRow)
    {
        Vector<Vector<Double>> res = new Vector<>();

        for (int r=startRow ; r <= endRow; r++)
        {
            res.add(getRow(r));
        }
        return new Matrix(res);
    }

    public Matrix getSliceByCols(int startCol, int endCol)
    {
        Vector<Vector<Double>> res = new Vector<>();

        for (int c=startCol ; c <= endCol; c++)
        {
            res.add(getCol(c));
        }
        return new Matrix(res);
    }

    public Matrix transpose()
    {
        // We don't modify the actual matrix we return m.T without affecting the original one
        Matrix result = new Matrix(nRows(),nCols());

        for(int row = 0; row < result.nRows() ; row ++)
        {
            for(int col = 0 ; col < result.nCols() ; col++ )
            {
                result.set(col,row,get(row,col));
            }
        }

        return  result;
    }

    public Vector<Vector<Double>> toVector()
    {
        return _matrix;
    }
}
