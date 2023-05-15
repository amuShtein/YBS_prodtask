PORT=8080
NAME=container
HOST_DB=4040
CONTAINER_DB=5432

build:
	gradle build

docker:
	docker build -t $(NAME) .

deploy:
	sudo docker run -p $(PORT):$(PORT) -p $(HOST_DB):$(CONTAINER_DB) $(NAME)

running:
	docker ps

clean:
	gradle clean
	rm $(NAME)
