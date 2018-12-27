#include <stdio.h>
#include <math.h>

double compute(double, double, double, double);
double latitude_1 = -200;		//纬度 
double longitude_1 = -200; 		//经度 
double latitude_2 = -200;		//纬度 
double longitude_2 = -200; 		//经度 
char city_1[100];
char city_2[100];
void read(void);

int main(){
/* TODO (#1#): 读取地理坐标 */
	read();
/*	Let phi = 90 - latitude. 
	Let theta = longitude	*/
	double phi_1 = 90 - latitude_1;
	double theta_1 = longitude_1;
	double phi_2 = 90 - latitude_2;
	double theta_2 = longitude_2;
/*输出结果*/
	double distance = 0.0;
	distance = compute(phi_1, theta_1, phi_2, theta_2);
	printf("The distance between %s and %s is %f km", city_1, city_2, distance);

	return 0;
} 

double compute(double phi_1, double theta_1, double phi_2, double theta_2){
	double Pi = acos(-1.0);
	double c = 0.0;
	/*转换为弧度制计算*/ 
	phi_1 = phi_1/180*Pi;
	theta_1 = theta_1/180*Pi;
	phi_2 = phi_2/180*Pi;
	theta_2 = theta_2/180*Pi;
	/*计算城市间角度【弧度制】 ---> c = sin(phi1)*sin(phi2)*cos(theta1-theta2) + cos(phi1)*cos(phi2).*/  
	c = sin(phi_1)*sin(phi_2)*cos(theta_1-theta_2) + cos(phi_1)*cos(phi_2);
	/*地球半径R = 6,371 km*/
	int R = 6371;
	/*计算弧线距离 ---> d = R*Arccos(c)*Pi/180*/ 
	double d = 0.0;
	d = R*acos(c);
	return d;
}

void read(){
	do{
		printf("Please input the name of the first city, then press ENTER\n");
		fflush(stdin);
		gets(city_1);
		printf("Please input the name, latitude and longitude of the first city, divided with space:\n");
		fflush(stdin);
		scanf("%lf",&latitude_1);
		scanf("%lf",&longitude_1);
		if(!(-90 <= latitude_1 && latitude_1 <= 90)||!(-180 <= longitude_1 && longitude_1 <= 180)||latitude_1 == -200||longitude_1 == -200){
			printf("\n\n\n*****The data is invalid, please input again*****\n\n\n");
		}
	}while(!(-90 <= latitude_1 && latitude_1 <= 90)||!(-180 <= longitude_1 && longitude_1 <= 180)||latitude_1 == -200||longitude_1 == -200);
	
	
	
	
	
	
	
	do{
		printf("Please input the name of the second city, then press ENTER\n");
		fflush(stdin);
		gets(city_2);
		printf("Please input the name, latitude and longitude of the second city, divided with space:\n");
		fflush(stdin);
		scanf("%lf",&latitude_2);
		scanf("%lf",&longitude_2);
		if(!(-90 <= latitude_2 && latitude_2 <= 90)||!(-180 <= longitude_2 && longitude_2 <= 180)||latitude_2 == -200||longitude_2 == -200){
			printf("\n\n\n*****The data is invalid, please input again*****\n\n\n");
		}
	}while(!(-90 <= latitude_2 && latitude_2 <= 90)||!(-180 <= longitude_2 && longitude_2 <= 180)||latitude_2 == -200||longitude_2 == -200);
}
