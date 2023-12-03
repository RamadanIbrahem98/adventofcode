# Makefile for compiling and running Java program

# Define the Java compiler
JAVAC = javac

# Define the Java runtime
JAVA = java

# Define the source file
SOURCE = Main.java

# Define the main class
MAIN_CLASS = Main

# Define the target executable
TARGET = $(MAIN_CLASS).class

# Determine the operating system
ifeq ($(OS),Windows_NT)
	# Use Windows commands
	RM = del
else
	# Use Unix-like commands
	RM = rm -f
endif

# Default target: compile and run the program
all: $(TARGET)
	$(JAVA) $(MAIN_CLASS)

# Target to compile the Java source file
$(TARGET): $(SOURCE)
	$(JAVAC) $^

# Target to clean up generated files
clean:
	$(RM) $(TARGET)

# Target to clean up all generated files, including .class files
distclean: clean
	$(RM) *.class
