# Token Bucket Rate Limiter
Rate limiters are used by API services to limit the number of requests that can be received in a given period of time. 
This project implements a rate limiter using the token bucket algorithm.

Rate limiters generally requires the following basic functionality:
- If the rate limit has **not** been reached: re-route incoming request to the backend API servers.
- Else: return a 429 response status code to the client.

## Token Bucket Algorithm
