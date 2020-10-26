# II.3502-lab2-RMI-MatrixOperations

Part 3 : Matrix multiplication

The aim here is to reduce computation time for matrix dot product.

In order to totaly fit my needs I manually reimplemented matrix operations for further optimization.

### Overview

The idea to reduce matrix dot product computation is to split matrix into several sub matrix.

As we can see in the picture bellow:
![ThreadedComputation](https://user-images.githubusercontent.com/48685784/97202007-5f46ea80-17b3-11eb-96ce-b31f4f6776d5.png)

This example is quite simple and here each thread will only compute one cell of the output matrix, but our algorithm will automatically split a matrices and assigne them to the desired amount of threads.

### What I did:

- Implement server/client RMI
- Implement matrix operations
- Ability for the client to send two matrices to the server and get the result
- QoL (Quality of Life) methods such as matrix serialization for display etc

- Split matrices into sub matrices for threaded operations

### What need to be done
- Implement thread operation
- Reconstruct the final matrix based on sub matrices
