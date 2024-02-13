# deposit-and-savings-calculator
A sample Kotlin project that calculate deposit and savings

This command line App gets the following parameters and print *the final balance* 
- The start deposit amount
- The interest rate p.a
- The investment term in months
- Interest paid at (Monthly, Quarterly, Annually, AtMaturity)

### How to run
1- Build
```bash
mvn clean package
```

2- Run in *Interactive* mode that asks user to enter parameters one by one
```bash
java -jar ./target/deposit-and-savings-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar
```

3- Users are able to fill all parameters in a text file and pass to the App 
to avoid tedious job of working with command line. This is very suitable for CLI and pipelines.  
For example
```bash
java -jar ./target/deposit-and-savings-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar -f ./input/sample1.properties
```
