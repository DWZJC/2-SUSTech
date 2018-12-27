//--------------------------Lab4.cpp
#include <iostream>
#include <string>
#include "UTF8string.hpp"
extern "C"
{
#include "utf8.h"
}

#define __message 0
#define __result 0
using namespace std;

main(int argc, char const *argv[])
{
    // string test = string("Mais où sont les neiges d'antan?");
    // string test2 = Ustring("Всё хорошо́, что хорошо́ конча́ется");
    // cout << "test contains: " << test.value() << endl;
    // cout << "length in bytes of test: " << test.bytes() << endl;
    // cout << "number of characters (one 2-byte character): " << test.length()
    //                                                         << endl;
    // cout << "position of \"sont\": " << test.find("sont") << endl;
    // cout << "test2 before replacement: " << test2.value() << endl;
    // test2.replace("хорошо́", "просто");
    // cout << "test2 after replacement: " << test2.value() << endl;
    // UTF8string test3 = UTF8string("中文测试123");
    // cout << "test3 before replacement: " << test3.value() << endl;
    // test3.replace("中", "中");
    // cout << "test3 after replacement: " << test3.value() << endl;
    string str;
    cin >> str;

    UTF8string utf8str = string(str);
    // cout << "length is ";
    // cout << utf8str.length() << endl;
    // cout << "bytes is ";
    // cout << utf8str.bytes() << endl;
    // cout << "bytes is ";
    // cout << utf8str.find("张佳") << endl;
    cout << "str = " << utf8str.value() << endl;
    utf8str.replace("涨价", "【张佳**】");
    cout << "str = " << utf8str.value() << endl;
#if __result
    cout << "length is ";
    cout << str.length() << endl;
    cout << "bytes is ";
    cout << bytes(str) << endl;
    cout << "length is ";
    cout << length(str) << endl;
#endif
    return 0;
}
