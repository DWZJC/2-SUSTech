# Lab4: Lab4.o
# 	gcc Lab4.o -o Lab4 

Lab4: testUTF8string.cpp utf8.c
	g++ -x c++ testUTF8string.cpp -x c utf8.c -o Lab4

clean:
	rm utf8.o Lab4.o Lab4 UTF8string.o

# Lab4.o :
# 	gcc -c utf8.c
# 	g++ Lab4.cpp utf8.o -o Lab4.o
# 	g++ Lab4.o -o Lab4

# CC = gcc
# C++ = g++
# LINK = g++ 

# root@DELL-ZJC-Sustc:~/Lab_4# gcc -c utf8.c
# root@DELL-ZJC-Sustc:~/Lab_4# g++ Lab4.cpp utf8.o
# root@DELL-ZJC-Sustc:~/Lab_4# ./a.out
# echo "嘤嘤嘤",
# LIBS = -L./ -lz -lm -lpcre	#库文件的包含路径放到使用-L./等表达式放到LIBS中的开头即可
# #must add -fPIC option
# CCFLAGS = $(COMPILER_FLAGS) -c -g -fPIC
# C++FLAGS = $(COMPILER_FLAGS) -c -g -fPIC 

# TARGET= Lab4 

# # INCLUDES = -I. -I../../ #头文件的包含路径放到INCLUDES后面

# C++FILES = Lab4.cpp
# #cpp文件 main.cpp \	
# #	       ../../hookmask.cpp
# CFILES = utf8.c	#c源文件 etc: cJSON.c ZipCoding.c TransferCoding.c mem_manage.c

# OBJFILE = $(CFILES:.c=.o) $(C++FILES:.cpp=.o) 

# all:$(TARGET) 

# $(TARGET): $(OBJFILE)	
# 		$(LINK) $^ $(LIBS) -Wall -fPIC -shared -o $@ 
		
# %.o:%.c	
# 		$(CC) -o $@ $(CCFLAGS) $< $(INCLUDES) 
	
# %.o:%.cpp	
# 		$(C++) -o $@ $(C++FLAGS) $< $(INCLUDES) 
	
# install:	
# 		tsxs -i -o $(TARGET) 
	
# clean:	
# 		rm -rf $(TARGET)	
# 		rm -rf $(OBJFILE)

