#include <stdio.h>
#include "utf8.h"
#include <stdlib.h>

int main()
{
    // char *str = "你好1Ω";
    // unsigned char *pt;
    // pt = (unsigned char *)str;
    // int lenptr = 0;
    // int codept = utf8_to_codepoint(pt, &lenptr);
    // printf("你 -> %x [%d]\n", codept, lenptr);
    // pt += lenptr;
    // codept = utf8_to_codepoint(pt, &lenptr);
    // printf("好 -> %x [%d]\n", codept, lenptr);
    // pt += lenptr;
    // codept = utf8_to_codepoint(pt, &lenptr);
    // printf("1 -> %x [%d]\n", codept, lenptr);
    // pt += lenptr;
    // codept = utf8_to_codepoint(pt, &lenptr);
    // printf("Ω -> %x [%d]\n", codept, lenptr);

    FILE *fp = fopen("test.txt", "r");
    fseek(fp, 0, SEEK_SET);
    char *str = (char *)malloc(100000 * sizeof(char));
    fgets(str, 100000, fp);
    printf("str = %s\n", str);

    unsigned char *pt;
    pt = (unsigned char *)str;
    int lenptr = 0;
    int codept = 0;
    printf("utf8_charlen(str) = %d\n",utf8_charlen(str));
    codept = utf8_to_codepoint(pt, &lenptr);
    printf("你 -> %x [%d]\n", codept, lenptr);
    pt += lenptr;
    codept = utf8_to_codepoint(pt, &lenptr);
    printf("好 -> %x [%d]\n", codept, lenptr);
    pt += lenptr;
    codept = utf8_to_codepoint(pt, &lenptr);
    printf("1 -> %x [%d]\n", codept, lenptr);
    pt += lenptr;
    if (*pt == '\0'){
        printf("at the end\n");
    }else{
        printf("pt = %s\n",pt);
    }
    codept = utf8_to_codepoint(pt, &lenptr);
    printf("Ω -> %x [%d]\n", codept, lenptr);
    pt += lenptr;
    if (*pt == '\0'){
        printf("at the end\n");
    }
    // char *str;
    // fgets(str, 10000, fp);

    // unsigned char *pt = (unsigned char *)str;
    // int lenptr = 0;
    // int codept = 0;
    // /* 加载数据文件，若加载失败报告并结束 */
    // char ch;
    // printf("$$$\n");
    // while (ch = fgetc(fp) != EOF)
    // {
    //     // putchar(ch);
    //     printf("%c\n",ch);
    // }

    // while (!feof(fp))
    // {
    //     codept = utf8_to_codepoint(pt, &lenptr);
    //     printf("%x [%d]\n", codept, lenptr);
    //     pt += lenptr;
    // }
}
