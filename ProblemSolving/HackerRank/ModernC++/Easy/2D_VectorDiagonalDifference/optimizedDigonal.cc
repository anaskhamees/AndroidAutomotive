int diagonalDifference(vector<vector<int>> arr) {
    int leftDiag = 0;
    int rightDiag = 0;
    int rows = arr.size();

    for (int i = 0; i < rows; i++) {
        leftDiag += arr[i][i];            // Primary diagonal
        rightDiag += arr[i][rows - i - 1];   // Secondary diagonal
    }

    return abs(leftDiag - rightDiag);
}

