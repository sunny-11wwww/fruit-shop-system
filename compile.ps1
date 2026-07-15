$env:JAVA_HOME="C:\Program Files\Java\jdk1.8.0_144"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"

if (-not (Test-Path "lib")) {
    New-Item -ItemType Directory -Force -Path "lib"
    .\mvnw.cmd dependency:copy-dependencies -DoutputDirectory=lib -q
}

$classpath = (Get-ChildItem -Path "lib" -Filter "*.jar" | ForEach-Object { $_.FullName }) -join ";"

New-Item -ItemType Directory -Force -Path "target\classes"

javac -encoding UTF-8 -parameters -cp $classpath -d target\classes src\main\java\com\shop\aifruit\*.java src\main\java\com\shop\aifruit\controller\*.java src\main\java\com\shop\aifruit\service\*.java src\main\java\com\shop\aifruit\service\impl\*.java src\main\java\com\shop\aifruit\mapper\*.java src\main\java\com\shop\aifruit\entity\*.java src\main\java\com\shop\aifruit\config\*.java

Copy-Item -Path "src\main\resources\*" -Destination "target\classes\" -Recurse -Force

Write-Host "Compilation complete"