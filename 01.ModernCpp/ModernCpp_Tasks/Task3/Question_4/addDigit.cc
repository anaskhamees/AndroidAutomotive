int addDigits(int num) {
    int sum = 0;
    int lastDigit = 0;

    while (num > 0 || sum > 9) {
        if (num == 0) {
            num = sum;
            sum = 0;
        }
        lastDigit = num % 10;
        sum += lastDigit;
        num /= 10;
    }
    return sum;
}

