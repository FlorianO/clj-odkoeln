# clj-odkoeln

A small clojure library for the open data apis from [Offene Daten Köln](http://www.offenedaten-koeln.de/offene-daten/).

## Installation

clj-odkoeln will soon be available from [Clojars](http://clojars.org/clj-odkoeln):
```clojure
[clj-odkoeln "0.1.0"]
```

## Usage

Everything is available in `clj-odkoeln.core` namespace.

If you want to use it from the repl:
```clojure
(use 'clj-odkoeln.core)
```

If you want to use it in a project:
```clojure
(ns your-fancy.app
    (:require [clj-odkoeln :as k]))
```

A request always looks like this:
```clojure
(k/get-data req-type req-data)
```
where req-type and req-data are just plain ol' clojure maps.

### Usage of the events-api

All valid parameter:
* `:request-type` - right now only "katlist" available to list all event categories.
* `:category-id` - to list all events from a single category.
* `:days` - number of days to fetch events

You could get call the events-api via:
```clojure
(k/get-data events {})
```

but maybe one of the convenience functions is enough for you:
* `(get-events)` - to get all the events for the next 7 days.
* `(get-events {})` - get the events and add request parameter as you wish
* `(get-event-categories)` - to get all event categories
* `(get-events-by-categorie id)` - to get all events with a specific categorie


## License

Copyright © 2013 Florian Over

Distributed under the Eclipse Public License, the same as Clojure.
