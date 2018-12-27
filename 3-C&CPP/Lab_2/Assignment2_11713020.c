#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <malloc.h>

#define F_PATH "world_cities.csv"
#define MAX_LINE 1
#define MAX_NAME_LENGTH 25
#define MAX_ARRAY_SIZE 800

typedef struct location
{
	char City_Name[MAX_NAME_LENGTH + 1];
	char Province_Name[MAX_NAME_LENGTH + 1];
	char Country_Name[MAX_NAME_LENGTH + 1];
	double Latitude;
	double Longitude;
} Location, *pLocation;
void data_Initial(void);
int data_Search(char *);
int readFile(void);
char *strtok_new(char *, char const *);
void printLocation(pLocation);
int getData(void);
void process(void);
int read_state; //The number of the data we get
pLocation data[MAX_ARRAY_SIZE];
double compute(double, double, double, double);

int main()
{
	printf("-------------------------------------------------------------------\n");
	data_Initial();
	printf("-------------------------------------------------------------------\n");
	while (1)
	{
		process();
		system("pause");
	}
	// system("pause");
	return 0;
}

void process(void)
{
	int index_City = 0;

	index_City = getData();
	printLocation(data[index_City]);
	char *city_1 = data[index_City]->City_Name;
	double phi_1 = 90 - data[index_City]->Latitude;
	double theta_1 = data[index_City]->Longitude;
	printf("\n\n");
	index_City = getData();
	printLocation(data[index_City]);
	char *city_2 = data[index_City]->City_Name;
	double phi_2 = 90 - data[index_City]->Latitude;
	double theta_2 = data[index_City]->Longitude;

	double distance = compute(phi_1, theta_1, phi_2, theta_2);
	printf("\n\n-------------------------------------------------------------------\n");
	printf(">>> The distance between %s and %s is %f km.\n", city_1, city_2, distance);
	printf("-------------------------------------------------------------------\n\n");
	return;
}

int getData(void)
{
	int i = -1;
	char city_Name[MAX_NAME_LENGTH + 1];
	int length = 0;
	printf("-------------------------------------------------------------------\n");
	printf("Please input the name of the city you want, or <bye> to quit.......\n> ");
	while (i < 0)
	{
		fflush(stdin);
		fgets(city_Name, MAX_NAME_LENGTH, stdin);
		/* 解决fgets()会把'\n'也读取的问题 */
		city_Name[strlen(city_Name) - 1] = '\0';
		printf("-------------------------------------------------------------------\n");

		if (!strcasecmp("bye", city_Name))
		{
			exit(0);
		}
		length = strlen(city_Name);
		if (length < 3 || length > MAX_NAME_LENGTH)
		{
			printf("Please input another name which is more specific...\n");
			continue;
		}
		else
		{
			i = data_Search(city_Name);
		}
	}

	return i;
}

/* return the index of the city, if more than one, print the following and return -1, */
int data_Search(char *city_Name)
{

	int length = strlen(city_Name);
	// printf("city_Name is %s\t%d\n", city_Name, length);
	int i = 0;
	int j = 0;
	int num = 0;
	while (i++ < read_state)
	{
		j = 0;
		/* 需要控制用户输入长度不超过MAX_NAME_LENGTH */
		if (!strnicmp(data[i]->City_Name, city_Name, length))
		{ /* 前length个字母不区分大小写匹配成功 */
			if (!strnicmp(data[i + 1]->City_Name, city_Name, length))
			{
				printf("\n-------------------------------------------------------------------\n");
				printf("There are several cities whose name include <%s>, such as\n", city_Name);
				while (i < read_state && !strnicmp(data[i]->City_Name, city_Name, length))
				{
					printf("> %s\n", data[i]->City_Name);
					i++;
				}
				printf("-------------------------------------------------------------------\n\n");
				printf("-------------------------------------------------------------------\n");
				printf("Please input a more specific one again...\n> ");
				return -1;
			}
			printf("\n-------------------------------------------------------------------\n");
			printf("We have found the city <%s> succeedly.\n", data[i]->City_Name);
			printf("-------------------------------------------------------------------\n");
			// printf("i = %d\n", i);
			return (i);
		}
	}
	printf("The name of the city you input <%s> is not found.\n");
	printf("Please input another again...\n");
	return -2;
}

void data_Initial(void)
{
	read_state = readFile(); // store the number of the data we readFile may less than what we want
	// printf("read_state = %d\n", read_state);
	switch (read_state)
	{
	case -1:
		printf("File is not found!\n");
		system("pause");
		exit(0);
		break;
	case MAX_ARRAY_SIZE:
		printf("Finished reading the file\n");
		break;
	default:
		printf("Finished reading the file\n");
		printf("Data is too much, some of which havn't been loaded!\n");
		break;
	}
	printf("We get %d data\n", read_state);
}

int readFile(void)
{
	FILE *fp = NULL;
	char line[1000];
	/* 加载数据文件，若加载失败报告并结束 */
	int i = -1;
	if ((fp = fopen(F_PATH, "r")) != NULL)
	{
		printf("File is opened\n");

		/* 逐行读取并存入数据 */
		/* TODO (#1#): 需要考虑数组长度不足，数据量过大溢出 */
		while (fgets(line, 100000, fp) && i < MAX_ARRAY_SIZE)
		{
			pLocation pCity = (pLocation)malloc(sizeof(Location));
			strncpy(pCity->City_Name, strtok_new(line, ","), MAX_NAME_LENGTH);
			strncpy(pCity->Province_Name, strtok_new(NULL, ","), MAX_NAME_LENGTH);
			strncpy(pCity->Country_Name, strtok_new(NULL, ","), MAX_NAME_LENGTH);
			pCity->Latitude = atof(strtok_new(NULL, ","));
			pCity->Longitude = atof(strtok_new(NULL, "\n"));
			data[i] = pCity;
			i++;
		}
		fclose(fp);
		fp = NULL;
	}
	return i;
}
/* 处理连续分隔符问题 http://www.it1352.com/482667.html */
char *strtok_new(char *string, char const *delimiter)
{
	static char *source = NULL;
	char *p, *riturn = 0;
	if (string != NULL)
		source = string;
	if (source == NULL)
		return NULL;
	if ((p = strpbrk(source, delimiter)) != NULL)
	{
		*p = 0;
		riturn = source;
		source = ++p;
	}

	return riturn;
}
double compute(double phi_1, double theta_1, double phi_2, double theta_2)
{
	double Pi = acos(-1.0);
	double c = 0.0;
	phi_1 = phi_1 / 180 * Pi;
	theta_1 = theta_1 / 180 * Pi;
	phi_2 = phi_2 / 180 * Pi;
	theta_2 = theta_2 / 180 * Pi;
	c = sin(phi_1) * sin(phi_2) * cos(theta_1 - theta_2) + cos(phi_1) * cos(phi_2);
	int R = 6371;
	double d = 0.0;
	d = R * acos(c);
	return d;
}

/* print the node of the struct with format */
void printLocation(pLocation l)
{
	printf("[");
	printf(" %s,", l->City_Name);
	printf(" %s,", l->Province_Name);
	printf(" %s,", l->Country_Name);
	printf(" %f,", l->Latitude);
	printf(" %f,", l->Longitude);
	printf("]\n");
	return;
}