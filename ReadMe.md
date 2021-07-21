Order Book naive implementation is presented

Order prices were presented as longs, it allows performing quick
math operations and correct comparison between prices.
Price Precision: 8 decimal places


Project limitations due to time constrain
1. No logging
2. No javadocs (code is simple with self explainable variable and method names)
3. No application to run
   VWAP method, for example, shown only in tests
4. Created Limited number of the Interfaces just outline API interfaces
5. Test coverage: only critical classes and methods (not all edge cases)
    - PriceLevel
    - OrderBookSide
    - OrderBook
    - conversion long to double and back
6. Tests run in Intellej Idea
7. Mocking for tests was not used

The project addressed following tasks:
1.  Order Book aggregates MarketData messages
    - Add Order
    - Amend Order
    - Cancel Order
    - Bids and offers are sorted according to task
        -   Price
        -   Quantity
        -   Source
    - Order crosses are not considered (no task for this)
2.  Main Order Book functions
    - reset
    - update
    - getTotalQuantityForPriceAndSide
    - getVwapForQuantityAndSide
