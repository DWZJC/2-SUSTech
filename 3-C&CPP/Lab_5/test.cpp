#include <iostream>
#include <string>
main(int argc, char const *argv[])
{
    std::string str1 = "123";
    std::string str2 = "456";
    std::string str3;
    str3 = str1 + str2;
    std::cout << "str1+str2 = " << str1+str2 << std::endl;
    // std::cout << "str1+=str2 = " << str1 += str2 << std::endl;
    return 0;
}
