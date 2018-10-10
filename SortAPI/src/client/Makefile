.SUFFIXES:
.SUFFIXES: .cpp

GCC = g++

.cpp:
	$(GCC) -std=c++11 -O2 -Wall -Wextra -pedantic -Wshadow -Wformat=2 -Wfloat-equal -Wconversion -Wlogical-op -Wno-unused-result $< -o $@ -lcpprest -lssl -lcrypto -lboost_system  
