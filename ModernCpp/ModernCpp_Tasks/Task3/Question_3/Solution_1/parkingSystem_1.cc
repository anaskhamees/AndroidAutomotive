class ParkingSystem {
    int Big;
    int Medium;
    int Small;
public:
    ParkingSystem(int big, int medium, int small) : Big(big), Medium(medium), Small(small) {}

    bool addCar(int carType) {
        switch(carType) {
            case 1:
                if (Big > 0) {
                    Big--;
                    return true;
                }
                return false;
            case 2:
                if (Medium > 0) {
                    Medium--;
                    return true;
                }
                return false;
            case 3:
                if (Small > 0) {
                    Small--;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
};

