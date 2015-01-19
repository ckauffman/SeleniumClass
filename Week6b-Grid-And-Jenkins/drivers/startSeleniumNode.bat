REM java -jar selenium-server-standalone-2.44.0.jar -role node  -hub http://localhost:4444/grid/register
java -jar selenium-server-standalone-2.44.0.jar -role node -hub http://localhost:4444/grid/register -maxSession 15 -browser browserName="chrome",version=ANY,platform=WINDOWS,maxInstances=15 -Dwebdriver.chrome.driver=chromedriver.exe
