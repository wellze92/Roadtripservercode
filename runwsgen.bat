set class=com.inno.innowebservices.WebservicesDemo
set clpth=./war/WEB-INF/classes/
set resourcedir=./war
set outsourcedir=./src
set outdir=./war/WEB-INF/classes
PATH=%path%;C:\PROGRA~1\Java\jdk1.7.0_17\bin\
wsgen -cp "%clpth%" -wsdl -keep -r "%resourcedir%" -d "%outdir%" -s "%outsourcedir%"  %class%	
