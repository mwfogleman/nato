(ns nato.core
  (:require
   [reagent.core :as r]
   [clojure.string :as str]))

;; -------------------------
;; Nato Phonetic Alphabet Functionality

(def alphabet {"." "Point"
               "0" "Zero"
               "1" "One"
               "2" "Two"
               "3" "Three"
               "4" "Four"
               "5" "Five"
               "6" "Six"
               "7" "Seven"
               "8" "Eight"
               "9" "Nine"
               "A" "Alpha"
               "B" "Bravo"
               "C" "Charlie"
               "D" "Delta"
               "E" "Echo"
               "F" "Foxtrot"
               "G" "Golf"
               "H" "Hotel"
               "I" "India"
               "J" "Juliet"
               "K" "Kilo"
               "L" "Lima"
               "M" "Mike"
               "N" "November"
               "O" "Oscar"
               "P" "Papa"
               "Q" "Quebec"
               "R" "Romeo"
               "S" "Sierra"
               "T" "Tango"
               "U" "Uniform"
               "V" "Victor"
               "W" "Whiskey"
               "X" "X-ray"
               "Y" "Yankee"
               "Z" "Zulu"})

(def alphabet-regex #"[A-Za-z0-9\.\s]")

(defn convert
  "Converts a string into the NATO phonetic alphabet."
  [input]
  (let [items (map clojure.string/upper-case input)
        values (for [i items] (get alphabet i i))]
    (clojure.string/join " " values)))

(def app-state (r/atom "Have fun!"))

;; -------------------------
;; Views

(defn nato-input []
  [:div
   [:p "Put in a phrase here:"]
   [:p [:center [:input {:placeholder @app-state
                         :type "text"
                         :on-change #(reset! app-state (-> % .-target .-value))}]]]])

(defn nato-output
  []
  (let [state @app-state
        v (convert state)]
    [:div
     [:p "Here is what that results in in the NATO Phonetic Alphabet:"] 
     [:p v]]))

(defn home-page []
  [:div
   [:h2 "The NATO Phonetic Alphabet"]
   [:h3 "by " [:a {:href "http://www.mwfogleman.com"} "Michael Fogleman"]]
   [nato-input]
   [nato-output]])



;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
