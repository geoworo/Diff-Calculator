
[![Actions Status](https://github.com/geoworo/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/geoworo/java-project-71/actions) [![Maintainability](https://api.codeclimate.com/v1/badges/0cc279d1934371289953/maintainability)](https://codeclimate.com/github/geoworo/java-project-71/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/0cc279d1934371289953/test_coverage)](https://codeclimate.com/github/geoworo/java-project-71/test_coverage)

# Diff Calculator
A simple command line applet that calculates diff between two JSON or YAML files.
The applet supports three output formats: stylish (default), plain and json.

## Usage
The applet can be used as a separate command line program, or as a library. 

### Using as a command line program:
1. Download the files.
2. Create a **\*.jar** file using `gradle clean installDist`.
3. From the 'app' subdirectory, access the resulting **\*.jar** file:
   ```
   ./build/install/app/bin/app -h
   ```
### Using as a library:
1. Download the files.
2. Create a **\*.jar** file using `gradle clean installDist`.
3. Place the file into the same folder as your source code.
4. Import the needed class or package into your program. 

## A demonstration:

[![asciicast](https://asciinema.org/a/ZegbxobPwDC2S8hrhN9ofHOXk.svg)](https://asciinema.org/a/ZegbxobPwDC2S8hrhN9ofHOXk)
