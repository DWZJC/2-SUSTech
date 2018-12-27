#ifndef Lab6_HPP
#define Lab6_HPP
#define __FileName Lab6.hpp
#include <iostream>

// THIS FILE DOESN'T NEED TO BE MODIFIED
// YOU NEED TO WRITE UTF8string.cpp

class Box
{
  private:
    int length;
    int breadth;
    int height;

  public:
    // Constructors
    // The default constructor of the class should initialize l, b, and h to 0.
    Box()
    {
        length = 0;
        breadth = 0;
        height = 0;
    }
    Box(int _length, int _breadth, int _height)
    {
        length = _length;
        breadth = _breadth;
        height = _height;
    }
    // Return box's length
    int getLength()
    {
        return this->length;
    }
    // Return box's breadth
    int getBreadth()
    {
        return this->breadth;
    }
    // Return box's height
    int getHeight()
    {
        return this->height;
    }

  public:
    // Return the volume of the box
    long long CalculateVolume()
    {
        return this->breadth * this->height * this->length;
    }

  public:
    friend bool operator<(const Box boxA, const Box boxB)
    {
        /* boxA and boxB will not be changed */
        if (boxA.length > boxB.length)
            return false;
        else if (boxA.length == boxB.length && boxA.breadth > boxB.breadth)
            return false;
        else if (boxA.length == boxB.length && boxA.breadth == boxB.breadth && boxA.height > boxB.height)
            return false;
        return true;
    }

    friend std::ostream & operator<<(std::ostream &out, Box &box)
    {
        out << box.length << " " << box.breadth<< " " << box.height;
        return out;
    }
};
#endif