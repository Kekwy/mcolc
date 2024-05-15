docker run -d \
  --name=rabbitmq \
  -p 5671:5671 \
  -p 5672:5672 \
  -p 4369:4369 \
  -p 15671:15671 \
  -p 15672:15672 \
  -p 25672:25672 \
  -e RABBITMQ_DEFAULT_USER=mcolc \
  -e RABBITMQ_DEFAULT_PASS=mcolc \
  rabbitmq:management
