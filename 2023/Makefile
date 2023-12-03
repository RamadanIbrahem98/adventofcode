JAVAC = javac
JAVA = java
SOURCE = Main.java
MAIN_CLASS = Main
RM = rm -f

all: $(patsubst %, %-run, $(wildcard day*))

%-run: %-compile
		(cd $* && $(JAVA) $(MAIN_CLASS))

%-compile:
	(cd $* && $(JAVAC) $(SOURCE))

%-clean:
	(cd $* && $(RM) $(MAIN_CLASS).class)

clean: $(patsubst %, %-clean, $(wildcard day*))

distclean: clean
	$(RM) */*.class
