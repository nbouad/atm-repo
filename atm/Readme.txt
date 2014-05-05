# Application - Simulation of an ATM

* *Author* : [Najate BOUAD](nbouad@gmail.com)
* *Technologies* : Java 7 / j-text-utils / JUnit / Mockito / PowerMockito/ Easytesting
* *Compilation* : Maven 2.2.1
* *Application Type* : Executable (JAR packaging)
* *Summary* : A simulation of an ATM

[Download the code from GitHub](https://github.com/nbouad/atm-repo)

## Choosen Frameworks

For the ATM application, j-text-utils has been choosing to print a table

## Compile and package

Being Maven centric, you can compile and package it with `mvn clean compile`, `mvn clean package` or 
`mvn clean install`. The `package` and `install` phase will automatically trigger the unit tests. 

## Execute the application

Once packaged, the application can be executed in commandline with 'java -jar atm-1.0.0.jar'
The necessary java version to be able to run the application is java 7 or java 8.

## Requirements
  
We need an application that simulates the Backend logic of a cash dispensing Automatic Teller Machine (ATM).   
Of course the application is not required to distribute money, but it should be able to simulate and report the outcome of people requesting money.   
This simulation will not require any authentication or PIN to access the ATM.   
Rather it is to be focused on keeping track of the current cash of the ATM, and dispensing only the notes available. 
It should be possible to tell it that it has so many of each type of note during initialisation. 
After initialisation, it is only possible to remove notes.  
It must know how many of each type of bank note it has and it should be able to report back how much of each note it has.  
It must support $20 and $50 notes.  
It should be able to dispense only legal combinations of notes. 
For example, a request for $100 can be satisfied by either five $20 notes or two $50 notes. It is not required to present a list of options.  
If a request cannot be satisfied due to failure to find a suitable combination of notes, it should report an error condition in some fashion. 
For example, in an ATM with only $20 and $50 notes, it is not possible to dispense $30.   
Dispensing money should reduce the amount of available cash in the machine.   
Failure to dispense money due to an error should not reduce the amount of available cash in the machine. 

## Licensing

<a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/"><img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by-sa/3.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/">Creative Commons Attribution-ShareAlike 3.0 Unported License</a>.

<div class="footer">
    <span class="footerTitle"><span class="uc">n</span>ajate <span class="uc">b</span>ouad</span>
</div> 