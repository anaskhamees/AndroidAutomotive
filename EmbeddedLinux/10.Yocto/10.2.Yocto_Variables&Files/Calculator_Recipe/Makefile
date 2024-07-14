# Makefile

# Compiler

# Compiler flags
CFLAGS = -Wall -Wextra

# Source file
SRC = calculator.c

# Executable name
EXE = calculator

# Default target
all: $(EXE)

# Compile the source file
$(EXE): $(SRC)
	$(CC) $(CFLAGS) -o $@ $^

# Clean up
clean:
	rm -f $(EXE)

