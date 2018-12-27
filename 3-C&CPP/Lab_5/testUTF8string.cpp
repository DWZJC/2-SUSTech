#define __FileName testUTF8string.cpp
#include <iostream>
#include "UTF8string.hpp"

using namespace std;
int main(int argc, char **argv)
{
    cout << "-------------------------------" << endl;
    UTF8string str1 = UTF8string("123");
    UTF8string str2 = UTF8string("456");
    UTF8string str3 = UTF8string("1张2佳3晨");
    UTF8string result;
    cout << "str1: " << str1.value() << endl;
    cout << "str2: " << str2.value() << endl;
    cout << "str3: " << str3.value() << endl;
    cout << "-------------------------------" << endl;
    result = str1 + str2;
    cout << "str1 + str2: " << result.value() << endl;
    cout << "str1: " << str1.value() << endl;
    cout << "str2: " << str2.value() << endl;
    cout << "-------------------------------" << endl;
    str2 += str1;
    cout << "str2 += str1, str2: " << str2.value() << endl;
    cout << "-------------------------------" << endl;
    result = 3 * str1;
    cout << "3 * str1: " << result.value() << endl;
    result = str1 * 2;
    cout << "str1 * 2: " << result.value() << endl;
    cout << "-------------------------------" << endl;
    result = !str3;
    cout << "!str3: " << result.value() << endl;
    cout << "-------------------------------" << endl;
    return 0;
}
