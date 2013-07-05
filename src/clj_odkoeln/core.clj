(ns clj-odkoeln.core
  (:require [clj-http.client :as client]))


(def default-header {:as :json})

(def events
  {:type :http/get
   :resource "http://www.stadt-koeln.de/externe-dienste/open-data/events-od.php"
   :parameter {:type :request-type
               :kat :category-id
               :ndays :days}})

(def traffic
  {:type :http/get
   :resource "http://www.stadt-koeln.de/externe-dienste/open-data/traffic.php"})


(def parking-deck
  {:type :http/get
   :resource "http://www.stadt-koeln.de/externe-dienste/open-data/parking.php"})

(defn generate-parameters
  [req-map req-data]
  (let [req default-header]
    (-> default-header
        (merge {:query-params (into {} (remove (comp nil? val) (into {} (map #(vector (key %) (get req-data (val %))) (:parameter req-map)))))}))))

(defn get-data
  [req-map req-data]
  (case (:type req-map)
    :http/get (client/get (:resource req-map) (generate-parameters req-map req-data))))

;; convenience-fns

(defn get-events []
  (:items (:body (get-data events {}))))

(defn get-events [req-data]
  (:items (:body (get-data events req-data))))

(defn get-event-categories []
  (:items (:body (get-data events {:request-type "listkat"}))))

(defn get-events-by-category [id]
  (:items (:body (get-data events {:category-id id}))))

(defn get-traffic-info []
  (:body (get-data traffic {})))

(defn get-parking-deck-info []
  (:features (:body (get-data parking-deck {}))))
