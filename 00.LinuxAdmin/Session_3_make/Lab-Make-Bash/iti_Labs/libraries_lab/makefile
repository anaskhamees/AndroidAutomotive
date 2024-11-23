# The C compiler to use
CC = arm-cortexa9_neon-linux-musleabihf-gcc

OBJ_DIR = objectDirectory
# Include paths for the compiler
INCLUDES = -I ./Includes

# Source files (all .c files in the ./src and ./app directory)
SOURCES = $(wildcard ./src/*.c ./app/*.c)

# Object files (all .o files in the $(OBJ_DIR) directory)
OBJS = $(patsubst %.c, $(OBJ_DIR)/%.o, $(notdir $(SOURCES)))

# The name of the final executable
PROJECT_NAME = calculator

# Default to dynamic linking unless STATIC variable is set
LINKING_MODE ?= dynamic

# Compiler flags
CCFLAGS = -Wall -Wextra

# Add -static flag if LINKING_MODE is set to static
ifeq ($(LINKING_MODE), static)
    CCFLAGS += -static
endif

# The default target, which depends on the final executable
all: $(PROJECT_NAME)

# Rule to create the object directory if it doesn't exist
$(OBJ_DIR):
	mkdir -p $(OBJ_DIR)

# Rule to compile .c files into .o files in the object directory
$(OBJ_DIR)/%.o: ./src/%.c | $(OBJ_DIR)
	$(CC) -c $(INCLUDES) $(CCFLAGS) $< -o $@

$(OBJ_DIR)/%.o: ./app/%.c | $(OBJ_DIR)
	$(CC) -c $(INCLUDES) $(CCFLAGS) $< -o $@

# Rule to link object files into the final executable
# $^ is all the prerequisites (the .o files)
# $@ is the target (the executable)
$(PROJECT_NAME): $(OBJS)
	$(CC) $(CCFLAGS) $^ -o $@ 
	 
# Declare that clean is a phony target (not a file)
.PHONY: clean

# Rule to clean up the build, removing .o files and the executable
clean:
	rm -rf $(OBJ_DIR)
	rm -f $(PROJECT_NAME)

