


typedef struct {
    int arr[3];
} ParkingSystem;


ParkingSystem* parkingSystemCreate(int big, int medium, int small) {
    ParkingSystem* obj=(ParkingSystem*)malloc(sizeof(ParkingSystem));
    obj->arr[0]=big;
    obj->arr[1]=medium;
    obj->arr[2]=small;
    return obj;
}


bool parkingSystemAddCar(ParkingSystem* obj, int carType) {
        if(obj->arr[carType-1]>0)
        {
            obj->arr[carType-1]--;
            return true;
        }
        return false;
}

void parkingSystemFree(ParkingSystem* obj) {
    free(obj);
}

/**
 * Your ParkingSystem struct will be instantiated and called as such:
 * ParkingSystem* obj = parkingSystemCreate(big, medium, small);
 * bool param_1 = parkingSystemAddCar(obj, carType);
 
 * parkingSystemFree(obj);
*/
