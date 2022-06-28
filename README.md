# Token Bucket Rate Limiter
Rate limiters are used by API services to limit the number of requests that can be received in a given period of time. 
This project implements a rate limiter using the token bucket algorithm.

Rate limiters generally requires the following basic functionality:
- If the rate limit has **not** been reached: re-route incoming request to the backend API servers.
- Else: return a 429 response status code to the client.

## Token Bucket Algorithm
A token bucket is counter with the following properties:
- Capacity - this is the maximum value of the counter
- Refill Rate - this is rate at which the counter increases

The algorithm for an incoming request:
1. Check the bucket for available tokens (counter > 0).
2. If there are tokens available:
   - Consume a token (decrement the counter by 1).
   - Relays the request to the correct API server.
3. Else:
   - Return a 429 error to the client.

The **refill rate** sets the rate limit as one token is required for every request. The **capacity** must be equal to 
or greater than refill rate. Having a capacity greater than the refill rate enables bursts of requests greater than the
rate limit however the sustained rate limit will not exceed the refill rate.

## Concurrency Concerns

## Usage

## Testing
