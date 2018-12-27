#define __FileName testUTF8string.cpp
#include <iostream>
#include "UTF8string.hpp"
using namespace std;
int main(int argc, char **argv)
{
    UTF8string test = UTF8string("Mais où sont les neiges d'antan?");
    UTF8string test2 = UTF8string("Всё хорошо́, что хорошо́ конча́ется");
    cout << "test contains: " << test.value() << endl;
    cout << "length in bytes of test: " << test.bytes() << endl;
    cout << "number of characters (one 2-byte character): " << test.length() << endl;
    cout << "position of \"sont\": " << test.find("sont") << endl;
    cout << "test2 before replacement: " << test2.value() << endl;
    test2.replace("хорошо́", "просто");
    cout << "test2 after replacement: " << test2.value() << endl;
    UTF8string test3 = UTF8string("123中文测试123");
    cout << "test3 before replacement: " << test3.value() << endl;
    test3.replace("1", "汉语");
    cout << "test3 after replacement: " << test3.value() << endl;
    return 0;
}
