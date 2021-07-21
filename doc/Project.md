Java 11

multiple participants

message
Source: Participant1
Instrument: AUDUSD
    -   Side: BUY
        Price: 0.7980
        Quantity: 1000000
    -   Side: BUY
        Price: 0.7979
        Quantity: 2000000
    -   Side: SELL
        Price: 0.7982
        Quantity: 5000000

Constrains:
-   100 participants
-   Single instrument
-   PriceLevel limit: 64 for each side
-   Each price in the message unique (no price repetition)
-   No ordering by price
-   Price and Quantity > 0
-   Each new message replaces prev message
-   Empty list - remove all prev Orders



Tasks:

OB

1.  The message as POJO MarketData
2.  Order book class (OrderBook)
    - Bids
        Sorted: 
            Price: highest -> lowest
            Quantity: highest -> lowest
            Name: 
    - Asks
        Sorted: 
          Price: lowest -> highest
          Quantity: highest -> lowest
          Name: 
3.  reset method to clear OB
4.  Update method to apply MarketData message from single participant

1.  getTotalQuantityForPriceAndSide that returns total q
2.  getVwapForQuantityAndSide returns VWAP price for given q and side.

