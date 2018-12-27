#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define START_CMD		0
#define STOP_CMD		1
#define RESTART_CMD		2
#define STATUS			3
#define EXIT			4

int main(){
	printf(">");
	char *commands[] = {"start", "stop", "restart", "status", "exit" };
	int i = -1;
	char command[10];
	while(1){
		fflush(stdin);
		/* ��ֹԽ�� */
		char c;
		int num = 0;
		/* ����ǰ������пո� */
		c =  getchar();
		while(c == ' '){
			c =  getchar();
		}
		/* �ӵ�һ���ǿո񴦿�ʼ��ȡ */
		command[0] = c;
		c =  getchar();
		
		while((c !=' ')&&(c !='\n')){
			num++;
			command[num] = c;
			/* �����restartֻ��7���� ��֮���Կɶ�ȡ����ֱ����ΪΥ�� */ 
			if(num > 6){
				printf("Invalid command\n>");
				goto out;
			}
			c =  getchar();
		}
		/* ĩλ�� \0��ʾ�ַ��� */
		command[num+1] = '\0';
		/* �������ң����������Ƿ���� */
		for(i=0; i<5; i++){
			if(strcmpi(command,*(commands+i))==0) {
				break;
			}
		}
		switch(i){
			case START_CMD:
			case STOP_CMD:
			case RESTART_CMD:
			case STATUS:
				printf("command %s recognized\n>", commands[i]);
				break;
			case EXIT:
				return 4;
			default:
				printf("Invalid command\n>");
				break;
		}
		out:;
	}	
	return 0;
} 
