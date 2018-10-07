.SUFFIXES:
.SUFFIXES: .cpp

GCC = g++

.cpp:
	$(GCC) -std=c++14 -O2 -Wall -Wextra -pedantic -Wshadow -Wformat=2 -Wfloat-equal -Wconversion -Wlogical-op -Wno-unused-result $< -o $@ -lboost_system -lcrypto -lssl -lcpprest
