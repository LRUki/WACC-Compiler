# Sample Makefile for the WACC Compiler lab: edit this to build your own comiler
# Locations

ANTLR_DIR	:= antlr_config
SOURCE_DIR	:= src
OUTPUT_DIR	:= bin

# Tools

ANTLR	:= antlrBuild
FIND	:= find
RM	:= rm -rf
MKDIR	:= mkdir -p
JAVA	:= java
JAVAC	:= javac

JFLAGS	:= -sourcepath $(SOURCE_DIR) -d $(OUTPUT_DIR) -cp lib/antlr-4.9.1-complete.jar 

GRADLE	:= gradlew
BUILD_DIR	:= build
JAR_DIR	:= $(BUILD_DIR)/libs
JAR_NAME	:= wacc_23-all-1.0-SNAPSHOT.jar

# the make rules

all: rules gradle

# runs the antlr build script then attempts to compile all .java files within src
rules:
	cd $(ANTLR_DIR) && ./$(ANTLR) 
	$(FIND) $(SOURCE_DIR) -name '*.java' > $@
	$(MKDIR) $(OUTPUT_DIR)
	$(JAVAC) $(JFLAGS) @$@
	$(RM) rules

gradle:
	./$(GRADLE) fatJar
	cp ./$(JAR_DIR)/$(JAR_NAME) ./$(JAR_NAME)

clean:
	$(RM) rules $(OUTPUT_DIR) $(SOURCE_DIR)/antlr $(BUILD_DIR) $(JAR_NAME)

.PHONY: all rules gradle clean


