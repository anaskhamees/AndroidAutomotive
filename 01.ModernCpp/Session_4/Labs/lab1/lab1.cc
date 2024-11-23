#include <iostream>

enum class ErrorCode {
    BadRequest = 400,
    NotFound = 404,
    ServerError = 500,
    GatewayTimeOut = 504
};

void printError(ErrorCode err) {
    switch(err) {
        case ErrorCode::BadRequest:
            std::cout << "BadRequest: " << static_cast<int>(ErrorCode::BadRequest) << std::endl;
            break;
        case ErrorCode::NotFound:
            std::cout << "NotFound: " << static_cast<int>(ErrorCode::NotFound) << std::endl;
            break;
        case ErrorCode::ServerError:
            std::cout << "ServerError: " << static_cast<int>(ErrorCode::ServerError) << std::endl;
            break;
        case ErrorCode::GatewayTimeOut:
            std::cout << "GatewayTimeOut: " << static_cast<int>(ErrorCode::GatewayTimeOut) << std::endl;
            break;
        default:
            std::cout << "Wrong Input, Try Again" << std::endl;
    }
}

int main() {
    printError(ErrorCode::BadRequest);
    printError(ErrorCode::NotFound);
    printError(ErrorCode::ServerError);
    printError(ErrorCode::GatewayTimeOut);
    return 0;
}

