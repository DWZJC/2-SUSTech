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
		/* 防止越界 */
		char c;
		int num = 0;
		/* 跳过前面的所有空格 */
		c =  getchar();
		while(c == ' '){
			c =  getchar();
		}
		/* 从第一个非空格处开始读取 */
		command[0] = c;
		c =  getchar();
		
		while((c !=' ')&&(c !='\n')){
			num++;
			command[num] = c;
			/* 最长命令restart只有7个， 若之后仍可读取，则直接视为违规 */ 
			if(num > 6){
				printf("Invalid command\n>");
				goto out;
			}
			c =  getchar();
		}
		/* 末位加 \0表示字符串 */
		command[num+1] = '\0';
		/* 遍历查找，输入命令是否存在 */
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
