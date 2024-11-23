cmake_minimum_required(VERSION 3.1...3.22)
project(calculator VERSION 0.0 DESCRIPTION "Calculator Project" LANGUAGES C)	
set(SOURCE_FILES /Lib_Src/add.c  /Lib_Src/div.c  /Lib_Src/mod.c  /Lib_Src/multi.c  /Lib_Src/sub.c)
add_executable(${PROJECT_NAME} main.c)
target_include_directories(${PROJECT_NAME} PRIVATE include)
add_library(operations STATIC ${SOURCE_FILES})
target_link_libraries(${PROJECT_NAME} PUBLIC operations)

