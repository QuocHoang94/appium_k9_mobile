Start HUB
Directory
cd /Users/nhuvo/Desktop/SDETPRO/sdetpro-appium-mobile/selenium-grid

MacOS
java -cp selenium-server-standalone-3.141.59.jar:selenium-grid-custom-matcher-3.141.59.jar org.openqa.grid.selenium.GridLauncherV3 -role hub -hubConfig hubConfig.json

Windows
java -cp "selenium-server-standalone-3.141.59.jar;selenium-grid-custom-matcher-3.141.59.jar" org.openqa.grid.selenium.GridLauncherV3 -role hub -hubConfig hubConfig.json

Start Node 01
appium -p 6000 --nodeconfig node_6000_config.json

Start Node 02
appium -p 7001 --nodeconfig node_7001_config.json

Start Node 03
appium -p 8000 --nodeconfig node_8000_config.json

