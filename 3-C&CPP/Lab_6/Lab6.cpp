#define __FileName Lab6.cpp
#include "Lab6.hpp"
#include <iostream>

int main(int argc, char const *argv[])
{
    Box boxA(1, 2, 3);
    Box boxB(1, 3, 4);
    std::cout << "boxA: " << boxA << std::endl;
    bool result = boxA < boxB;
    std::cout << result << std::endl;
    return 0;
}
