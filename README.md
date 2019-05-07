Zipkin
======
1) Start a local in memory Zipkin server using docker:
> docker run -it -p 9411:9411 openzipkin/zipkin

2) Use curl to fetch a span:
curl -XGET http://localhost:9411/api/v1/trace/5cd16d2129b01a1d1e4551a7640a6759

3) Call frontend service:
http://localhost:8081/

4) Call backend server directly:
http://localhost:9000/api