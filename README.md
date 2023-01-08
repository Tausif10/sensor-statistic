#Sensor statistic
***

This application perform some calculation to get the sensor statistic like sensor id, min, max and average sensor humidity.

###How to run this application?
`Application.scala` inside the `src/main/scala` package is entry point of this application.
1. If you are using Intellij you can run it from there itself by clicking on green arrow like symbol.
2. You can run same application using terminal - 
   1. `cd /your-path/sensor-status-calculator`
   2. Run this command `sbt run`
      1. It will ask you the path of the directory where file containing sensor humidity data are saved.
```
$ sbt run                                                                                                                                  
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=256m; support was removed in 8.0                                    
[info] welcome to sbt 1.8.0 (Oracle Corporation Java 1.8.0_301)                                                                            
[info] loading global plugins from C:\Users\Tausif\.sbt\1.0\plugins                                                                       
[info] loading project definition from C:\Users\Tausif\sensor-status-calculator\project                                          
[info] loading settings for project sensor-status-calculator from build.sbt ...                                                            
[info] set current project to sensor-status-calculator (in build file:/C:/Users/Tausif/sensor-status-calculator/)              
[info] running Application                                                                                                                 
enter directory path: src/test/resources                                                                                                   
Num of processed files: 2                                                                                                                  
Num of processed measurements: 7                                                                                                           
Num of failed measurements: 2                                                                                                              
                                                                                                                                           
Sensors with highest avg humidity:                                                                                                         
                                                                                                                                           
sensor-id,min,avg,max                                                                                                                      
s2, 78, 82.0, 88                                                                                                                           
s1, 10, 54.0, 98                                                                                                                           
s3, NaN, NaN, NaN                                                                                                                          
                                                                                                                                           
[success] Total time: 67 s (01:07), completed 8 Jan, 2023 1:56:59 PM                                                                       
```