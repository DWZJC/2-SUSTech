# CS205 C/ C++ Programming - Lab Assignment 4

* * *

**Name:** Jiachen Zhang

**SID:** 11713020

* * *

# Part 1 - Analysis

* * *

> This Lab Assignment focuses on the combination of C and C++, and I need to learn about the UTF8string.\
  The difference between UTF8string and a regular C++ string is that UTF8string knows "characters" when a string only knows bytes.\
  I need to creat UTF8string.cpp and finish it.

> I finish this Lab Assignment with the following small steps.\

- I add these code to the head of `utf8.h`,\
    because rules for resolving are different in C and C++.\
    This is required to tell the linker that these are C, not C++, functions and that C rules should apply. 
    
```cpp
#ifdef __cplusplus
extern "C"
{
#endif
    extern unsigned char *codepoint_to_utf8(const unsigned int cp, unsigned char *val);
    extern int isutf8(const unsigned char *u);
    extern unsigned int utf8_to_codepoint(const unsigned char *u, int *lenptr);
    extern int utf8_charlen(unsigned char *s);
#ifdef __cplusplus
}
#endif
```
- Then I finish the four methods, which are\
    `length()`, that returns the length IN CHARACTERS of the UTF8string.\
    `bytes()`, that returns the number of bytes used for storing the 
UTF8string.\
    `find(string substr)`, that returns the CHARACTER POSITION where 
substr starts.\
    `replace(UTF8string to_remove, UTF8string replacement)`, that replaces 
to_remove with replacement. 
> The thoughts and comments are in **Part 2 - Code**.

## Part 2 - Code

```cpp
#define __FileName UTF8string.cpp
#include <iostream>
#include <string>

#include "utf8.h"
#include "UTF8string.hpp"

size_t UTF8string::length()
{
    unsigned char *s = (unsigned char *)m_s.c_str();
    return utf8_charlen(s);
}

size_t UTF8string::bytes() // Number of bytes in the string
{
    int lenptr = 0;
    int bytes = 0;
    unsigned char *pt = (unsigned char *)m_s.c_str();
    while (*pt != '\0' && *pt != '\n' && *pt != '\r')
    {
        lenptr = isutf8(pt);
        /* move the pointer */
        pt += lenptr;
        /* store the bytes of the characters processed */
        bytes += lenptr;
    }
    return bytes;
}

int UTF8string::find(std::string substr)
{
    /* The pointer of the Text */
    unsigned char *pT = (unsigned char *)m_s.c_str();
    int lenptrT = 0;
    int codeptT = 0;
    /* The pointer of the Pattern */
    unsigned char *pP = (unsigned char *)substr.c_str();
    int lenptrP = 0;
    int codeptP = 0;
    int i = 0;
    /* BF Algorithm */
    int lenT = 0;
    int lenP = 0;
    while (lenP < substr.size() && i < m_s.size())
    {/* using the function of utf8_to_codepoint 
        to compare the value of utf8 characters and get the size of each character*/
        codeptT = utf8_to_codepoint(pT + lenT, &lenptrT);
        codeptP = utf8_to_codepoint(pP + lenP, &lenptrP);
        if (codeptT == codeptP)
        {
            lenT += lenptrT;
            lenP += lenptrP;
        }
        else
        {
            /* move the pointer of Pattern and Text to the original position */
            lenP = lenT = 0;
            codeptT = utf8_to_codepoint(pT + lenT, &lenptrT);
            pT += lenptrT;
            /* compute the position of the pointer */
            i++;
        }
    }
    if (lenP >= substr.size())
        return i + 1;
    else
        return 0;
}

void UTF8string::replace(UTF8string remove, UTF8string replacement)
{
    std::string str_remove = remove.value();
    const std::string str_replacement = replacement.value();
    /* using the method of string to achieve this function */
    int start = m_s.find(str_remove, 0);
    while (start != -1)
    {/* break until there is no remove-pattern */
        m_s = m_s.replace(start, str_remove.length(), str_replacement);
        /* find the remove-pattern continuously */
        start = m_s.find(str_remove, 0);
    }
}
```

## Part 3 - Result & Verification
> I use `testUTF8string.cpp` to test my `UTF8string.cpp`.

```cpp
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
```
Input
```
make Lab4
```
Output:
```
test contains: Mais où sont les neiges d'antan?
length in bytes of test: 33
number of characters (one 2-byte character): 32
position of "sont": 9
test2 before replacement: Всё хорошо́, что хорошо́ конча́ется
test2 after replacement: Всё просто, что просто конча́ется
test3 before replacement: 123中文测试123
test3 after replacement: 汉语23中文测试汉语23
```


## Part 4 - Difficulties & Solutions

> Hybrid compiling C and C++ programming languages is not a easy work.\
    I finish it with the help of SA (Thanks!) and using this command:
    `g++ -x c++ Lab4.cpp UTF8string.cpp -x c utf8.c -o Lab4`

> For developing good programming habits, I use `std::cout`, `std::endl` and `std::string`, etc. instead using `using namespace std;` in the `UTF8string.cpp` file.
