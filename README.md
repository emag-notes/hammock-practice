# hammock-practice

This is a test for [Hammock](https://github.com/johnament/hammock).

## Usage

### Build API Server

~~~
git clone https://github.com/emag/hammock-practice.git
cd hammock-practice
mvn assembly:assembly
~~~

### Run API Server

~~~
java -cp "target/hammock-practice-bin/hammock-practice/lib/*" org.jboss.weld.environment.se.StartMain
~~~

Now the API Server listen to `0.0.0.0:18080`.

### Request

~~~
curl localhost:18080/api/echo
hello%
~~~