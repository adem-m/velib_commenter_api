
IMAGE_NAME=paulb314/velib-commenter-api
IMAGE_TAG=1.0.0
TEST_TAG=test

build-docker:
	docker build -f Dockerfile -t $(IMAGE_NAME):$(IMAGE_TAG) .
.PHONY: build

deploy-docker:
	docker push $(IMAGE_NAME):$(IMAGE_TAG)
.PHONY: deploy

rmi:
	docker rmi $(IMAGE_NAME):$(IMAGE_TAG)
.PHONY: rmi

run:
	docker run --rm -p 8080:8080 $(IMAGE_NAME):$(IMAGE_TAG)
.PHONY: run

run-local:
	mvn clean install -DskipTests=true -Dsonar.skip=true -Dexec.mainClass="com.gitter.socialapi.SocialApiApplication"
	mvn exec:java -Dexec.mainClass="org.esgi.trademe.Main"
.PHONY: run-local

test:
	$(MAKE) build IMAGE_TAG=$(TEST_TAG)
	docker run --entrypoint=mvn $(IMAGE_NAME):$(TEST_TAG) test
.PHONY: test

deploy:
	docker push $(IMAGE_NAME):$(IMAGE_TAG)
.PHONY: deploy

debug:
	docker run -ti --entrypoint=sh quay.io/paulbarrie7/trademe:1.0.0
.PHONY: debug

test-local:
	mvn test  -Dsonar.skip=true
.PHONY: test