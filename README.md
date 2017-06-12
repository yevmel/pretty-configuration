# pretty-configuration

human friendly representation of your applications configuration.

## cli

| parameters        | description                                                          |
| ------------- |--------------------------------------------------------------------------|
| --template    | path to freemarker template                                              |
| --output-dir  | where to put resulting files                                             |
| --output-name | filename of the resulting file (default: name of the template file       |
| --input-files | arbitrary number of configuration files (at the moment only *.properties |

## samples

* [template](template.html)
* [ant configuration](build.xml)
* [gradle configuration (generateHTML task)](build.gradle)
* [properties file (incl. meta)](src/test/resources/application.develop.properties)