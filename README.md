# hammock-practice

This is an example for [Hammock](https://github.com/johnament/hammock).

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

### APIs

#### GET /greetings

~~~
curl "localhost:18080/api/greetings/"
[{"id":1,"english":"Good Morning","japanese":"おはよう"},{"id":2,"english":"Hello","japanese":"こんにちは"},{"id":3,"english":"Good Evening","japanese":"こんばんは"}]%
~~~

#### GET /greetings/:id

~~~
curl "localhost:18080/api/greetings/2"
{"id":2,"english":"Hello","japanese":"こんにちは"}
~~~

#### GET /greetings/echo?message=:message

~~~
curl "localhost:18080/api/greetings/echo?message=Hammock"
Hi, Hammock%
~~~