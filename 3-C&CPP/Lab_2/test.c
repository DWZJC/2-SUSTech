#include <stdio.h>
#include <string.h>

int main()
{
    char str[] = "lanzhihui is a good boy!"; //假设len=strlen(str)
    char str_1[25];                          //测试strncpy()
    memset(str_1, 0, sizeof(str_1));
    strncpy(str_1, str, sizeof(str_1)); //当数组大小 小于等于 len时，也会发生缓冲区溢出，但是程序不会崩溃，发生缓冲区溢出是因为数组没有以'\0'结束，或者说截断超出长度限制的字符串，包括源字符串结尾的’\0’。
    //当数组大小 大于等于 len+1时，才能正确运行，并且数组尾部为'\0'
    str_1[sizeof(str_1) - 1] = '\0';        //上面问题：缓冲区溢出可以通过数组尾部加'\0'解决
    strncpy(str_1, str, sizeof(str_1) - 1); //本句正确使用strncpy,不会造成缓冲区溢出，并且在数组最后加入了'\0'。
    puts(str_1);
    return 0;
}