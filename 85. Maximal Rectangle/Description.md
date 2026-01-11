# 85. Maximal Rectangle

_Hard_

Topics: `Array` `Dynamic Programming` `Stack` `Matrix` `Monotonic Stack`

Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 

## Example 1:

<img width="797" height="633" alt="image" src="https://github.com/user-attachments/assets/28fbaeee-5daa-467f-b98c-f69ebaa48fbf" />

**Input:** matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]Output: 6Explanation: The maximal rectangle is shown in the above picture.

## Example 2:

**Input:** matrix = [["0"]]Output: 0

## Example 3:

**Input:** matrix = [["1"]]Output: 1

 

## Constraints:

- rows == matrix.length

- cols == matrix[i].length

- 1 <= rows, cols <= 200

matrix[i][j] is '0' or '1'.

