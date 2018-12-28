# CS205 C/ C++ Programming - Lab Assignment 6

* * *

**Name:** Jiachen Zhang

**SID:** 11713020

* * *

# Part 1 - Analysis

* * *

> This Lab Assignment focuses on the overload of the functions, and I need to learn about it.

> I need to learn about the friend functions. 

> I finish this Lab Assignment with the following small steps.

- This assignment focuses on the use of the class in cpp.
- Then I finish the following commends, which are\
    - `int getLength()` that will return box's length 
    - `int getBreadth()` that will return box's breadth
    - `int getHeight()` that will return box's height 
    - `long long CalculateVolume()` that will return the volume of the box
    - overload the < and << operator for class box.
> The thoughts and comments are in **Part 2 - Code**.

## Part 2 - Code

```cpp
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
```

## Part 3 - Result & Verification
> I use `Lab6.cpp` to test my `Lab6.hpp`.

```cpp
#define __FileName Lab6.cpp
#include "Lab6.hpp"
#include <iostream>

int main(int argc, char const *argv[])
{
    Box boxA(1, 2, 3);
    Box boxB(1, 3, 4);
    std::cout << "boxA: " << boxA << std::endl;
    std::cout << "boxA.CalculateVolume = " << boxA.CalculateVolume() << std::endl;
    std::cout << "boxB.getHeight = " << boxB.getHeight() << std::endl;
    bool result = boxA < boxB;
    std::cout << result << std::endl;
    return 0;
}
```
Output:
```
boxA: 1 2 3
boxA.CalculateVolume = 6
boxB.getHeight = 4
1
```


## Part 4 - Difficulties & Solutions

> friend function is learned in last assignment, so it's not too difficult for me.

> The overload of the operator methonds is really useful as it can simplify the code which I use to test.

> For developing good programming habits, I use `std::cout`, `std::endl` and `std::string`, etc. instead using `using namespace std;` in the `UTF8string.hpp` file.
