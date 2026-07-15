$env:JAVA_HOME="C:\Program Files\Java\jdk1.8.0_144"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"

$classpath = (Get-ChildItem -Path "lib" -Filter "*.jar" | ForEach-Object { $_.FullName }) -join ";"
$classpath = "$classpath;target\classes"

& java "-Dfile.encoding=UTF-8" -cp $classpath com.shop.aifruit.AiFruitShopApplication