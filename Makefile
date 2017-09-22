build:
	docker build -t java-doesnt-suck:latest .

size:
	docker images | grep java-doesnt-suck

run:
	docker run -it --rm java-doesnt-suck:latest

verify: build size run
