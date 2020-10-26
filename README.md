# II.3502-lab2-RMI-MatrixOperations

Part 3 : Matrix multiplication

The aim here is to reduce computation time for matrix dot product.

In order to totaly fit my needs I manually reimplemented matrix operations for further optimization.

### Overview

The idea to reduce matrix dot product computation is to split matrix into several sub matrix

### What I did:

- Implement server/client RMI
- Implement matrix operations
- Ability for the client to send two matrices to the server and get the result
- QoL (Quality of Life) methods such as matrix serialization for display etc

- Split matrices into sub matrices for threaded operations

### What need to be done
- Implement thread operation
- Reconstruct the final matrix based on sub matrices
