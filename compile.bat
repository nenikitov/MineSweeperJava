:: Recreate a build directory
del  /q build/
mkdir build/
:: Build the program
javac -d ./build/ *.java
:: Create the manifest
cd build/
echo Main-Class: MineSweeper > Manifest.txt
:: Build into jar
jar -cfm MineSweeper.jar Manifest.txt *
:: Create a .bat executable
echo java -jar MineSweeper.jar > MineSweeper.bat
:: ZIP
zip build.zip MineSweeper.bat MineSweeper.jar