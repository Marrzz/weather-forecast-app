# Simple application to display current weather and 3 day forecast.

### OpenWeatherMapAPI
  
  This application presumes you have a premium OpenWeatherMapAPI key.
  This key should be used in ```ÀPI_KEY``` constant in ```MyWebResource.java``` class
  
  [Get your key here...](https://openweathermap.org/api)

### Commands for interacting with the project from terminal:

Navigate to the root foler of the project and run the following commands

- Build project: `mvn build`
- Compile project: `mvn compile`
- Run tests:  `mvn run test`
- Run Project: `mvn exec:java -Dexec.mainClass=main.Main`

### Technologies used
- Lombok
- Gson
- Google Truth
- REST Assured

### Accepted inputs
- Input file has to be in txt format and be located in the root directory of the project

### Output
- Output file(s) will be created in the root directory of the project. Created files will be in json format.
  If a file for a city already exists the file will be overwritten.
