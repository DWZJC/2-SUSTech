#ifndef UTF8STRING_HPP
#define UTF8STRING_HPP

#include <iostream>
#include <string>
#include "utf8.h"

// THIS FILE DOESN'T NEED TO BE MODIFIED
// YOU NEED TO WRITE UTF8string.cpp

class UTF8string
{
  std::string m_s;

public:
  // Constructors - No code required
  UTF8string(){};
  UTF8string(const char *s) { m_s = std::string(s); };
  UTF8string(std::string s) { m_s = s; };
  //
  // The four following functions need to be coded
  // in UTF8string.cpp
  //
  size_t length()
  {// Number of UTF-8 characters in the string
    unsigned char *s = (unsigned char *)m_s.c_str();
    return utf8_charlen(s);
}
  size_t bytes() 
  {// Number of bytes in the string
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
  // IMPORTANT: for find(), 1st character = 1 (not zero)
  // Return 0 if not found
  int find(std::string substr){
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
    {/* using the function of utf8_to_codepoint to compare the value of utf8 characters and get the size of each */
        codeptT = utf8_to_codepoint(pT + lenT, &lenptrT);
        codeptP = utf8_to_codepoint(pP + lenP, &lenptrP);
        if (codeptT == codeptP)
        {
            lenT += lenptrT;
            lenP += lenptrP;
        }
        else
        {
            /* move the pointer of Pattern to the original position */
            /* move the pointer of Text to the original position */
            lenP =lenT = 0;
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
  void replace(UTF8string remove, UTF8string replacement){
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
  // ---------------------------------------
  // value() is for checking what was done
  // No code required
  std::string value() { return m_s; };
};

#endif
