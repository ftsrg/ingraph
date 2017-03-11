# Middleware

We should define a middleware similar to the one defined in the [IncQuery-D paper](https://link.springer.com/chapter/10.1007/978-3-319-11653-2_40).

## Middleware driver requirements

- The `registerQuery` method should return a query specification.
- The query specification should contain information about the columns.
- The driver should be able to return the last `ChangeSet`.

## Middleware driver RFC

- The middleware driver should notify every observer about the changes. If any of the observers block the middleware driver (e.g. network issues while delivering), the notifications for the following observers can delayed greatly.
	* Possible solution could be a thread pool.
