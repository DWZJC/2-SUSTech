#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "utf8.c"

#define F_PATH "Blocks.txt"
#define MAX_ARRAY_SIZE 300
#define MAX_LINE_LENGTH 1000
#define MAX_BLOCK_NAME_SIZE 100

typedef struct blocks
{
    int Start_Code;
    int End_Code;
    char Block_Name[MAX_BLOCK_NAME_SIZE + 1];
} Blocks, *pBlocks;
int test(void);
void data_Initial(void);
int readBlockFile(void);
int read_state;
void printBlock(pBlocks);
pBlocks data[MAX_ARRAY_SIZE];
int search_Block(int);
int process(void);

int main(int argc, char const *argv[])
{
    /* This function must be processed as it's used to load the data of blocks */
    data_Initial();
    /* >>> It will be better if you only run one of the next two functions at one time <<< */
    // test(); /* This function is used to test finding block name with a unicode */
    process(); /* This function is used to display on the standard output the name of the block */
               /* to which most characters belong. */
    return 0;
}

int process()
{
    printf("----------------------------------------------------------\n");
    printf("Process a file from the standard input:\n");
    int counters[read_state];
    for (int i = 0; i < read_state; i++) /*initialize the counters*/
        counters[i] = 0;
    unsigned char *pt;
    int lenptr = 0;
    int codept = 0;
    char line[MAX_LINE_LENGTH];
    while (fgets(line, MAX_LINE_LENGTH, stdin))
    { /* Read the file from the standard input line by line. */
        pt = (unsigned char *)line;
        while (*pt != '\0' && *pt != '\n' && *pt != '\r')
        { /* Read the file from the standard input character by character. */
            /* Break when the pointer got to the end of the line */
            codept = utf8_to_codepoint(pt, &lenptr);
            /* Move the pointer depending on the lenptr */
            pt += lenptr;
            /* Record the times of blocks appeared which the charaters belongs to */
            counters[search_Block(codept)]++;
        }
    }
    /* Read the file from the standard input character by character. */
    int max = 0;
    for (size_t i = 0; i < read_state; i++)
        /* Then find the index of the block with the most characters in. */
        if (counters[i] > max)
            /* Record the times of each block appearing */
            max = counters[i];

    /* Print the block which the most characters appear in */
    printf("> The block which most of the characters appear in:\n");
    for (size_t i = 0; i < read_state; i++)
        if (counters[i] == max)
            printf("> -> %s\n", data[i]->Block_Name);
    printf("----------------------------------------------------------\n");
}

/* return i(>= 0): get the index of which block this value belongs to 
 * return -1 : not found 
 */
int search_Block(int value)
{
    for (size_t i = 0; i < read_state; i++)
        if (data[i]->Start_Code <= value && value <= data[i]->End_Code)
            return i;
    return -1;
}

/* return -1 : file is opened but no accessable data.
 * return 0 : file is not opened succeedly.
 * return i (> 0): file is opened and the number of accessable data is i.
 */
int readBlockFile(void)
{
    FILE *fp = NULL;
    char line[1000];
    /* Load the Blocks.txt and program will exit with announcement when fail in loading. */
    int i = 0;
    if ((fp = fopen(F_PATH, "r")) != NULL)
    {
        printf("----------------------------------------------------------\n");
        printf("Initializing...\n");
        printf("> File is opened\n");
        /* Read and store the date in the file line by line */
        while (fgets(line, MAX_LINE_LENGTH, fp) && i < MAX_ARRAY_SIZE)
        {
            // skip all the blank lines and comments
            if (line[0] == '\n' || line[0] == '\r' || line[0] == '#')
                continue;
            pBlocks pBlock = (pBlocks)malloc(sizeof(Blocks));
            /* convert the 16-base number to 10-base number */
            pBlock->Start_Code = strtol(strtok(line, "."), NULL, 16);
            pBlock->End_Code = strtol(strtok(NULL, ";") + 1, NULL, 16);
            strncpy(pBlock->Block_Name, strtok(NULL, "\n") + 1, MAX_BLOCK_NAME_SIZE);
            data[i] = pBlock;
            i++;
        }
        fclose(fp);
        fp = NULL;
        return i;
    }
    else
    {
        return 0;
    }
}

void data_Initial(void)
{
    read_state = readBlockFile(); // store the number of the data we readFile may less than what we want
    switch (read_state)
    {
    case -1:
        printf("> File is opened but there is no accessable data.\n");
        system("pause");
        exit(0);
    case 0:
        printf("> File is not found!\n");
        system("pause");
        exit(0);
    default:
        printf("> Finished reading the file\n");
    }
    printf("> We get %d data\n", read_state);
    printf("----------------------------------------------------------\n\n");
    return;
}

void printBlock(pBlocks b)
{
    printf("[");
    printf(" %d,", b->Start_Code);
    printf(" %d,", b->End_Code);
    printf(" %s", b->Block_Name);
    printf(" ]\n");
    return;
}

int test()
{
    int index = 0;
    int UnicodeValue = 0;
    printf("----------------------------------------------------------\n");
    printf("Small test function:\n");
    printf("> Print the unicode value:\n> ");
    fflush(stdin);
    scanf("%d", &UnicodeValue);
    index = search_Block(UnicodeValue);
    if (index < 0)
        printf("invalid\n");
    else
    {
        printf("> It belongs to the block named:\n");
        printf("> > %s\n", data[index]->Block_Name);
    }

    fflush(stdin);
    printf("----------------------------------------------------------\n\n");
    return 0;
}