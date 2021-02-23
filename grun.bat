set JVM_VerNo=jdk-14
call X:\MiddleWares\JVM\set-jdk-vars.bat

java -cp bin;lib\antlr-4.9.1-complete.jar org.antlr.v4.gui.TestRig antlr.Wacc program -gui wacc_examples\valid\expressions\intExpr1.wacc
